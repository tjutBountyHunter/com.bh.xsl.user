package resource;

import vo.XslResult;

public interface UserInfoResouce {

	XslResult getUserInfo(String useid);

	XslResult getUserInfoByHunterId(String hunterid);

	XslResult getUserInfoMasterId(String masterid);

	XslResult getSchoolInfo(String schoolid);

	XslResult getHunterInfo(String hunterid);

	XslResult getMasterInfo(String masterid);

	XslResult getSchoolByName(String SchoolName);


}
