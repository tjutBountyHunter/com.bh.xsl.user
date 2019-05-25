package com.xsl.user.vo;

public class HunterListReq extends ReqBaseVo {
	private String masterId;
	private Integer size;

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
