package com.bupt.battery.service;

import com.bupt.battery.entity.ModelDO;
import com.bupt.battery.entity.ModelMonitorDO;

import java.util.List;

public interface IModelMonitorDOService extends BaseService<ModelMonitorDO,Long> {
    //test 12.6.19
    ModelMonitorDO findModelMonitorDOByModelId(Long modelId);
    //
    List<ModelMonitorDO> findAllByModelId(Long modelId);
}
