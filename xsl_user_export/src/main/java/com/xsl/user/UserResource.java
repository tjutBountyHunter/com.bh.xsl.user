package com.xsl.user;


import vo.ResBaseVo;
import vo.UserReqVo;
import vo.XslUserRegister;

public interface UserResource {
    /**
     * 快速注册
     * @param xslUserRegister
     * @return
     * @throws Exception
     */
    ResBaseVo quickCreateUser(XslUserRegister xslUserRegister);

    /**
     * 登录
     *
     * @param userReqVo
     * @return
     */
    ResBaseVo userLogin(UserReqVo userReqVo);

    /**
     * 检查Token被更换
     *
     * @param token
     * @return
     */
    ResBaseVo getUserByToken(String token, String phone);

}
