package user.service;

import com.xsl.user.vo.NetworkReqVo;
import com.xsl.user.vo.ResBaseVo;

public interface UserNetworkService {
	ResBaseVo updateNetwork(NetworkReqVo networkReq);
}
