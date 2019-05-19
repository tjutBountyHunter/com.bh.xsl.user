package vo;

import java.util.List;

public class HunterInfoVo extends ResBaseVo {
	private String hunterid;

	private String userid;

	private Short level;

	private Integer empirical;

	private Integer taskaccnum;

	private Integer taskfailnum;

	private Short credit;

	private String name;

	private String sex;

	private String phone;

	private String email;

	private String txUrl;

	private List<TagVo> tagVos;

	public String getHunterid() {
		return hunterid;
	}

	public void setHunterid(String hunterid) {
		this.hunterid = hunterid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public Integer getEmpirical() {
		return empirical;
	}

	public void setEmpirical(Integer empirical) {
		this.empirical = empirical;
	}

	public Integer getTaskaccnum() {
		return taskaccnum;
	}

	public void setTaskaccnum(Integer taskaccnum) {
		this.taskaccnum = taskaccnum;
	}

	public Integer getTaskfailnum() {
		return taskfailnum;
	}

	public void setTaskfailnum(Integer taskfailnum) {
		this.taskfailnum = taskfailnum;
	}

	public Short getCredit() {
		return credit;
	}

	public void setCredit(Short credit) {
		this.credit = credit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTxUrl() {
		return txUrl;
	}

	public void setTxUrl(String txUrl) {
		this.txUrl = txUrl;
	}

	public List<TagVo> getTagVos() {
		return tagVos;
	}

	public void setTagVos(List<TagVo> tagVos) {
		this.tagVos = tagVos;
	}
}
