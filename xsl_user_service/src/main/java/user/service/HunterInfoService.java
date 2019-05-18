package user.service;

import vo.HunterInfoVo;
import vo.HunterListReq;

import java.util.List;

public interface HunterInfoService {

	List<String> getHunterIdByOrder(HunterListReq hunterListReq);

	List<String> getHunterIdByNB(HunterListReq hunterListReq);

	List<HunterInfoVo> getHunterList(List<String> hunterids);
}
