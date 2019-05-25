package user.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import user.service.VerifyCodeService;
import util.JedisClientUtil;
import util.JsonUtils;
import util.Message;
import util.RandomNumUtil;
import com.xsl.user.vo.ResBaseVo;
import vo.XslResult;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
	@Value("${REDIS_USER_SESSION_CODE_KEY}")
	private String REDIS_USER_SESSION_CODE_KEY;
	@Value("${Login_SESSION_EXPIRE_CODE}")
	private Integer Login_SESSION_EXPIRE_CODE;

	private static final Logger logger = LoggerFactory.getLogger(VerifyCodeServiceImpl.class);

	/**
	 * 短信验证码
	 *
	 * @param phone
	 * @return
	 */
	@Override
	public ResBaseVo sendMessageCode(String phone) {
		String message;

		//1.手机号检测
		int bool = checkData(phone).getStatus();
		if (bool != 200) {
			message = "手机号码填写错误,请检查手机号码格式是否正确";
			return ResBaseVo.build(400, JsonUtils.objectToJson(message));
		}

		//2.发送短信验证码
		SendSmsResponse q = sendVerifyCode(phone);
		if (!"OK".equals(q.getCode())) {
			message = "短信验证未请求成功,请联系工作人员";
			return ResBaseVo.build(500, JsonUtils.objectToJson(message));
		}
		message = "短信验证请求成功";
		return ResBaseVo.ok(JsonUtils.objectToJson(message));

	}

	/**
	 * 检验手机验证码
	 *
	 * @param phone
	 * @return
	 */
	@Override
	public ResBaseVo checkCode(String phone, String code) {
		String num = JedisClientUtil.get(REDIS_USER_SESSION_CODE_KEY + ":" + phone);
		if (StringUtils.isEmpty(num)) {
			return ResBaseVo.build(403, "您的验证码失效");
		}

		if (!code.equals(num)) {
			return ResBaseVo.build(403, "验证码错误");
		}

		return ResBaseVo.ok("验证成功");
	}

	/**
	 * 发送短信验证码
	 * @param phone
	 * @return
	 */
	private SendSmsResponse sendVerifyCode(String phone) {
		//1.获取一个四位数的验证码
		String code = RandomNumUtil.getRandom().substring(0,4);
		//2.发送验证码
		SendSmsResponse response = Message.sendIdentifyingCode(phone, code);
		//2.1验证码发送成功添加缓存
		if ("OK".equals(response.getCode())){
			JedisClientUtil.set(REDIS_USER_SESSION_CODE_KEY+ ":" +phone, code);
			//设置session过期时间
			JedisClientUtil.expire(REDIS_USER_SESSION_CODE_KEY + ":" + phone, Login_SESSION_EXPIRE_CODE);
		}

		return response;
	}

	/**
	 * 手机格式校验
	 *
	 * @param content
	 * @return
	 */
	private XslResult checkData(String content) {
		//用户校检
		boolean b = content.matches("^[1][35678]\\d{9}");
		if (b) {
			return XslResult.ok();
		} else {
			return XslResult.build(400,"手机格式错误");
		}
	}
}
