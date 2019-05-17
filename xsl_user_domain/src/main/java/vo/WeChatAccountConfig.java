package vo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/4/20 17:06
 */
public class WeChatAccountConfig {

    /**开放平台Id*/
    private String openId;

    /**开放平台session_key*/
    private String sessionKey;

    private Byte state;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
