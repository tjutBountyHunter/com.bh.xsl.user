package userSourceImpl;
import com.xsl.user.service.UserSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.XslResult;
import pojo.XslUserRegister;
import  userSource.UserService;
import vo.UserReqVo;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserSerivice userSerivice;


    @Override
    public XslResult createUser(XslUserRegister xslUserRegister) {
        return userSerivice.createUser(xslUserRegister);
    }

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
