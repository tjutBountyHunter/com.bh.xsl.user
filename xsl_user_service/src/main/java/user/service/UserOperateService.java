package user.service;

import vo.ResBaseVo;
import vo.UserReqVo;
import vo.UserResVo;
import vo.XslUserRegister;


public interface UserOperateService {

    /**
     * 快速注册
     * @param xslUserRegister
     * @return
     * @throws Exception
     */
    UserResVo quickCreateUser(XslUserRegister xslUserRegister);

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
