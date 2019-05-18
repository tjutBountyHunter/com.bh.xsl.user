package resourceImpl;

import com.xsl.user.HunterListResource;
import org.springframework.beans.factory.annotation.Autowired;
import user.service.HunterInfoService;
import util.ListUtil;
import vo.HunterInfoVo;
import vo.HunterListReq;

import java.util.ArrayList;
import java.util.List;

public class HunterListResourceImpl implements HunterListResource {
	@Autowired
	private HunterInfoService hunterInfoService;


	@Override
	public List<HunterInfoVo> queryHistoryHunter(HunterListReq hunterListReq) {
		List<HunterInfoVo> hunterInfoVos = new ArrayList<>();

		try {
			List<String> hunterIdByOrder = hunterInfoService.getHunterIdByOrder(hunterListReq);

			if(ListUtil.isNotEmpty(hunterIdByOrder)){
				hunterInfoVos = hunterInfoService.getHunterList(hunterIdByOrder);
			}

			return hunterInfoVos;
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}


	@Override
	public List<HunterInfoVo> queryNBHunter(HunterListReq hunterListReq) {
		List<HunterInfoVo> hunterInfoVos = new ArrayList<>();

		try {
			List<String> hunterIdByNB = hunterInfoService.getHunterIdByNB(hunterListReq);

			if(ListUtil.isNotEmpty(hunterIdByNB)){
				hunterInfoVos = hunterInfoService.getHunterList(hunterIdByNB);
			}

			return hunterInfoVos;
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}
