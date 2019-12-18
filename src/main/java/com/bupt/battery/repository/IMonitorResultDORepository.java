package com.bupt.battery.repository;

import com.bupt.battery.entity.MonitorResultDO;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface IMonitorResultDORepository extends BaseRepository<MonitorResultDO,Long> {
    //@Lock(LockModeType.PESSIMISTIC_READ)
    List<MonitorResultDO> findAllByVehicleIdAndPortIdAndIsRead(Long vehicleId, Long portId, Long isRead);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    MonitorResultDO update(MonitorResultDO resultDO);
}
