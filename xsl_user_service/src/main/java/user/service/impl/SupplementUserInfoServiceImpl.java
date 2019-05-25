package user.service.impl;

import com.xsl.user.FileOperateResource;
import com.xsl.user.vo.*;
import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.TagVo;
import example.XslUserExample;
import example.XslUserFileExample;
import mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.*;
import user.service.SupplementUserInfoService;
import user.service.TaskInfoService;
import user.service.UserMqService;
import user.service.UserInfoService;
import util.JedisClientUtil;
import util.ListUtil;
import util.Md5Utils;
import util.UUIDUtil;
import vo.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 14:19
 */
@Service
public class SupplementUserInfoServiceImpl implements SupplementUserInfoService {
    @Autowired
    private XslUserMapper xslUserMapper;
    @Autowired
    private XslSchoolinfoMapper xslSchoollinfoMapper;
    @Autowired
    private XslHunterTagMapper xslHunterTagMapper;
    @Autowired
    private XslUserFileMapper xslUserFileMapper;

    @Autowired
    private UserMqService userMqService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private FileOperateResource fileOperateResource;

    @Resource
    private ThreadPoolTaskExecutor userExecutor;
    @Resource
    private TaskInfoService taskInfoService;

    private static final Logger logger = LoggerFactory.getLogger(SupplementUserInfoServiceImpl.class);


    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${USER_INFO}")
    private String USER_INFO;
    @Value("${USER_TX_URL}")
    private String USER_TX_URL;

