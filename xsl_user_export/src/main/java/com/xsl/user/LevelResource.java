package com.xsl.user;

import vo.ResBaseVo;
import vo.UserLevelReqVo;

public interface LevelResource {
     ResBaseVo MasterAddEmpirical(UserLevelReqVo userLevelReqVo);

     ResBaseVo HunterAddEmpirical(UserLevelReqVo userLevelReqVo);

     ResBaseVo AddEmpirical(UserLevelReqVo userLevelReqVo);

}
