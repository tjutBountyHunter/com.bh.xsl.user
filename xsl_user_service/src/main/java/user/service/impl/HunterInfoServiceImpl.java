package user.service.impl;

import com.github.pagehelper.PageHelper;
import example.XslOrderExample;
import mapper.XslHunterMapper;
import mapper.XslOrderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslHunter;
import pojo.XslUser;
import user.service.HunterInfoService;
import user.service.UserInfoService;
import com.xsl.user.vo.HunterInfoVo;
import com.xsl.user.vo.HunterListReq;

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
		xslOrderExample.setDistinct(true);
		xslOrderExample.createCriteria().andSendidEqualTo(masterId).andStateBetween(2, 3);
		PageHelper.startPage(1, size);
		List<String> hunterIds = xslOrderMapper.selectHunterIdByExample(xslOrderExample);

		return hunterIds;
	}

	@Override
	public List<String> getHunterIdByNB(HunterListReq hunterListReq) {
		Integer size = hunterListReq.getSize();
		List<String> ids = xslHunterMapper.selectGoodHunter();
		ids = ids.subList(0, size);
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
			hunterInfoVo.setTagVos(userInfoService.getHunterTags(hunterId));
			hunterInfoVos.add(hunterInfoVo);
		}

		return hunterInfoVos;
	}
}
