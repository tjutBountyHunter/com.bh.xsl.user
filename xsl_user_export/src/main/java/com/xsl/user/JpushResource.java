package com.xsl.user;

import com.xsl.user.vo.JPushReqVo;
import com.xsl.user.vo.ResBaseVo;

public interface JpushResource {

	ResBaseVo sendByPhone(JPushReqVo jPushReqVo);

	ResBaseVo sendAll(JPushReqVo jPushReqVo);

}
