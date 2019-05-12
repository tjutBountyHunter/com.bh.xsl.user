package com.xsl.user;

import vo.ResBaseVo;

public interface VerifyCodeResource {
	/**
	 * 传递验证码
	 *
	 * @param phone
	 * @return
	 */
	ResBaseVo sendMessageCode(String phone);
	/**
	 * 检验手机验证码
	 * @param phone
	 * @return
	 */
	ResBaseVo checkCode(String phone, String code);
}
