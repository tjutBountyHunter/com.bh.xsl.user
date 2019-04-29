package user.service;
import pojo.XslResult;
import pojo.XslUserRegister;
import vo.UserReqVo;
public interface UserSerivice {
    /**
     * 注册
     * @param xslUserRegister
     * @return
     * @throws Exception
     */
    XslResult createUser(XslUserRegister xslUserRegister);

    /**
     * 快速注册
     * @param xslUserRegister
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
