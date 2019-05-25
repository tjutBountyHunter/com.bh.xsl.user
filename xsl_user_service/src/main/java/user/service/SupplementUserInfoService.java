package user.service;

import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.UserAccReqVo;
import com.xsl.user.vo.UserReqVo;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 14:18
 */
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

}
