package resource;

import vo.ResBaseVo;
import vo.UserLevelReqVo;

public interface LevelResource {
     ResBaseVo MasterAddEmpirical(UserLevelReqVo userLevelReqVo);
    ResBaseVo HunterAddEmpirical(UserLevelReqVo userLevelReqVo);

}
