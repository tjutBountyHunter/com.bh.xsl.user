package resourceImpl;

import com.xsl.user.SupplementUserInfoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import user.service.SupplementUserInfoService;
import vo.*;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 15:14
 */
@Controller
public class SupplementUserInfoResourceImpl implements SupplementUserInfoResource {

    @Autowired
    private SupplementUserInfoService supplementUserInfoService;

    @Override
    public ResBaseVo saveUserInfo(UserReqVo userReqVo) {
        return supplementUserInfoService.saveUserInfo(userReqVo);
    }

    @Override
    public ResBaseVo userAcc(UserAccReqVo userAccReqVo) {
        return supplementUserInfoService.userAcc(userAccReqVo);
    }
}
