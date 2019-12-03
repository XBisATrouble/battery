package com.bupt.battery.repository;

import com.bupt.battery.entity.ChargeDischargeStatisticsDO;
import com.bupt.battery.entity.TaskTypeDO;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskTypeDORepository extends BaseRepository<TaskTypeDO,Long> {
    TaskTypeDO findTaskTypeDOByType(String type);
}
