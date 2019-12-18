package com.bupt.battery.service.impl;

import com.bupt.battery.entity.MonitorResultDO;
import com.bupt.battery.repository.BaseRepository;
import com.bupt.battery.repository.IMonitorResultDORepository;
import com.bupt.battery.service.IModelMonitorDOService;
import com.bupt.battery.service.IMonitorResultDOService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MonitorResultDOServiceImpl extends BaseServiceImpl<MonitorResultDO,Long> implements IMonitorResultDOService {
    @Autowired
    private IMonitorResultDORepository monitorResultDORepository;
    @Override
    public List<MonitorResultDO> findAllByVehicleIdAndPortIdAndIsRead(Long vehicleId, Long portId, Long isRead) {
        return monitorResultDORepository.findAllByVehicleIdAndPortIdAndIsRead(vehicleId, portId, isRead);
    }
    @Override
    public MonitorResultDO update(MonitorResultDO resultDO) {
        return monitorResultDORepository.update(resultDO);
    }
}
