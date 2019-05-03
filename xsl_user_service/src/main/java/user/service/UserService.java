package user.service;
import vo.UserAccReqVo;
import vo.XslResult;
import vo.XslUserRegister;
import vo.UserReqVo;
public interface UserService {


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

    /**
     * 获取雇主和猎人信息
     * @return
     */
    XslResult getHMinfo(UserReqVo userReqVo);

    /**
     * 用户认证
     * @return
     */
    XslResult userAcc(UserAccReqVo userAccReqVo);


}
