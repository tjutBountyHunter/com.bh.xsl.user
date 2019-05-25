package user.service;

import com.xsl.user.vo.FileUploadReqVo;
import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.UserAccReqVo;
import com.xsl.user.vo.UserReqVo;


public interface SupplementUserInfoService {

    /**
     * 保存用户信息
     * @param userReqVo
     * @return
     */
    ResBaseVo saveUserInfo(UserReqVo userReqVo);

    /**
     * 用户认证
     * @param userAccReqVo
     * @return
     */
    ResBaseVo userAcc(UserAccReqVo userAccReqVo);

    /**
     * 上传用户头像
     * @return
     */
    ResBaseVo upLoadUserTx(FileUploadReqVo uploadFile, String userid);

}
