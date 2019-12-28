package com.bupt.battery.repository;

import com.bupt.battery.entity.MonitorResultDO;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Date;
import java.util.List;

@Repository
public interface IMonitorResultDORepository extends BaseRepository<MonitorResultDO,Long> {
    //@Lock(LockModeType.PESSIMISTIC_READ)
    List<MonitorResultDO> findAllByVehicleIdAndPortIdAndIsRead(int vehicleId, int portId, int isRead);
    List<MonitorResultDO> findAllByVehicleIdAndPortIdAndModelId(int vehicleId, int portId, int modelId);
    List<MonitorResultDO> findAllByVehicleIdAndPortIdAndModelIdAndIsRead(int vehicleId, int portId, int modelId, int isRead);
    List<MonitorResultDO> findAllByDataTimeBetween(Date start_time, Date end_time);
}
