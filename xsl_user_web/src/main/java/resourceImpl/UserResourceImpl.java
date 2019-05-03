package resourceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import resource.UserResource;
import vo.UserReqVo;
import vo.XslResult;
import user.service.UserService;
import vo.XslUserRegister;

@Controller
public class UserResourceImpl implements UserResource {

    @Autowired
    private UserService userService;


    @Override
    public XslResult quickCreateUser(XslUserRegister xslUserRegister) {

        return userService.quickCreateUser(xslUserRegister);
    }

    @Override
    public XslResult userLogin(UserReqVo userReqVo) {

        return userService.userLogin(userReqVo);
    }

    @Override
    public XslResult getUserByToken(String token, String phone) {

        return userService.getUserByToken(token,phone);
    }

    @Override
    public XslResult Password(String phone, String password) {

        return userService.Password(phone,password);
    }
}
