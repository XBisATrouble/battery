package com.bupt.battery.service.impl;

import com.bupt.battery.entity.DataManagementDO;
import com.bupt.battery.entity.VehicleDO;
import com.bupt.battery.repository.IDataManagementDORepository;
import com.bupt.battery.service.IDataManagementDOService;
import com.bupt.battery.service.IVehicleDOService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataManagementDOServiceImpl extends BaseServiceImpl<DataManagementDO,Long> implements IDataManagementDOService {
    @Autowired
    private IDataManagementDORepository dataManagementDORepository;
    @Override
    public DataManagementDO findDataManagementDOByGranId(Long granId) {
        return dataManagementDORepository.findDataManagementDOByGranId(granId);
    }
}
