package com.bupt.battery.service;

import com.bupt.battery.entity.ModelDO;
import com.bupt.battery.entity.PortDO;

import javax.sound.sampled.Port;

public interface IPortDOService extends BaseService<PortDO,Long> {
    PortDO findByName(String portName);
    PortDO findByNameAndStatus(String portName,Integer status);
}
