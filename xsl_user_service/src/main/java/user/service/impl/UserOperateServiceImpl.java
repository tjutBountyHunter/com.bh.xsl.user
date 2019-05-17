package user.service.impl;

import com.google.gson.Gson;
import enums.UserStateEnum;
import example.*;
import mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import pojo.*;
import user.service.InitUserInfo;
import user.service.UserInfoService;
import user.service.UserOperateService;
import util.*;
import vo.ResBaseVo;
import vo.UserReqVo;
import vo.UserResVo;
import vo.XslUserRegister;

import java.util.List;


@Service
public class UserOperateServiceImpl implements UserOperateService {

    @Autowired
    private XslFileMapper xslFileMapper;
    @Autowired
    private XslHunterMapper xslHunterMapper;
    @Autowired
    private XslMasterMapper xslMasterMapper;
    @Autowired
    private XslUserMapper xslUserMapper;
    @Autowired
    private XslSchoolinfoMapper xslSchoollinfoMapper;
    @Autowired
    private XslUserFileMapper xslUserFileMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private InitUserInfo initUserInfo;


    private static final Logger logger = LoggerFactory.getLogger(UserOperateServiceImpl.class);


    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${USER_INFO}")
    private String USER_INFO;
    @Value("${USER_TX_URL}")
    private String USER_TX_URL;

    @Override
    public UserResVo quickCreateUser(XslUserRegister xslUserRegister) {
        XslUserExample example = new XslUserExample();
        XslUserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(xslUserRegister.getPhone());
        List<XslUser> list = xslUserMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            return (UserResVo) ResBaseVo.build(403,"该手机号已经注册过");
        }


        XslUser xslUser = new XslUser();
        xslUser.setUserid(UUIDUtil.getUUID().substring(0, 12));
        //初始化猎人信息
        XslHunter xslHunter = initUserInfo.initXslHunter(xslUser);
        //初始化雇主信息
        XslMaster xslMaster = initUserInfo.initXslMaster(xslUser);
        //初始化用户信息
        initUserInfo(xslUserRegister, xslUser, xslHunter, xslMaster);

        UserResVo userResVo = new UserResVo();
        BeanUtils.copyProperties(xslUser, userResVo);
        userResVo.setUserid(xslUser.getUserid());
        userResVo.setMasterid(xslMaster.getMasterid());
        userResVo.setMasterlevel(xslMaster.getLevel());
        userResVo.setHunterid(xslHunter.getHunterid());
        userResVo.setHunterlevel(xslHunter.getLevel());
        userResVo.setTxUrl("http://47.93.200.190/images/default.png");

        JedisClientUtil.set(REDIS_USER_SESSION_KEY + ":" + xslUserRegister.getPhone(), xslUserRegister.getToken());

