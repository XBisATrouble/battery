package com.bupt.battery.service;

import com.bupt.battery.entity.DataManagementDO;
import com.bupt.battery.entity.VehicleDO;

public interface IDataManagementDOService extends BaseService<DataManagementDO,Long> {
    DataManagementDO findDataManagementDOByGranId(Long granId);
}
