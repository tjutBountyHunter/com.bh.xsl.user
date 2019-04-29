package resourceImpl;

import resource.UserResource;
import vo.UserReqVo;
import vo.XslResult;

public class UserResourceImpl implements UserResource {

    @Override
    public XslResult quickCreateUser(UserReqVo userReqVo) {
        return null;
    }

    @Override
    public XslResult userLogin(UserReqVo userReqVo) {
        return null;
    }

    @Override
    public XslResult getUserByToken(String token, String phone) {
        return null;
    }

    @Override
    public XslResult Password(String phone, String password) {
        return null;
    }
}
