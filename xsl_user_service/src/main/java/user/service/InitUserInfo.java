package user.service;

import pojo.XslHunter;
import pojo.XslMaster;
import pojo.XslUser;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 21:46
 */

public interface InitUserInfo {

    /**
     * 初始化雇主信息
     * @param xslUser
     * @return
     */
    XslMaster initXslMaster(XslUser xslUser);

    /**
     * 初始化猎人信息
     * @param xslUser
     * @return
     */
    XslHunter initXslHunter(XslUser xslUser);

}
