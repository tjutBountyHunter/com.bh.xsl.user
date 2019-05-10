package user.service;

import vo.ResBaseVo;
import vo.UserLevelReqVo;

public interface LevelService {
	/**
	 * 雇主升级
	 * @param
	 * @return
	 * @throws Exception
	 */
	ResBaseVo MasterUpLevel(UserLevelReqVo userLevelReqVo);

	/**
	 * 猎人升级
	 * @param
	 * @return
	 * @throws Exception
	 */
	ResBaseVo HunterUpLevel(UserLevelReqVo userLevelReqVo);

	/**
	 * 雇主升级
	 * @param
	 * @return
	 * @throws Exception
	 */
	ResBaseVo MasterDownLevel(UserLevelReqVo userLevelReqVo);

	/**
	 * 猎人升级
	 * @param
	 * @return
	 * @throws Exception
	 */
	ResBaseVo HunterDownLevel(UserLevelReqVo userLevelReqVo);

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
	 * 雇主减少经验
	 * @param
	 * @return
	 * @throws Exception
	 */
	ResBaseVo MasterSubEmpirical(UserLevelReqVo userLevelReqVo);

	/**
	 * 猎人减少经验
	 * @param
	 * @return
	 * @throws Exception
	 */
	ResBaseVo HunterSubEmpirical(UserLevelReqVo userLevelReqVo);

}
