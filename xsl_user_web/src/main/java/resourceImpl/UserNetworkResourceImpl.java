package resourceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.xsl.user.UserNetworkResource;
import user.service.UserNetworkService;
import com.xsl.user.vo.NetworkReqVo;
import com.xsl.user.vo.ResBaseVo;


@Controller
public class UserNetworkResourceImpl implements UserNetworkResource {
	@Autowired
	private UserNetworkService userNetworkService;

	@Override
	public ResBaseVo updateNetwork(NetworkReqVo networkReq) {
		return userNetworkService.updateNetwork(networkReq);
	}
}
