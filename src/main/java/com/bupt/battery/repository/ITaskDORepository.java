package com.bupt.battery.repository;

import com.bupt.battery.entity.TaskDO;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskDORepository extends BaseRepository<TaskDO,Long>, JpaSpecificationExecutor<TaskDO> {

}
