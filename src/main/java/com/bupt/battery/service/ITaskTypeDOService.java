package com.bupt.battery.service;

import com.bupt.battery.entity.ChargeDischargeStatisticsDO;
import com.bupt.battery.entity.TaskTypeDO;

public interface ITaskTypeDOService extends BaseService<TaskTypeDO,Long> {
    TaskTypeDO findTaskTypeDOByType(String type);
}
