package com.xsl.user.enums;

public enum JpushTypeEnum {
	GXZD("GXZD", "高校组队"),
	XSL("XSL", "悬赏令");

	private String name;
	private String msg;

	JpushTypeEnum(String name, String msg) {
		this.name = name;
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public String getMsg() {
		return msg;
	}
}
