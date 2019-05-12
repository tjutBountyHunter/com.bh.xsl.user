package com.xsl.user.export;

import vo.ResBaseVo;
import vo.UserLevelReqVo;

public interface LevelResource {
     ResBaseVo MasterAddEmpirical(UserLevelReqVo userLevelReqVo);

     ResBaseVo HunterAddEmpirical(UserLevelReqVo userLevelReqVo);

}
