package com.bupt.battery.service.impl;

import com.bupt.battery.entity.PortDO;
import com.bupt.battery.entity.UserDO;
import com.bupt.battery.repository.IUserDORepository;
import com.bupt.battery.service.IPortDOService;
import com.bupt.battery.service.IUserDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDOServiceImpl extends BaseServiceImpl<UserDO,Long> implements IUserDOService {
    @Autowired
    private IUserDORepository userDORepository;
    @Override
    public UserDO findUserDOByUsername(String username) {
        return userDORepository.findUserDOByUsername(username);
    }
}
