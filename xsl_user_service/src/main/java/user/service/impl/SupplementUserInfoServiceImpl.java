package user.service.impl;

import com.xsl.user.vo.TagVo;
import com.xsl.user.vo.UserAccReqVo;
import com.xsl.user.vo.UserReqVo;
import example.XslUserExample;
import mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.XslHunterTag;
import pojo.XslSchoolinfo;
import pojo.XslTask;
import pojo.XslUser;
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

    @Resource
    private ThreadPoolTaskExecutor userExecutor;
    @Autowired
    private UserMqService userMqService;

    @Resource
    private TaskInfoService taskInfoService;

    private static final Logger logger = LoggerFactory.getLogger(SupplementUserInfoServiceImpl.class);


    @Autowired
    private UserInfoService userInfoService;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${USER_INFO}")
    private String USER_INFO;
    @Value("${USER_TX_URL}")
    private String USER_TX_URL;

    @Override
    public com.xsl.user.vo.ResBaseVo saveUserInfo(UserReqVo userReqVo) {
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

        return com.xsl.user.vo.ResBaseVo.ok();
    }

    @Override
    public com.xsl.user.vo.ResBaseVo userAcc(UserAccReqVo userAccReqVo) {
        XslSchoolinfo xslSchoolinfo = new XslSchoolinfo();

        //初步信息录入
        BeanUtils.copyProperties(userAccReqVo, xslSchoolinfo);
        xslSchoolinfo.setSchoolid(UUIDUtil.getUUID());
        xslSchoolinfo.setStartdate("2015-09-01");
        xslSchoolinfo.setDegree((byte) 2);
        xslSchoolinfo.setSchoolhours((byte) 4);

        int i = xslSchoollinfoMapper.insertSelective(xslSchoolinfo);

        if(i < 1){
            return com.xsl.user.vo.ResBaseVo.build(500, "服务器繁忙，请重试");
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
            return com.xsl.user.vo.ResBaseVo.build(500, "服务器繁忙，请重试");
        }

        JedisClientUtil.delete(USER_INFO +":"+ userid);

        //二次自动审核认证
        List<com.xsl.user.vo.TagVo> tagVos = userAccReqVo.getTagVos();
        if(tagVos == null || tagVos.size() == 0){
            return com.xsl.user.vo.ResBaseVo.ok(2);
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
            return com.xsl.user.vo.ResBaseVo.ok(2);
        }

        xslUser.setState((byte) 1);
        int AccCount = xslUserMapper.updateByExampleSelective(xslUser, xslUserExample);

        if(AccCount < 1){
            return com.xsl.user.vo.ResBaseVo.build(200, "认证成功，待管理员审核");
        }

        return com.xsl.user.vo.ResBaseVo.ok(1);
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
