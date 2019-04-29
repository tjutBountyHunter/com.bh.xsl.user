package resourceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import resource.UserResource;
import vo.UserReqVo;
import vo.XslResult;
import user.service.UserSerivice;
import vo.XslUserRegister;

public class UserResourceImpl implements UserResource {

    @Autowired
    private UserSerivice userSerivice;


    @Override
    public XslResult quickCreateUser(XslUserRegister xslUserRegister) {

        return userSerivice.quickCreateUser(xslUserRegister);
    }

    @Override
    public XslResult userLogin(UserReqVo userReqVo) {

        return userSerivice.userLogin(userReqVo);
    }

    @Override
    public XslResult getUserByToken(String token, String phone) {

        return userSerivice.getUserByToken(token,phone);
    }

    @Override
    public XslResult Password(String phone, String password) {

        return userSerivice.Password(phone,password);
    }
}
