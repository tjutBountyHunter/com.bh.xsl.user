package resourceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.xsl.user.export.UserResource;
import vo.*;
import user.service.UserService;

@Controller
public class UserResourceImpl implements UserResource {

    @Autowired
    private UserService userService;


    @Override
    public ResBaseVo quickCreateUser(XslUserRegister xslUserRegister) {
        XslResult xslResult = userService.quickCreateUser(xslUserRegister);
        ResBaseVo resBaseVo = new ResBaseVo();
        BeanUtils.copyProperties(xslResult, resBaseVo);
        return resBaseVo;
    }

    @Override
    public ResBaseVo userLogin(UserReqVo userReqVo) {
        XslResult xslResult = userService.userLogin(userReqVo);
        ResBaseVo resBaseVo = new ResBaseVo();
        BeanUtils.copyProperties(xslResult, resBaseVo);
        return resBaseVo;
    }

    @Override
    public ResBaseVo getUserByToken(String token, String phone) {
        XslResult xslResult = userService.getUserByToken(token, phone);
        ResBaseVo resBaseVo = new ResBaseVo();
        BeanUtils.copyProperties(xslResult, resBaseVo);
        return resBaseVo;
    }

}
