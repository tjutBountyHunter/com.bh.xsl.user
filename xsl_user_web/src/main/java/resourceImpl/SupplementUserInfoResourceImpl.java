package resourceImpl;

import com.xsl.user.SupplementUserInfoResource;
import com.xsl.user.vo.FileUploadReqVo;
import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.UserAccReqVo;
import com.xsl.user.vo.UserReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import user.service.SupplementUserInfoService;

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
    public com.xsl.user.vo.ResBaseVo saveUserInfo(UserReqVo userReqVo) {
        return supplementUserInfoService.saveUserInfo(userReqVo);
    }

    @Override
    public ResBaseVo userAcc(UserAccReqVo userAccReqVo) {
        return supplementUserInfoService.userAcc(userAccReqVo);
    }

    @Override
    public ResBaseVo upLoadUserTx(FileUploadReqVo uploadFile, String userid) {
        if(StringUtils.isEmpty(userid)){
            return ResBaseVo.build(400, "userid有误");
        }

        return supplementUserInfoService.upLoadUserTx(uploadFile, userid);
    }

}
