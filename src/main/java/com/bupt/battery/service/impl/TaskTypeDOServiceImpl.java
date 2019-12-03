package com.bupt.battery.service.impl;

import com.bupt.battery.entity.TaskTypeDO;
import com.bupt.battery.repository.ITaskTypeDORepository;
import com.bupt.battery.service.ITaskTypeDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskTypeDOServiceImpl extends BaseServiceImpl<TaskTypeDO,Long> implements ITaskTypeDOService {
    @Autowired
    private ITaskTypeDORepository taskTypeDORepository;
    @Override
    public TaskTypeDO findTaskTypeDOByType(String type) {
        return taskTypeDORepository.findTaskTypeDOByType(type);
    }
}
