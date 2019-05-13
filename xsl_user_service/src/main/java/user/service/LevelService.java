package user.service;

import vo.ResBaseVo;
import vo.UserLevelReqVo;

public interface LevelService {
	/**
	 * 雇主增加经验
	 * @param
	 * @return
	 * @throws Exception
	 */
	ResBaseVo MasterAddEmpirical(UserLevelReqVo userLevelReqVo);

	/**
	 * 猎人增加经验
	 * @param
	 * @return
	 * @throws Exception
	 */
	ResBaseVo HunterAddEmpirical(UserLevelReqVo userLevelReqVo);

	/**
	 * 增加经验
	 * @param
	 * @return
	 * @throws Exception
	 */
	ResBaseVo AddEmpirical(UserLevelReqVo userLevelReqVo);

}
