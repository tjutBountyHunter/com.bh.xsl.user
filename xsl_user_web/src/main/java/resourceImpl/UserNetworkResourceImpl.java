package resourceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import resource.UserNetworkResource;
import user.service.UserNetworkService;
import vo.NetworkReqVo;
import vo.ResBaseVo;


@Controller
public class UserNetworkResourceImpl implements UserNetworkResource {
	@Autowired
	private UserNetworkService userNetworkService;

	@Override
	public ResBaseVo updateNetwork(NetworkReqVo networkReq) {
		return userNetworkService.updateNetwork(networkReq);
	}
}
