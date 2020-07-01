package com.bupt.battery.service.impl;

import com.bupt.battery.entity.MonitorResultDO;
import com.bupt.battery.repository.BaseRepository;
import com.bupt.battery.repository.IMonitorResultDORepository;
import com.bupt.battery.service.IModelMonitorDOService;
import com.bupt.battery.service.IMonitorResultDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MonitorResultDOServiceImpl extends BaseServiceImpl<MonitorResultDO,Long> implements IMonitorResultDOService {
    @Autowired
    private IMonitorResultDORepository monitorResultDORepository;
    @Override
    public List<MonitorResultDO> findAllByVehicleIdAndPortIdAndIsRead(String vehicleId, int portId, int isRead) {
        return monitorResultDORepository.findAllByVehicleIdAndPortIdAndIsRead(vehicleId, portId, isRead);
    }
    @Override
    public List<MonitorResultDO> findAllByVehicleIdAndPortIdAndModelId(String vehicleId, int portId, int modelId) {
        return monitorResultDORepository.findAllByVehicleIdAndPortIdAndModelId(vehicleId, portId, modelId);
    }
    @Override
    public List<MonitorResultDO> findAllByVehicleIdAndPortIdAndModelIdAndIsRead(String vehicleId, int portId, int modelId, int isRead) {
        return monitorResultDORepository.findAllByVehicleIdAndPortIdAndModelIdAndIsRead(vehicleId, portId, modelId, isRead);
    }

    @Override
    public List<MonitorResultDO> findAllByDataTimeBetween(Date start_time, Date end_time) {
        return monitorResultDORepository.findAllByDataTimeBetween(start_time, end_time);
    }
}
