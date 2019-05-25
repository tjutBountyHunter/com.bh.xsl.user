package com.xsl.user;

import com.xsl.user.vo.ResBaseVo;
import com.xsl.user.vo.UserLevelReqVo;

public interface LevelResource {
     ResBaseVo MasterAddEmpirical(UserLevelReqVo userLevelReqVo);

     ResBaseVo HunterAddEmpirical(UserLevelReqVo userLevelReqVo);

     ResBaseVo AddEmpirical(UserLevelReqVo userLevelReqVo);

}
