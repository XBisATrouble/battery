package com.bupt.battery.service;

import com.bupt.battery.Enum.TaskType;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.repository.BaseRepository;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITaskDOService extends BaseService<TaskDO,Long> {
    Page<TaskDO> findTaskListByPage(Integer pageNum,Integer pageSize,String taskName, Date startTime,Date endTime, Long taskTypeId);
}
