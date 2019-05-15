package resourceImpl;

import com.xsl.user.UserOperateResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import user.service.UserOperateService;
import vo.ResBaseVo;
import vo.UserReqVo;
import vo.XslUserRegister;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 15:12
 */
@Controller
public class UserOperateResourceImpl implements UserOperateResource {

    @Autowired
    private UserOperateService userOperateService;

    @Override
    public ResBaseVo quickCreateUser(XslUserRegister xslUserRegister) {
        return userOperateService.quickCreateUser(xslUserRegister);
    }

    @Override
    public ResBaseVo userLogin(UserReqVo userReqVo) {
        return userOperateService.userLogin(userReqVo);
    }

    @Override
    public ResBaseVo userLogout(UserReqVo userReqVo) {
        return userOperateService.userLogout(userReqVo);
    }
}
