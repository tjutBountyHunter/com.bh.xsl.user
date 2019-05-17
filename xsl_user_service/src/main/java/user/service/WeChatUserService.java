package user.service;

import vo.ResBaseVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 20:17
 */
public interface WeChatUserService {

    /**
     * 获取微信用户openId和session_key
     * @param code
     * @param nickName
     * @param avatarUrl
     * @return
     */
    ResBaseVo getOpenIdAndSessionKey(String code, String nickName, String avatarUrl);

}
