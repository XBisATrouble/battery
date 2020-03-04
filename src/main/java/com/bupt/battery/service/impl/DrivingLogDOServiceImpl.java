package com.bupt.battery.service.impl;

import com.bupt.battery.entity.DrivingLogDO;
import com.bupt.battery.repository.IDrivingLogDORepository;
import com.bupt.battery.service.IDrivingLogDOService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrivingLogDOServiceImpl extends BaseServiceImpl<DrivingLogDO,Long> implements IDrivingLogDOService {
    @Autowired
    IDrivingLogDORepository drivingLogDORepository;
    @Override
    public List<DrivingLogDO> findDrivingLogDOSByVehicleIdAndTimeBetween(Integer vehicleId, Date startTime, Date endTime) {
        return drivingLogDORepository.findDrivingLogDOSByVehicleIdAndTimeBetween(vehicleId,startTime,endTime);
    }

    @Override
    public List<DrivingLogDO> findDrivingLogDOSByVehicleId(Integer vehicleId) {
        return drivingLogDORepository.findDrivingLogDOSByVehicleId(vehicleId);
    }
}
