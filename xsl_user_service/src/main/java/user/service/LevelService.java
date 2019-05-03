package user.service;

import vo.UserLevelReqVo;

public interface LevelService {
	/**
	 * 雇主升级
	 * @param
	 * @return
	 * @throws Exception
	 */
	int MasterUpLevel(UserLevelReqVo userLevelReqVo);

	/**
	 * 猎人升级
	 * @param
	 * @return
	 * @throws Exception
	 */
	int HunterUpLevel(UserLevelReqVo userLevelReqVo);

	/**
	 * 雇主升级
	 * @param
	 * @return
	 * @throws Exception
	 */
	int MasterDownLevel(UserLevelReqVo userLevelReqVo);

	/**
	 * 猎人升级
	 * @param
	 * @return
	 * @throws Exception
	 */
	int HunterDownLevel(UserLevelReqVo userLevelReqVo);

	/**
	 * 雇主增加经验
	 * @param
	 * @return
	 * @throws Exception
	 */
	int MasterAddEmpirical(UserLevelReqVo userLevelReqVo);

	/**
	 * 猎人增加经验
	 * @param
	 * @return
	 * @throws Exception
	 */
	int HunterAddEmpirical(UserLevelReqVo userLevelReqVo);

	/**
	 * 雇主减少经验
	 * @param
	 * @return
	 * @throws Exception
	 */
	int MasterSubEmpirical(UserLevelReqVo userLevelReqVo);

	/**
	 * 猎人减少经验
	 * @param
	 * @return
	 * @throws Exception
	 */
	int HunterSubEmpirical(UserLevelReqVo userLevelReqVo);

}
