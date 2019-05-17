package com.xsl.user;

import vo.ResBaseVo;
import vo.UserReqVo;
import vo.UserResVo;
import vo.XslUserRegister;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 13:51
 */
public interface UserOperateResource {

    /**
     * 快速注册
     * @param xslUserRegister
     * @return
     * @throws Exception
     */
    UserResVo quickCreateUser(XslUserRegister xslUserRegister);

    /**
     * 登录
     *
     * @param userReqVo
     * @return
     */
    UserResVo userLogin(UserReqVo userReqVo);

    /**
     * 注销
     *
     * @param userReqVo
     * @return
     */
    ResBaseVo userLogout(UserReqVo userReqVo);

}
