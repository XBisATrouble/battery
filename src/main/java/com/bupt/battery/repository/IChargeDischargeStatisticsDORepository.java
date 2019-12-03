package com.bupt.battery.repository;

import com.bupt.battery.entity.ChargeDischargeStatisticsDO;
import com.bupt.battery.entity.baseEntity.BaseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IChargeDischargeStatisticsDORepository extends BaseRepository<ChargeDischargeStatisticsDO,Long> {
}
