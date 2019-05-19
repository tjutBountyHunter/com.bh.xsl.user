package com.xsl.user;

import vo.*;

public interface UserInfoResouce {

	UserVo getUserInfo(String useid);

	UserVo getUserInfoByHunterId(String hunterid);

	UserVo getUserInfoMasterId(String masterid);

	SchoolinfoVo getSchoolInfo(String schoolid);

	HunterVo getHunterInfo(String hunterid);

	MasterVo getMasterInfo(String masterid);

	SchoolVo getSchoolByName(String SchoolName);

	ResBaseVo getHMinfo(UserReqVo userReqVo);

	int getUserNums();
}
