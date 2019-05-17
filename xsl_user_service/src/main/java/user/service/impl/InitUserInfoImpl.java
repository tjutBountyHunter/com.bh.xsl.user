package user.service.impl;

import mapper.XslHunterMapper;
import mapper.XslMasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.XslHunter;
import pojo.XslMaster;
import pojo.XslUser;
import user.service.InitUserInfo;

import java.util.Date;
import java.util.UUID;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 21:51
 */
@Service
public class InitUserInfoImpl implements InitUserInfo {

    @Autowired
    private XslMasterMapper xslMasterMapper;

    @Autowired
    private XslHunterMapper xslHunterMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public XslMaster initXslMaster(XslUser xslUser) {
        //初始化雇主信息
        XslMaster xslMaster = new XslMaster();
        xslMaster.setUserid(xslUser.getUserid());
        xslMaster.setMasterid(UUID.randomUUID().toString());
        xslMaster.setLevel((short) 1);
        xslMaster.setDescr("新人雇主");
        xslMaster.setLastaccdate(new Date());
        try {
            int result = xslMasterMapper.insertSelective(xslMaster);

            if (result < 1){
                throw new RuntimeException("雇主信息插入失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
        return xslMaster;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public XslHunter initXslHunter(XslUser xslUser) {
        //初始化猎人信息
        XslHunter xslHunter = new XslHunter();
        xslHunter.setUserid(xslUser.getUserid());
        xslHunter.setHunterid(UUID.randomUUID().toString());
        xslHunter.setLevel((short) 1);
        xslHunter.setDescr("新手猎人");
        xslHunter.setLasttime(new Date());
        try {
            int result = xslHunterMapper.insertSelective(xslHunter);

            if (result < 1) {
                throw new RuntimeException("猎人信息插入失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
        return xslHunter;
    }
}
