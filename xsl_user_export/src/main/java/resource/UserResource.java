package resource;


import vo.UserReqVo;
import vo.XslResult;
import vo.XslUserRegister;

public interface UserResource {
    /**
     * 快速注册
     * @param XslUserRegister
     * @return
     * @throws Exception
     */
    XslResult quickCreateUser(XslUserRegister xslUserRegister);

    /**
     * 登录
     *
     * @param userReqVo
     * @return
     */
    XslResult userLogin(UserReqVo userReqVo);

    /**
     * 检查Token被更换
     *
     * @param token
     * @return
     */
    XslResult getUserByToken(String token, String phone);

    /**
     * 忘记密码
     * @return
     */
    XslResult Password(String phone, String password);
}
