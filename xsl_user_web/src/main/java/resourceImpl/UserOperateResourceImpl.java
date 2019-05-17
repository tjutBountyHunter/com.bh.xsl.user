package resourceImpl;

import com.xsl.user.UserOperateResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import user.service.UserOperateService;
import vo.ResBaseVo;
import vo.UserReqVo;
import vo.UserResVo;
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
    public UserResVo quickCreateUser(XslUserRegister xslUserRegister) {
        try {
            return userOperateService.quickCreateUser(xslUserRegister);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public UserResVo userLogin(UserReqVo userReqVo) {
        try {
            return userOperateService.userLogin(userReqVo);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResBaseVo userLogout(UserReqVo userReqVo) {
        try {
            return userOperateService.userLogout(userReqVo);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
