package com.xsl.user;

import com.xsl.user.vo.NetworkReqVo;
import com.xsl.user.vo.ResBaseVo;

public interface UserNetworkResource {
	ResBaseVo updateNetwork(NetworkReqVo networkReq);
}
