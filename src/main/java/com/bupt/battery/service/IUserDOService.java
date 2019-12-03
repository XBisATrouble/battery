package com.bupt.battery.service;

import com.bupt.battery.entity.PortDO;
import com.bupt.battery.entity.UserDO;
import org.apache.catalina.User;

public interface IUserDOService extends BaseService<UserDO,Long> {
    UserDO findUserDOByUsername(String username);
}
