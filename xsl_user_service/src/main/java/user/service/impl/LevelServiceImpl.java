package user.service.impl;

import example.XslHunterExample;
import example.XslMasterExample;
import mapper.XslHunterMapper;
import mapper.XslMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pojo.XslHunter;
import pojo.XslMaster;
import user.service.LevelService;
import util.ListUtil;
import vo.ResBaseVo;
import vo.UserLevelReqVo;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

	@Autowired
	private XslMasterMapper xslMasterMapper;
	@Autowired
	private XslHunterMapper xslHunterMapper;

	private static final Logger logger = LoggerFactory.getLogger(LevelServiceImpl.class);

	@Override
	public ResBaseVo MasterAddEmpirical(UserLevelReqVo userLevelReqVo) {
		try {
			String masterId=userLevelReqVo.getMasterId();
			xslMasterMapper.updateEmpirical(masterId);
			int emprical=xslMasterMapper.selectEmpirical(masterId);
			if(emprical==100||emprical==200||emprical==300||emprical==500){
				xslMasterMapper.updateLevel(masterId);
			}
			return ResBaseVo.build(200,"成功");
		}
		catch (Exception e){
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResBaseVo HunterAddEmpirical(UserLevelReqVo userLevelReqVo) {
		try {
			String hunterId=userLevelReqVo.getHunterId();
			xslHunterMapper.updateEmpirical(hunterId);
			int emprical=xslHunterMapper.selectEmpirical(hunterId);
			if(emprical==100||emprical==200||emprical==300||emprical==500){
				xslHunterMapper.updateLevel(hunterId);
			}
			return ResBaseVo.build(200,"成功");
		}
		catch (Exception e){
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public ResBaseVo AddEmpirical(UserLevelReqVo userLevelReqVo){
		try {
			String masterId = userLevelReqVo.getMasterId();
			String hunterId = userLevelReqVo.getHunterId();

			if(!StringUtils.isEmpty(hunterId)){
				addHunterEmpirical(hunterId);
			}

			if(!StringUtils.isEmpty(masterId)){
				addMasterEmpirical(masterId);
			}

			return ResBaseVo.ok();
		}catch (Exception e){
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	private void addMasterEmpirical(String masterId) {
		XslMasterExample xslMasterExample = new XslMasterExample();
		xslMasterExample.createCriteria().andMasteridEqualTo(masterId);
		List<XslMaster> xslMasters = xslMasterMapper.selectByExample(xslMasterExample);

		if(!ListUtil.isNotEmpty(xslMasters)){
			return;
		}

		XslMaster xslMaster = xslMasters.get(0);
		int level = xslMaster.getLevel();
		Integer empirical = xslMaster.getEmpirical();
		xslMasterExample.clear();
		xslMasterExample.createCriteria().andMasteridEqualTo(masterId).andEmpiricalEqualTo(empirical).andLevelEqualTo((short) level);
		int newExample = empirical + 20;
		xslMaster.setEmpirical(newExample);

		//升級判断
		if(upLevel(newExample, level)){
			level = level + 1;
			xslMaster.setLevel((short) level);
		}

		xslMasterMapper.updateByExampleSelective(xslMaster, xslMasterExample);
	}

	private void addHunterEmpirical(String hunterId) {
		XslHunterExample xslHunterExample = new XslHunterExample();
		xslHunterExample.createCriteria().andHunteridEqualTo(hunterId);
		List<XslHunter> xslHunters = xslHunterMapper.selectByExample(xslHunterExample);

		if(!ListUtil.isNotEmpty(xslHunters)){
			return;
		}

		XslHunter xslHunter = xslHunters.get(0);
		int level = xslHunter.getLevel();
		Integer empirical = xslHunter.getEmpirical();
		xslHunterExample.clear();
		xslHunterExample.createCriteria().andHunteridEqualTo(hunterId).andEmpiricalEqualTo(empirical).andLevelEqualTo((short) level);
		int newExample = empirical + 20;
		xslHunter.setEmpirical(newExample);

		//升級判断
		if(upLevel(newExample, level)){
			level = level + 1;
			xslHunter.setLevel((short) level);
		}

		xslHunterMapper.updateByExampleSelective(xslHunter, xslHunterExample);
	}

	private boolean upLevel(int newExample, int level) {
		if(1 == level)
			if(newExample > 100)
				return true;


		if(2 == level)
			if(newExample > 200)
				return true;


		if(3 == level)
			if(newExample > 300)
				return true;

		if(4 == level)
			if(newExample > 500)
				return true;


		if(5 == level)
			if(newExample > 800)
				return true;

		return false;

	}

}
