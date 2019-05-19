package user.service.impl;

import com.github.pagehelper.PageHelper;
import example.XslOrderExample;
import mapper.XslHunterMapper;
import mapper.XslOrderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslHunter;
import pojo.XslOrder;
import pojo.XslUser;
import user.service.HunterInfoService;
import user.service.UserInfoService;
import util.ListUtil;
import vo.HunterInfoVo;
import vo.HunterListReq;

import java.util.ArrayList;
import java.util.List;

@Service
public class HunterInfoServiceImpl implements HunterInfoService {
	@Autowired
	private XslOrderMapper xslOrderMapper;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private XslHunterMapper xslHunterMapper;


	@Override
	public List<String> getHunterIdByOrder(HunterListReq hunterListReq) {
		Integer size = hunterListReq.getSize();
		String masterId = hunterListReq.getMasterId();
		XslOrderExample xslOrderExample = new XslOrderExample();
		xslOrderExample.createCriteria().andSendidEqualTo(masterId).andStateBetween(0, 1);
		PageHelper.startPage(1, size);
		List<XslOrder> xslOrders = xslOrderMapper.selectByExample(xslOrderExample);
		List<String> hunterIds = new ArrayList<>();
		if(ListUtil.isNotEmpty(xslOrders)){
			xslOrders.forEach(var -> hunterIds.add(var.getReceiveid()));
		}

		return hunterIds;
	}

	@Override
	public List<String> getHunterIdByNB(HunterListReq hunterListReq) {
		List<String> ids = xslHunterMapper.selectGoodHunter();

		return ids;
	}

	@Override
	public List<HunterInfoVo> getHunterList(List<String> hunterids) {
		List<HunterInfoVo> hunterInfoVos = new ArrayList<>();

		for (String hunterId : hunterids){
			HunterInfoVo hunterInfoVo = new HunterInfoVo();
			XslUser userInfo = userInfoService.getUserInfoByHunterId(hunterId);
			BeanUtils.copyProperties(userInfo, hunterInfoVo);
			String userTx = userInfoService.getUserTx(userInfo.getUserid());
			hunterInfoVo.setTxUrl(userTx);
			XslHunter hunterInfo = userInfoService.getHunterInfo(hunterId);
			BeanUtils.copyProperties(hunterInfo, hunterInfoVo);
			hunterInfoVos.add(hunterInfoVo);
		}

		return hunterInfoVos;
	}
}
