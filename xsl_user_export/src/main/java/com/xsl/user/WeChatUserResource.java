package com.xsl.user;

import com.xsl.user.vo.ResBaseVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 20:14
 */
public interface WeChatUserResource {

    /**
     * 获取微信用户openId和session_key
     * @param code
     * @param nickName
     * @param avatarUrl
     * @return
     */
    ResBaseVo getOpenIdAndSessionKey(String code, String nickName, String avatarUrl);

}
