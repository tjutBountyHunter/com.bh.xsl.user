package user.service.impl;

import mapper.XslHunterMapper;
import mapper.XslMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.service.LevelService;
import vo.ResBaseVo;
import vo.UserLevelReqVo;
@Service
public class LevelServiceImpl implements LevelService {

	@Autowired
	private XslMasterMapper xslMasterMapper;
	@Autowired
	private XslHunterMapper xslHunterMapper;
	private static final Logger logger = LoggerFactory.getLogger(LevelServiceImpl.class);


	@Override
	public ResBaseVo MasterUpLevel(UserLevelReqVo userLevelReqVo) {
//		try {
//			String masterId=userLevelReqVo.getMasterId();
//			xslMasterMapper.updateLevel(masterId);
//			return ResBaseVo.build(200,"成功");
//		}
//		catch (Exception e){
//			logger.error(e.getMessage());
//
//		}
//
//        return ResBaseVo.build(500,"服务器异常");
		return  null;
	}

	@Override
	public ResBaseVo HunterUpLevel(UserLevelReqVo userLevelReqVo) {
//		try {
//			String hunterId=userLevelReqVo.getHunterId();
//			xslHunterMapper.updateLevel(hunterId);
//			return ResBaseVo.build(200,"成功");
//		}
//		catch (Exception e){
//			logger.error(e.getMessage());
//
//		}
//
//		return ResBaseVo.build(500,"服务器异常");
		return null;
	}

	@Override
	public ResBaseVo MasterDownLevel(UserLevelReqVo userLevelReqVo) {
		return null;
	}

	@Override
	public ResBaseVo HunterDownLevel(UserLevelReqVo userLevelReqVo) {
		return null;
	}

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

		}

		return ResBaseVo.build(500,"服务器异常");
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
			System.out.print(e);

		}

		return ResBaseVo.build(500,"服务器异常");	}

	@Override
	public ResBaseVo MasterSubEmpirical(UserLevelReqVo userLevelReqVo) {
		return null;
	}

	@Override
	public ResBaseVo HunterSubEmpirical(UserLevelReqVo userLevelReqVo) {
		return null;
	}
}
