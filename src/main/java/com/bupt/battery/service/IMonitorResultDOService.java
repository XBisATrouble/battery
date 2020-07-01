package com.bupt.battery.service;

import com.bupt.battery.entity.MonitorResultDO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface IMonitorResultDOService extends BaseService<MonitorResultDO,Long> {
    List<MonitorResultDO> findAllByVehicleIdAndPortIdAndIsRead(String vehicleId, int portId, int isRead);
    List<MonitorResultDO> findAllByVehicleIdAndPortIdAndModelId(String vehicleId, int portId, int modelId);
    List<MonitorResultDO> findAllByVehicleIdAndPortIdAndModelIdAndIsRead(String vehicleId, int portId, int modelId, int isRead);
    List<MonitorResultDO> findAllByDataTimeBetween(Date start_time, Date end_time);
}