package com.bupt.battery.repository;

import com.bupt.battery.entity.GranularityDO;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.entity.VehicleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IGranularityDORepository extends BaseRepository<GranularityDO,Long>, JpaSpecificationExecutor<GranularityDO> {
    GranularityDO getGranularityDOByAreaAndTimeAndType(String area,String time,String type);
}
