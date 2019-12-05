package com.bupt.battery.service;

import com.bupt.battery.entity.ModelDO;
import com.bupt.battery.entity.PortDO;
import org.springframework.data.domain.Page;

import javax.sound.sampled.Port;
import java.util.List;

public interface IPortDOService extends BaseService<PortDO,Long> {
    PortDO findByName(String portName);
    PortDO findByNameAndStatus(String portName,Integer status);
    Page<PortDO> findPortPage();
}
