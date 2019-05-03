package vo;

import java.io.Serializable;

public class SchoolReqVo extends RequestBaseVo {
	private Integer schoolid;
	private Integer collegeid;

	public Integer getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(Integer schoolid) {
		this.schoolid = schoolid;
	}

	public Integer getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(Integer collegeid) {
		this.collegeid = collegeid;
	}
}
