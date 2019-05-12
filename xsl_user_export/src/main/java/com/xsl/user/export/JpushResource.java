package com.xsl.user.export;

import vo.JPushReqVo;
import vo.ResBaseVo;

public interface JpushResource {

	ResBaseVo sendByPhone(JPushReqVo jPushReqVo);

	ResBaseVo sendAll(JPushReqVo jPushReqVo);

}
