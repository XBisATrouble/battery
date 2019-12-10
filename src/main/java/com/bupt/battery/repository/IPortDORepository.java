package com.bupt.battery.repository;

import com.bupt.battery.entity.PortDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPortDORepository extends BaseRepository<PortDO,Long>{
    PortDO findPortDOByPortName(String portName);
    // PortDO findPortDOByPortNameAndAndStatus(String portName,Integer status);
    List<PortDO> findPortDOByPortNameAndAndStatus(String portName, Integer status);
    List<PortDO> findPortDOSByStatus(Integer staus);
}
