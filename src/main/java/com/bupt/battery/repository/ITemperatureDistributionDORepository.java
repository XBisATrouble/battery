package com.bupt.battery.repository;

import com.bupt.battery.entity.PortDO;
import com.bupt.battery.entity.TemperatureDistributionDO;
import org.springframework.stereotype.Repository;

@Repository
public interface ITemperatureDistributionDORepository extends BaseRepository<TemperatureDistributionDO,Long>{

}
