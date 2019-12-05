package com.bupt.battery.repository;

import com.bupt.battery.entity.PortDO;
import org.springframework.stereotype.Repository;

@Repository
public interface IPortDORepository extends BaseRepository<PortDO,Long>{
    PortDO findPortDOByPortName(String portName);
    PortDO findPortDOByPortNameAndAndStatus(String portName,Integer status);
}