    @Override
    public ResBaseVo saveUserInfo(UserReqVo userReqVo) {
        XslUser xslUser = new XslUser();
        BeanUtils.copyProperties(userReqVo, xslUser);
        if(!StringUtils.isEmpty(userReqVo.getPassword())){
            xslUser.setPassword(Md5Utils.digestMds(userReqVo.getPassword()));
        }

        XslUserExample xslUserExample = new XslUserExample();
        xslUserExample.createCriteria().andPhoneEqualTo(userReqVo.getPhone());
        try {
            xslUserMapper.updateByExampleSelective(xslUser, xslUserExample);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        JedisClientUtil.delete(USER_INFO + ":" + userReqVo.getUserid());

        //es中数据同步待修复
        userExecutor.execute(() -> esUserName(userReqVo.getUserid(), userReqVo.getName()));

        return ResBaseVo.ok();
    }

    @Override
    public ResBaseVo userAcc(UserAccReqVo userAccReqVo) {
        XslSchoolinfo xslSchoolinfo = new XslSchoolinfo();

        //初步信息录入
        BeanUtils.copyProperties(userAccReqVo, xslSchoolinfo);
        xslSchoolinfo.setSchoolid(UUIDUtil.getUUID());
        xslSchoolinfo.setStartdate("2015-09-01");
        xslSchoolinfo.setDegree((byte) 2);
        xslSchoolinfo.setSchoolhours((byte) 4);

        int i = xslSchoollinfoMapper.insertSelective(xslSchoolinfo);

        if(i < 1){
            return ResBaseVo.build(500, "服务器繁忙，请重试");
        }

        String userid = userAccReqVo.getUserid();
        XslUser xslUser = new XslUser();
        xslUser.setSchoolinfo(xslSchoolinfo.getSchoolid());
        xslUser.setState((byte) 2);

        if(!StringUtils.isEmpty(userAccReqVo.getPhone())){
            xslUser.setPhone(userAccReqVo.getPhone());
        }

        XslUserExample xslUserExample = new XslUserExample();
        xslUserExample.createCriteria().andUseridEqualTo(userid);
        int count = xslUserMapper.updateByExampleSelective(xslUser, xslUserExample);

        if(count < 1){
            return ResBaseVo.build(500, "服务器繁忙，请重试");
        }

        JedisClientUtil.delete(USER_INFO +":"+ userid);

        //二次自动审核认证
        List<com.xsl.user.vo.TagVo> tagVos = userAccReqVo.getTagVos();
        if(tagVos == null || tagVos.size() == 0){
            return ResBaseVo.ok(2);
        }

        XslUser userInfo = userInfoService.getUserInfo(userid);
        String hunterId = userInfo.getHunterid();

        List<XslHunterTag> xslHunterTags = new ArrayList<>();

        for (TagVo tagVo : tagVos){
            XslHunterTag xslHunterTag = new XslHunterTag();
            xslHunterTag.setHunterid(hunterId);
            xslHunterTag.setTagid(tagVo.getTagid());
            xslHunterTags.add(xslHunterTag);
        }

        int tagCount = xslHunterTagMapper.insertSelectiveBatch(xslHunterTags);

        if(tagCount < 1){
            return ResBaseVo.ok(2);
        }

        xslUser.setState((byte) 1);
        int AccCount = xslUserMapper.updateByExampleSelective(xslUser, xslUserExample);

        if(AccCount < 1){
            return ResBaseVo.build(200, "认证成功，待管理员审核");
        }

        return ResBaseVo.ok(1);
    }


    @Override
    public ResBaseVo upLoadUserTx(FileUploadReqVo uploadFile, String userid){
        //1.获取用户信息
        XslUser userInfo = userInfoService.getUserInfo(userid);
        if (userInfo.getUserid() == null) {
            return ResBaseVo.build(403, "用户不存在");
        }

        try {
            String userTx = userInfoService.getUserTx(userid);
            if(!StringUtils.isEmpty(userTx)){
                XslUserFileExample xslUserFileExample = new XslUserFileExample();
                xslUserFileExample.createCriteria().andUseridEqualTo(userid).andTypeEqualTo("TX");
                xslUserFileMapper.deleteByExample(xslUserFileExample);
                JedisClientUtil.delete(USER_TX_URL + ":" + userid);
            }

            FileVo fileVo = fileOperateResource.fileUpload(uploadFile);

            if(fileVo.getStatus() != 200){
                return ResBaseVo.build(fileVo.getStatus(), fileVo.getMsg());
            }

            //2.获取用户信息
            //建立用户与文件关联
            XslUserFile xslUserFile = new XslUserFile();
            xslUserFile.setUserid(userInfo.getUserid());
            xslUserFile.setFileid(fileVo.getFileid());
            xslUserFile.setType("TX");

            int insert = xslUserFileMapper.insert(xslUserFile);
            if(insert < 1){
                return ResBaseVo.build(500, "服务器异常");
            }

            //3.异步更新状态
            userExecutor.execute(() -> esUserTxurl(userid, fileVo.getUrl()));

            return ResBaseVo.ok(fileVo.getUrl());
        } catch (Exception e) {
           throw new RuntimeException(e);
        }

    }


    private void esUserTxurl(String userid, String txUrl) {
        logger.info("esUserName userid"+userid+"txUrl:"+txUrl);
        esUserInfo(userid, "", txUrl);
    }

    private void esUserName(String userid, String name) {
        logger.info("esUserName userid"+userid+"name:"+name);
        esUserInfo(userid, name, "");
    }


    private void esUserInfo(String userid, String name, String txUrl) {
        XslUser userInfo = userInfoService.getUserInfo(userid);
        String masterId = userInfo.getMasterid();
        List<XslTask> tasks = taskInfoService.getTaskByMasterId(masterId);

        if(ListUtil.isNotEmpty(tasks)){
            for(XslTask xslTask : tasks){
                UpdateTaskVo updateTaskVo = new UpdateTaskVo();
                if(!StringUtils.isEmpty(name)){
                    updateTaskVo.setName(name);
                }
                if(!StringUtils.isEmpty(txUrl)){
                    updateTaskVo.setTxUrl(txUrl);
                }
                updateTaskVo.setTaskId(xslTask.getTaskid());
                userMqService.updateEsTask(updateTaskVo);
            }
        }
    }
}
