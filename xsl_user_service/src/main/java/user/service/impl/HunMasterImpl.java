package user.service.impl;

import example.XslHunterExample;
import example.XslMasterExample;
import mapper.XslHunterLevelMapper;
import mapper.XslHunterMapper;
import mapper.XslMasterLevelMapper;
import mapper.XslMasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslHunter;
import pojo.XslHunterLevel;
import pojo.XslMaster;
import pojo.XslMasterLevel;
import user.service.HunMaster;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 猎人初始化
 */
@Service
public class HunMasterImpl implements HunMaster {
    @Autowired
    private XslHunterLevelMapper xslHunterLevelMapper;
    @Autowired
    private XslMasterLevelMapper xslMasterLevelMapper;
    @Autowired
    private XslHunterMapper xslHunterMapper;
    @Autowired
    private XslMasterMapper xslMasterMapper;
//    @Autowired
//    private UserResource userService;
    @Override
    public Map<String, Integer> insertPeople(Integer userId) {
        XslHunter xslHunter = new XslHunter();
        XslMaster xslMaster = new XslMaster();
        xslHunter.setLevel((short) 1);
        xslHunter.setTaskaccnum(0);
        xslHunter.setTaskfailnum(0);
        xslHunter.setEmpirical(0);
        xslHunter.setCredit((short) 100);
        xslHunter.setLasttime(new Date());
        xslHunter.setDescr("");
        xslMaster.setLevel((short) 1);
        xslMaster.setTaskaccnum(0);
        xslMaster.setTaskrevokenum(0);
        xslMaster.setCredit((short) 100);
        xslMaster.setEmpirical(0);
        xslMaster.setLastaccdate(new Date());
        xslMaster.setDescr("");
        xslHunterMapper.insertSelective(xslHunter);
        xslMasterMapper.insertSelective(xslMaster);
        XslHunterExample xslHunterExample = new XslHunterExample();
        XslHunterExample.Criteria criteria = xslHunterExample.createCriteria();
        List<XslHunter> list = xslHunterMapper.selectByExample(xslHunterExample);
        XslMasterExample xslMasterExample = new XslMasterExample();
        XslMasterExample.Criteria criteria1 = xslMasterExample.createCriteria();
        List<XslHunter> list1 = xslHunterMapper.selectByExample(xslHunterExample);
        Map<String, Integer> map = new HashMap<>();
        map.put("masterId", list1.get(0).getId());
        map.put("hunterId", list.get(0).getId());
        return map;
    }

    @Override
    public void insertLevel() {
        XslHunterLevel xslHunterLevel = new XslHunterLevel();
        XslMasterLevel xslMasterLevel = new XslMasterLevel();
        xslHunterLevel.setName("铜牌猎人");
        xslHunterLevel.setDescr("");;
        xslHunterLevel.setCreatedate(new Date());
        xslHunterLevel.setUpdatedate(new Date());
        xslMasterLevel.setName("铜牌雇主");
        xslMasterLevel.setDescr("");
        xslMasterLevel.setCreatedate(new Date());
        xslMasterLevel.setUpdatedate(new Date());
        xslHunterLevelMapper.insert(xslHunterLevel);
        xslMasterLevelMapper.insert(xslMasterLevel);
    }
}
