package user.service;

import vo.ResBaseVo;
import vo.UserAccReqVo;
import vo.UserReqVo;

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
