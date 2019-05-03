package user.service;

import vo.JPushVo;

public interface JpushService {
	int sendToRegistrationId(JPushVo jPushVo, String source);

	int sendToAll(JPushVo jPushVo, String source);
}
