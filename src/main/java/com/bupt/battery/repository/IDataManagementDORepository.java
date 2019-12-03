package com.bupt.battery.repository;

import com.bupt.battery.entity.DataManagementDO;
import com.bupt.battery.entity.VehicleDO;
import org.springframework.stereotype.Repository;

@Repository
public interface IDataManagementDORepository extends BaseRepository<DataManagementDO,Long>{

    DataManagementDO findDataManagementDOByGranId(Long granId);
}
