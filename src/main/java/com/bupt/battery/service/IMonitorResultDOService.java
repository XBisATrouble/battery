package com.bupt.battery.service;

import com.bupt.battery.entity.MonitorResultDO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IMonitorResultDOService extends BaseService<MonitorResultDO,Long> {
    List<MonitorResultDO> findAllByVehicleIdAndPortIdAndIsRead(Long vehicleId, Long portId, Long isRead);
    MonitorResultDO update(MonitorResultDO resultDO);
}