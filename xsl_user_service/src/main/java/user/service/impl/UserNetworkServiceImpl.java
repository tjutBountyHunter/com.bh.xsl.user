package user.service.impl;

import example.XslNetworkExample;
import mapper.XslNetworkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslNetwork;
import user.service.UserInfoService;
import user.service.UserNetworkService;
import util.ListUtil;
import com.xsl.user.vo.NetworkReqVo;
import com.xsl.user.vo.ResBaseVo;

import java.util.List;

@Service
public class UserNetworkServiceImpl implements UserNetworkService {

	@Autowired
	private XslNetworkMapper xslNetworkMapper;

	@Autowired
	private UserInfoService userInfoService;

	@Override
	public ResBaseVo updateNetwork(NetworkReqVo networkReq) {
		String hunterId = networkReq.getHunterId();
		String masterId = networkReq.getMasterId();

		try {
			XslNetworkExample xslNetworkExample = new XslNetworkExample();
			xslNetworkExample.createCriteria().andAidEqualTo(hunterId).andBidEqualTo(masterId);
			List<XslNetwork> xslNetworkA = xslNetworkMapper.selectByExample(xslNetworkExample);

			if(ListUtil.isNotEmpty(xslNetworkA)){
				updateNum(xslNetworkExample, xslNetworkA);
				return ResBaseVo.ok();
			}

			xslNetworkExample.clear();
			xslNetworkExample.createCriteria().andAidEqualTo(masterId).andBidEqualTo(hunterId);
			List<XslNetwork> xslNetworkB = xslNetworkMapper.selectByExample(xslNetworkExample);

			if (ListUtil.isNotEmpty(xslNetworkB)){
				updateNum(xslNetworkExample, xslNetworkB);
				return ResBaseVo.ok();
			}
			XslNetwork xslNetwork = new XslNetwork();
			xslNetwork.setAid(masterId);
			xslNetwork.setAphone(userInfoService.getUserInfoMasterId(masterId).getPhone());
			xslNetwork.setBid(hunterId);
			xslNetwork.setBphone(userInfoService.getUserInfoByHunterId(hunterId).getPhone());
			xslNetwork.setBid(hunterId);
			xslNetwork.setNum(1);
			xslNetworkMapper.insertSelective(xslNetwork);
		}catch (Exception e){
			e.printStackTrace();
		}

		return ResBaseVo.ok();
	}

	private void updateNum(XslNetworkExample xslNetworkExample, List<XslNetwork> xslNetworkA) {
		XslNetwork xslNetwork = xslNetworkA.get(0);
		xslNetwork.setNum(xslNetwork.getNum() + 1);
		xslNetworkMapper.updateByExampleSelective(xslNetwork, xslNetworkExample);
	}
}
