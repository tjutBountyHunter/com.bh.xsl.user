package user.service.impl;

import enums.UserStateEnum;
import example.XslUserExample;
import mapper.XslFileMapper;
import mapper.XslUserMapper;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.XslFile;
import pojo.XslHunter;
import pojo.XslMaster;
import pojo.XslUser;
import user.service.InitUserInfo;
import user.service.WeChatUserService;
import util.CommonUtil;
import vo.ResBaseVo;
import vo.WeChatAccountConfig;

import java.util.Date;
import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 20:19
 */

@Service
public class WeChatUserServiceImpl implements WeChatUserService {

    @Autowired
    private XslUserMapper xslUserMapper;

    @Autowired
    private XslFileMapper xslFileMapper;

    @Autowired
    private InitUserInfo initUserInfo;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WeChatUserServiceImpl.class);;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResBaseVo getOpenIdAndSessionKey(String code, String nickName, String avatarUrl) {
        if(StringUtils.isEmpty(code)){
            return ResBaseVo.build(403,"code不能为空");
        }
        try {

            String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=wxb7715d658f00b0e1&secret=d95be64495bf3a94114bae2841a54961&js_code=" + code +"&"+
                    "grant_type=authorization_code";
            JSONObject jsonObject = CommonUtil.httpsRequest(WX_URL, "GET", null);
            String userId = (String) jsonObject.get("openid");
            if(jsonObject!=null&&!StringUtils.isEmpty(userId)){
                WeChatAccountConfig weChatAccountConfig = new WeChatAccountConfig();
                weChatAccountConfig.setOpenId(userId);
                weChatAccountConfig.setSessionKey(jsonObject.getString("session_key"));
                XslUserExample example = new XslUserExample();
                XslUserExample.Criteria criteria = example.createCriteria();
                criteria.andUseridEqualTo(userId);
                List<XslUser> list = xslUserMapper.selectByExample(example);
                if(list!=null&&list.size()>0){
                    weChatAccountConfig.setState(list.get(0).getState());
                    return ResBaseVo.build(1,"正常",weChatAccountConfig);
                }
                weChatAccountConfig.setState(UserStateEnum.NA.getCode());
                XslUser xslUser = new XslUser();
                xslUser.setUserid(userId);
                xslUser.setName(nickName);
                XslMaster xslMaster = initUserInfo.initXslMaster(xslUser);
                XslHunter xslHunter = initUserInfo.initXslHunter(xslUser);
                initXslFile(xslUser,avatarUrl);
                initUserInfo(xslUser,xslHunter,xslMaster);
                return ResBaseVo.build(1,"正常",weChatAccountConfig);
            }
        }catch (Exception e){
            LOGGER.error("服务器异常警告："+e.getMessage());
            return ResBaseVo.build(500, "服务器异常");
        }
        return ResBaseVo.build(500,"服务器异常");
    }


    private void initXslFile(XslUser xslUser,String avatarUrl){
        //初始化文件信息
        XslFile xslFile = new XslFile();
        xslFile.setFileid(xslUser.getUserid());
        xslFile.setUrl(avatarUrl);
        xslFile.setDescr("微信用户头像");
        xslFile.setCreatedate(new Date());
        try {

            int result = xslFileMapper.insertSelective(xslFile);

            if (result < 1){
                throw new RuntimeException("微信头像图片存储失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
    }

    private void initUserInfo(XslUser xslUser, XslHunter xslHunter, XslMaster xslMaster) {
        xslUser.setHunterid(xslHunter.getHunterid());
        xslUser.setMasterid(xslMaster.getMasterid());
        xslUser.setState(UserStateEnum.NA.getCode());
        xslUser.setSex("男");
        xslUser.setCreatedate(new Date());
        xslUser.setUpdatedate(new Date());
        try {
            int result = xslUserMapper.insertSelective(xslUser);

            if (result < 1){
                throw new RuntimeException("微信用户信息插入失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
    }
}
