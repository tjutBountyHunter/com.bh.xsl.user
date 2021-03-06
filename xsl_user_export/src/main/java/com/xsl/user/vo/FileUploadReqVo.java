package com.xsl.user.vo;

import java.io.InputStream;

public class FileUploadReqVo extends ReqBaseVo{
	private String name;
	private String originalFilename;
	private String contentType;
	private long size;
	private byte[] bytes;
	private byte[] inputStream;
	private String userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public byte[] getInputStream() {
		return inputStream;
	}

	public void setInputStream(byte[] inputStream) {
		this.inputStream = inputStream;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
