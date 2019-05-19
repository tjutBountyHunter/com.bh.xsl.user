package com.xsl.user;

import vo.HunterInfoVo;
import vo.HunterListReq;

import java.util.List;

public interface HunterListResource {
	List<HunterInfoVo> queryHistoryHunter(HunterListReq hunterListReq);

	List<HunterInfoVo> queryNBHunter(HunterListReq hunterListReq);
}
