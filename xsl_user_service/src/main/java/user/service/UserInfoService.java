package user.service;

import pojo.*;
import vo.ResBaseVo;
import vo.TagVo;
import vo.UserReqVo;

import java.util.List;

public interface UserInfoService {
	XslUser getUserInfo(String useid);

	XslUser getUserInfoByHunterId(String hunterid);

	XslUser getUserInfoMasterId(String masterid);

	XslSchoolinfo getSchoolInfo(String schoolid);

	XslHunter getHunterInfo(String hunterid);

	XslMaster getMasterInfo(String masterid);

	XslSchool getSchoolByName(String SchoolName);

	String getUserTx(String userid);

	ResBaseVo getHMinfo(UserReqVo userReqVo);

	List<TagVo> getHunterTags(String hunterid);

	/**
	 *
	 * @return 得到用户总数
	 */
	int getUserNums();

}
