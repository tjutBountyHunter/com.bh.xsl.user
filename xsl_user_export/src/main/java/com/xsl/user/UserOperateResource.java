package com.xsl.user;

import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.UserReqVo;
import com.xsl.user.vo.UserResVo;
import com.xsl.user.vo.UserRegisterReqVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 13:51
 */
public interface UserOperateResource {

    /**
     * 快速注册
     * @param userRegisterReqVo
     * @return
     * @throws Exception
     */
    UserResVo quickCreateUser(UserRegisterReqVo userRegisterReqVo);

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
