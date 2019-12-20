package com.bupt.battery.service.impl;

import com.bupt.battery.entity.MonitorResultDO;
import com.bupt.battery.repository.BaseRepository;
import com.bupt.battery.repository.IMonitorResultDORepository;
import com.bupt.battery.service.IModelMonitorDOService;
import com.bupt.battery.service.IMonitorResultDOService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class MonitorResultDOServiceImpl extends BaseServiceImpl<MonitorResultDO,Long> implements IMonitorResultDOService {
    @Autowired
    private IMonitorResultDORepository monitorResultDORepository;
    @Override
    public List<MonitorResultDO> findAllByVehicleIdAndPortIdAndIsRead(int vehicleId, int portId, int isRead) {
        return monitorResultDORepository.findAllByVehicleIdAndPortIdAndIsRead(vehicleId, portId, isRead);
    }
    @Autowired
    public List<MonitorResultDO> findAllByVehicleIdAndPortIdAndModelId(int vehicleId, int portId, int modelId) {
        return monitorResultDORepository.findAllByVehicleIdAndPortIdAndModelId(vehicleId, portId, modelId);
    }
    @Autowired
    @Override
    public List<MonitorResultDO> findAllByVehicleIdAndPortIdAndModelIdAndIsRead(int vehicleId, int portId, int modelId, int isRead) {
        return monitorResultDORepository.findAllByVehicleIdAndPortIdAndModelIdAndIsRead(vehicleId, portId, modelId, isRead);
    }

    @Autowired
    @Override
    public List<MonitorResultDO> findAllByDataTimeBetween(Date start_time, Date end_time) {
        return monitorResultDORepository.findAllByDataTimeBetween(start_time, end_time);
    }
}
