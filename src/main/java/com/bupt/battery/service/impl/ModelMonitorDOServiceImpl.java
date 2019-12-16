package com.bupt.battery.service.impl;

import com.bupt.battery.entity.ModelDO;
import com.bupt.battery.entity.ModelMonitorDO;
import com.bupt.battery.repository.IModelMonitorDORepository;
import com.bupt.battery.service.IModelDOService;
import com.bupt.battery.service.IModelMonitorDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelMonitorDOServiceImpl extends BaseServiceImpl<ModelMonitorDO,Long> implements IModelMonitorDOService {
    @Autowired
    private IModelMonitorDORepository modelMonitorDORepository;
    @Override
    public ModelMonitorDO findModelMonitorDOByModelId(Long modelId) {
        return modelMonitorDORepository.findModelMonitorDOByModelId(modelId);
    }
    @Override
    public List<ModelMonitorDO> findAllByModelId(Long modelId) {
        return modelMonitorDORepository.findAllByModelId(modelId);
    }
}
