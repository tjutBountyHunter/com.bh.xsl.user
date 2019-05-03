package vo;

import java.util.List;

public class UserAccReqVo extends RequestBaseVo {
	private String userid;

	private String sno;

	private String major;

	private String college;

	private String school;

	private String sex;

	private List<TagVo> tagVos;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<TagVo> getTagVos() {
		return tagVos;
	}

	public void setTagVos(List<TagVo> tagVos) {
		this.tagVos = tagVos;
	}
}
