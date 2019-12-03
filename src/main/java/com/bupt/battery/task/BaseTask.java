package com.bupt.battery.task;

import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.request.TaskRequest;

public interface BaseTask {
    void excute(TaskDO taskDO,String request);
}
