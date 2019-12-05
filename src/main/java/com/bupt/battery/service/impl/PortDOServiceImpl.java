package com.bupt.battery.service.impl;

import com.bupt.battery.entity.PortDO;
import com.bupt.battery.repository.IPortDORepository;
import com.bupt.battery.service.IPortDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortDOServiceImpl extends BaseServiceImpl<PortDO,Long> implements IPortDOService {
    @Autowired
    private IPortDORepository portDORepository;

    @Override
    public PortDO findByName(String portName) {
        return portDORepository.findPortDOByPortName(portName);
    }

    @Override
    public PortDO findByNameAndStatus(String portName, Integer status) {
        return portDORepository.findPortDOByPortNameAndAndStatus(portName,status);
    }
}
