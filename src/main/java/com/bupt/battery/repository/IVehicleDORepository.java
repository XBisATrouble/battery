package com.bupt.battery.repository;

import com.bupt.battery.entity.GranularityDO;
import com.bupt.battery.entity.PortDO;
import com.bupt.battery.entity.VehicleDO;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehicleDORepository extends BaseRepository<VehicleDO,Long>, JpaSpecificationExecutor<VehicleDO> {

    List<VehicleDO> findVehicleDOSByDrivingAreaAndBatteryTypeAndOnlineDateBetween(String drivingArea,String batteryType, Date startDate,Date endDate);
    List<VehicleDO> findVehicleDOSByVehicleId(Integer vehicleId);
}
