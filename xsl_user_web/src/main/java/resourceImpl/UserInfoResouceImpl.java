package resourceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import resource.UserInfoResouce;
import user.service.UserInfoService;
import vo.*;

@Controller
public class UserInfoResouceImpl implements UserInfoResouce {
	@Autowired
	private UserInfoService userInfoService;


	@Override
	public XslUser getUserInfo(String useid) {
		XslUser userInfo = userInfoService.getUserInfo(useid);
		return userInfo;
	}

	@Override
	public XslUser getUserInfoByHunterId(String hunterid) {
		XslUser userInfoByHunterId = userInfoService.getUserInfoByHunterId(hunterid);
		return userInfoByHunterId;
	}

	@Override
	public XslUser getUserInfoMasterId(String masterid) {
		XslUser userInfoMasterId = userInfoService.getUserInfoMasterId(masterid);
		return userInfoMasterId;
	}

	@Override
	public XslSchoolinfo getSchoolInfo(String schoolid) {
		XslSchoolinfo schoolInfo = userInfoService.getSchoolInfo(schoolid);
		return schoolInfo;
	}

	@Override
	public XslHunter getHunterInfo(String hunterid) {
		XslHunter hunterInfo = userInfoService.getHunterInfo(hunterid);
		return hunterInfo;
	}

	@Override
	public XslMaster getMasterInfo(String masterid) {
		XslMaster masterInfo = userInfoService.getMasterInfo(masterid);
		return masterInfo;
	}

	@Override
	public XslSchool getSchoolByName(String schoolName) {
		XslSchool school = userInfoService.getSchoolByName(schoolName);
		return school;
	}
}
