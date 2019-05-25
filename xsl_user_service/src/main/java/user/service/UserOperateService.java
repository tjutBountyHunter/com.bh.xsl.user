package user.service;

import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.UserReqVo;
import com.xsl.user.vo.UserResVo;
import com.xsl.user.vo.UserRegisterReqVo;


public interface UserOperateService {

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
