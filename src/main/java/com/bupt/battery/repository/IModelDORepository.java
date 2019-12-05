package com.bupt.battery.repository;

import com.bupt.battery.entity.ModelDO;
import com.bupt.battery.entity.TaskDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IModelDORepository extends BaseRepository<ModelDO,Long>{

}
