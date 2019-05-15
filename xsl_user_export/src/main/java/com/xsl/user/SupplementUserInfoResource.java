package com.xsl.user;

import vo.ResBaseVo;
import vo.UserAccReqVo;
import vo.UserReqVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 14:15
 */
public interface SupplementUserInfoResource {

    /**
     * 保存用户信息
     *
     * @param userReqVo
     * @return
     */
    ResBaseVo saveUserInfo(UserReqVo userReqVo);

    /**
     * 用户认证
     * @return
     */
    ResBaseVo userAcc(UserAccReqVo userAccReqVo);

}
