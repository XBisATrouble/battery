package com.bupt.battery.service;

import com.bupt.battery.entity.ModelDO;
import com.bupt.battery.entity.PortDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.sound.sampled.Port;
import java.util.List;

public interface IPortDOService extends BaseService<PortDO,Long> {
    List<PortDO> findByNameAndStatus(String portName,Integer status);
    Page<PortDO> findPortPage(Pageable pageable);
}
