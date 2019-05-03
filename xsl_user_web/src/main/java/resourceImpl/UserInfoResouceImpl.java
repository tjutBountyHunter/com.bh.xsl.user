package resourceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pojo.*;
import resource.UserInfoResouce;
import user.service.UserInfoService;
import vo.XslResult;

@Controller
public class UserInfoResouceImpl implements UserInfoResouce {
	@Autowired
	private UserInfoService userInfoService;


	@Override
	public XslResult getUserInfo(String useid) {
		XslUser userInfo = userInfoService.getUserInfo(useid);
		return XslResult.ok(userInfo);
	}

	@Override
	public XslResult getUserInfoByHunterId(String hunterid) {
		XslUser userInfoByHunterId = userInfoService.getUserInfoByHunterId(hunterid);
		return XslResult.ok(userInfoByHunterId);
	}

	@Override
	public XslResult getUserInfoMasterId(String masterid) {
		XslUser userInfoMasterId = userInfoService.getUserInfoMasterId(masterid);
		return XslResult.ok(userInfoMasterId);
	}

	@Override
	public XslResult getSchoolInfo(String schoolid) {
		XslSchoolinfo schoolInfo = userInfoService.getSchoolInfo(schoolid);
		return XslResult.ok(schoolInfo);
	}

	@Override
	public XslResult getHunterInfo(String hunterid) {
		XslHunter hunterInfo = userInfoService.getHunterInfo(hunterid);
		return XslResult.ok(hunterInfo);
	}

	@Override
	public XslResult getMasterInfo(String masterid) {
		XslMaster masterInfo = userInfoService.getMasterInfo(masterid);
		return XslResult.ok(masterInfo);
	}

	@Override
	public XslResult getSchoolByName(String schoolName) {
		XslSchool school = userInfoService.getSchoolByName(schoolName);
		return XslResult.ok(school);
	}
}
