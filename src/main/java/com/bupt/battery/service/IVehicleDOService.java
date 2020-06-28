package com.bupt.battery.service;

import com.bupt.battery.entity.PortDO;
import com.bupt.battery.entity.VehicleDO;
import com.bupt.battery.form.GranularityForm;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;

public interface IVehicleDOService extends BaseService<VehicleDO,Long> {
    List<VehicleDO> findVehicleDOS(String drivingArea,String batteryType, Date startDate,Date endDate);

    Page<VehicleDO> findVehicleDOPage(GranularityForm form);
    List<VehicleDO> findVehicleDOList(GranularityForm form);

    List<VehicleDO> findVehicleDOSByVehicleId(String vehicleId);
}
