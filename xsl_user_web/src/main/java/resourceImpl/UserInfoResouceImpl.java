package resourceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pojo.*;
import com.xsl.user.UserInfoResouce;
import user.service.UserInfoService;
import vo.*;

@Controller
public class UserInfoResouceImpl implements UserInfoResouce {
	@Autowired
	private UserInfoService userInfoService;


	@Override
	public UserVo getUserInfo(String useid) {
		XslUser userInfo = userInfoService.getUserInfo(useid);
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(userInfo, userVo);
		return userVo;
	}

	@Override
	public UserVo getUserInfoByHunterId(String hunterid) {
		XslUser userInfoByHunterId = userInfoService.getUserInfoByHunterId(hunterid);
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(userInfoByHunterId, userVo);
		return userVo;
	}

	@Override
	public UserVo getUserInfoMasterId(String masterid) {
		XslUser userInfoMasterId = userInfoService.getUserInfoMasterId(masterid);
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(userInfoMasterId, userVo);
		return userVo;
	}

	@Override
	public SchoolinfoVo getSchoolInfo(String schoolid) {
		XslSchoolinfo schoolInfo = userInfoService.getSchoolInfo(schoolid);
		SchoolinfoVo schoolinfoVo = new SchoolinfoVo();
		BeanUtils.copyProperties(schoolInfo, schoolinfoVo);
		return schoolinfoVo;
	}

	@Override
	public HunterVo getHunterInfo(String hunterid) {
		XslHunter hunterInfo = userInfoService.getHunterInfo(hunterid);
		HunterVo hunterVo = new HunterVo();
		BeanUtils.copyProperties(hunterInfo, hunterVo);
		return hunterVo;
	}

	@Override
	public MasterVo getMasterInfo(String masterid) {
		XslMaster masterInfo = userInfoService.getMasterInfo(masterid);
		MasterVo masterVo = new MasterVo();
		BeanUtils.copyProperties(masterInfo, masterVo);
		return masterVo;
	}

	@Override
	public SchoolVo getSchoolByName(String schoolName) {
		XslSchool school = userInfoService.getSchoolByName(schoolName);
		SchoolVo schoolVo = new SchoolVo();
		BeanUtils.copyProperties(school, schoolVo);
		return schoolVo;
	}

	@Override
	public ResBaseVo getHMinfo(UserReqVo userReqVo) {
		return userInfoService.getHMinfo(userReqVo);
	}

	@Override
	public int getUserNums() {
		return userInfoService.getUserNums();
	}
}
