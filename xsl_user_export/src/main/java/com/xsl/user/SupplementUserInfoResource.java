package com.xsl.user;

import com.xsl.user.vo.FileUploadReqVo;
import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.UserAccReqVo;
import com.xsl.user.vo.UserReqVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 14:15
 */
public interface SupplementUserInfoResource {

    /**
     * 保存用户信息
     *
     * @param userReqVo
     * @return
     */
    ResBaseVo saveUserInfo(UserReqVo userReqVo);

    /**
     * 用户认证
     * @return
     */
    ResBaseVo userAcc(UserAccReqVo userAccReqVo);


    /**
     * 上传用户头像
     * @return
     */
    ResBaseVo upLoadUserTx(FileUploadReqVo uploadFile, String userid);

}
