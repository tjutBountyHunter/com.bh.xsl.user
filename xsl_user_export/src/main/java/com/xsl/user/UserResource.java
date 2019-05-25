package com.xsl.user;

import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.UserReqVo;
import com.xsl.user.vo.UserRegisterReqVo;

public interface UserResource {
    /**
     * 快速注册
     * @param userRegisterReqVo
     * @return
     * @throws Exception
     */
    ResBaseVo quickCreateUser(UserRegisterReqVo userRegisterReqVo);

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
