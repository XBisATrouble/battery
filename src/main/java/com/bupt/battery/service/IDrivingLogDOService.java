package com.bupt.battery.service;

import com.bupt.battery.entity.DrivingLogDO;
import java.util.Date;
import java.util.List;

public interface IDrivingLogDOService extends BaseService<DrivingLogDO,Long> {
    List<DrivingLogDO> findDrivingLogDOSByVehicleIdAndTimeBetween(Integer vehicleId, Date startTime,Date endTime);
}
