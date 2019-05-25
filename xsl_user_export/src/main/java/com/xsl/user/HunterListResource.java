package com.xsl.user;

import com.xsl.user.vo.HunterInfoVo;
import com.xsl.user.vo.HunterListReq;

import java.util.List;

public interface HunterListResource {
	List<HunterInfoVo> queryHistoryHunter(HunterListReq hunterListReq);

	List<HunterInfoVo> queryNBHunter(HunterListReq hunterListReq);
}