        return userResVo;
    }

    /**
     * 用户登录
     * @param userReqVo
     * @return
     */
    @Override
    public UserResVo userLogin(UserReqVo userReqVo) {

        String phone = userReqVo.getPhone();
        String password = userReqVo.getPassword();
        String token = userReqVo.getToken();

        if(StringUtils.isEmpty(phone)){
            return (UserResVo) ResBaseVo.build(403, "手机号码为空");
        }

        if(StringUtils.isEmpty(password)){
            return (UserResVo) ResBaseVo.build(403, "密码为空");
        }

        if(StringUtils.isEmpty(token)){
            return (UserResVo) ResBaseVo.build(403, "权限不足");
        }

        //1.查询
        XslUserExample example = new XslUserExample();
        XslUserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<XslUser> list = xslUserMapper.selectByExample(example);

        //2.判断用户是否存在
        if(list == null || list.size() < 1){
            return (UserResVo) ResBaseVo.build(403, "用户名或密码错误");
        }
        XslUser user = list.get(0);

        UserResVo resVo = new UserResVo();
        BeanUtils.copyProperties(user, resVo);
        logger.info("login user is {}", JsonUtils.objectToJson(user));
        try {
            resVo.setState(user.getState());
            logger.info("login resVo is {}", resVo.getState());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        logger.info("login resVo is {}", JsonUtils.objectToJson(resVo));
        resVo.setUserid(user.getUserid());

        //3.校验密码
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            logger.info("password error");
            return (UserResVo) ResBaseVo.build(400, "用户名或密码错误");
        }

        String userId = user.getUserid();

        //4.判断用户异常状态
        Byte state = user.getState();

        if(state == -1){
            logger.info("login check status is {}", user.getState());
            return (UserResVo) ResBaseVo.build(403, "用户被冻结");
        }

        if(state == -3){
            logger.info("login check status is {}", user.getState());
            return (UserResVo) ResBaseVo.build(403, "用户不存在");
        }

        //5.查询图片信息
        String imgUrl = "http://47.93.200.190/images/default.png";
        String userTx = userInfoService.getUserTx(userId);
        if(!StringUtils.isEmpty(userTx)){
            imgUrl = userTx;
        }
        XslUserFileExample xslUserFileExample = new XslUserFileExample();
        XslUserFileExample.Criteria criteria2 = xslUserFileExample.createCriteria();
        criteria2.andUseridEqualTo(userId);
        criteria2.andTypeEqualTo("TX");
        List<XslUserFile> xslUserFiles = xslUserFileMapper.selectByExample(xslUserFileExample);
        if(xslUserFiles != null && xslUserFiles.size() > 0){
            XslFileExample xslFileExample = new XslFileExample();
            XslFileExample.Criteria criteria1 = xslFileExample.createCriteria();
            criteria1.andFileidEqualTo(xslUserFiles.get(0).getFileid());
            List<XslFile> xslFileList = xslFileMapper.selectByExample(xslFileExample);
            if (xslFileList != null && xslFileList.size() > 0) {
                imgUrl = xslFileList.get(0).getUrl();
            }
        }

        resVo.setTxUrl(imgUrl);

        //6.查询雇主信息和猎人信息
        XslHunterExample xslHunterExample = new XslHunterExample();
        XslMasterExample xslMasterExample = new XslMasterExample();
        XslHunterExample.Criteria criteria1 = xslHunterExample.createCriteria();
        criteria1.andUseridEqualTo(userId);
        XslMasterExample.Criteria criteria3 = xslMasterExample.createCriteria();
        criteria3.andUseridEqualTo(userId);

        List<XslHunter> xslHunters = xslHunterMapper.selectByExample(xslHunterExample);
        List<XslMaster> xslMasters = xslMasterMapper.selectByExample(xslMasterExample);

        resVo.setHunterid(xslHunters.get(0).getHunterid());
        resVo.setHunterlevel(xslHunters.get(0).getLevel());
        resVo.setMasterid(xslMasters.get(0).getMasterid());
        resVo.setMasterlevel(xslMasters.get(0).getLevel());

        //7.获取学校信息
        if(!StringUtils.isEmpty(user.getSchoolinfo())){
            XslSchoolinfoExample xslSchoolinfoExample = new XslSchoolinfoExample();
            XslSchoolinfoExample.Criteria criteria4 = xslSchoolinfoExample.createCriteria();
            criteria4.andSchoolIdEqualTo(user.getSchoolinfo());
            List<XslSchoolinfo> xslSchoolinfos = xslSchoollinfoMapper.selectByExample(xslSchoolinfoExample);
            BeanUtils.copyProperties(xslSchoolinfos.get(0), resVo);
        }


        JedisClientUtil.set(REDIS_USER_SESSION_KEY + ":" + user.getPhone(), token);

        Gson gson = GsonSingle.getGson();
        String userInfo = gson.toJson(user);
        JedisClientUtil.setEx(USER_INFO + ":" + user.getUserid(), userInfo, 300);

        logger.info("login return message is {}", JsonUtils.objectToJson(resVo));

        resVo.setStatus(200);
        return resVo;
    }

    @Override
    public ResBaseVo userLogout(UserReqVo userReqVo) {
        if(StringUtils.isEmpty(userReqVo.getPhone())){
            return ResBaseVo.build(403, "登陆状态异常");
        }
        JedisClientUtil.delete(REDIS_USER_SESSION_KEY + ":" + userReqVo.getPhone());
        return ResBaseVo.ok();
    }

    private void initUserInfo(XslUserRegister xslUserRegister, XslUser xslUser, XslHunter xslHunter, XslMaster xslMaster) {
        xslUser.setHunterid(xslHunter.getHunterid());
        xslUser.setMasterid(xslMaster.getMasterid());
        xslUser.setPhone(xslUserRegister.getPhone());
        xslUser.setState(UserStateEnum.NA.getCode());
        xslUser.setPassword(Md5Utils.digestMds(xslUserRegister.getPassword()));
        xslUser.setSex("男");
        xslUser.setName("xsl_"+xslUserRegister.getPhone());

        try {
            int result = xslUserMapper.insertSelective(xslUser);

            if (result < 1){
                throw new RuntimeException("用户信息插入失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
    }
}
