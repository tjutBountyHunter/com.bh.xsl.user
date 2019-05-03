package resourceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import resource.JpushResource;
import user.service.JpushService;
import util.JedisClientUtil;
import vo.JPushReqVo;
import vo.JPushVo;
import vo.XslResult;

@Controller
public class JpushResourceImpl implements JpushResource {
	@Autowired
	private JpushService jpushService;
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;

	@Override
	public XslResult sendByPhone(JPushReqVo jPushReqVo) {
		JPushVo jPushVo = new JPushVo();
		BeanUtils.copyProperties(jPushReqVo, jPushVo);

		String phone = jPushReqVo.getPhone();
		//获取设备码
		String s = JedisClientUtil.get(REDIS_USER_SESSION_KEY + ":" + phone);
		jPushVo.setRegistrationId(s);
		//发推送
		String source = jPushReqVo.getSource();
		int i = jpushService.sendToRegistrationId(jPushVo, source);

		if(i < 1){
			return XslResult.build(500, "发送失败");
		}

		return XslResult.ok();
	}

	@Override
	public XslResult sendAll(JPushReqVo jPushReqVo) {
		JPushVo jPushVo = new JPushVo();
		BeanUtils.copyProperties(jPushReqVo, jPushVo);

		//发推送
		String source = jPushReqVo.getSource();
		int i = jpushService.sendToAll(jPushVo, source);

		if(i < 1){
			return XslResult.build(500, "发送失败");
		}

		return XslResult.ok();
	}
}
