package com.xsl.user;

import com.xsl.user.vo.FileUploadReqVo;
import com.xsl.user.vo.FileVo;

public interface FileOperateResource {

	/**
	 * 上传图片
	 *
	 * @param fileUploadReqVo
	 * @return
	 */
	FileVo fileUpload(FileUploadReqVo fileUploadReqVo);
}
