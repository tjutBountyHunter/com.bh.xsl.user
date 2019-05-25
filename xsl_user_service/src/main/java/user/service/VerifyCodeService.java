package user.service;

import com.xsl.user.vo.ResBaseVo;

public interface VerifyCodeService {
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
