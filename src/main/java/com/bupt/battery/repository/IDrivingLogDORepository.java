package com.bupt.battery.repository;

import com.bupt.battery.entity.DrivingLogDO;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IDrivingLogDORepository extends BaseRepository<DrivingLogDO,Long>{

    List<DrivingLogDO> findDrivingLogDOSByVehicleIdAndTimeBetween(String vehicleId, Date startTime,Date endTime);

    List<DrivingLogDO> findDrivingLogDOSByVehicleId(String vehicleId);
}
