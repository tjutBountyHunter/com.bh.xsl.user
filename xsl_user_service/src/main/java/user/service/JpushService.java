package user.service;

import vo.JPushVo;

public interface JpushService {
	int sendToRegistrationId(JPushVo jPushVo);

	int sendToAll(JPushVo jPushVo);
}
