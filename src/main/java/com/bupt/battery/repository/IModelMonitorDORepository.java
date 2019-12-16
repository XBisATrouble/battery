package com.bupt.battery.repository;

import com.bupt.battery.entity.ModelDO;
import com.bupt.battery.entity.ModelMonitorDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IModelMonitorDORepository extends BaseRepository<ModelMonitorDO,Long>{
    //test 12.6.19
    //根据modelid查找
    ModelMonitorDO findModelMonitorDOByModelId(Long modelId);
    List<ModelMonitorDO> findAllByModelId(Long modelId);
}
