package user.service;

import vo.ResBaseVo;
import vo.UserReqVo;
import vo.XslUserRegister;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 13:54
 */
public interface UserOperateService {

    /**
     * 快速注册
     * @param xslUserRegister
     * @return
     * @throws Exception
     */
    ResBaseVo quickCreateUser(XslUserRegister xslUserRegister);

    /**
     * 登录
     *
     * @param userReqVo
     * @return
     */
    ResBaseVo userLogin(UserReqVo userReqVo);

    /**
     * 注销
     *
     * @param userReqVo
     * @return
     */
    ResBaseVo userLogout(UserReqVo userReqVo);

}
