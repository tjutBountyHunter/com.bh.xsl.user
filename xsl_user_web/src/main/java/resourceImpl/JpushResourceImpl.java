package resourceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import com.xsl.user.JpushResource;
import user.service.JpushService;
import util.JedisClientUtil;
import com.xsl.user.vo.JPushReqVo;
import vo.JPushVo;
import com.xsl.user.vo.ResBaseVo;

@Controller
public class JpushResourceImpl implements JpushResource {
	@Autowired
	private JpushService jpushService;
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;

	@Override
	public ResBaseVo sendByPhone(JPushReqVo jPushReqVo) {
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
			return ResBaseVo.build(500, "发送失败");
		}

		return ResBaseVo.ok();
	}

	@Override
	public ResBaseVo sendAll(JPushReqVo jPushReqVo) {
		JPushVo jPushVo = new JPushVo();
		BeanUtils.copyProperties(jPushReqVo, jPushVo);

		//发推送
		String source = jPushReqVo.getSource();
		int i = jpushService.sendToAll(jPushVo, source);

		if(i < 1){
			return ResBaseVo.build(500, "发送失败");
		}

		return ResBaseVo.ok();
	}
}
