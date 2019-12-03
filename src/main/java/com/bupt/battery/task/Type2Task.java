package com.bupt.battery.task;

import com.bupt.battery.Enum.StatusType;
import com.bupt.battery.entity.DailyStatisticsDO;

import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.request.TaskRequest;
import com.bupt.battery.service.IDailyStatisticsDOService;
import com.bupt.battery.service.IDrivingLogDOService;
import com.bupt.battery.service.ITaskDOService;
import com.bupt.battery.util.SpringUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 耗电量
 */

public class Type2Task implements BaseTask {

    @Override
    public void excute(TaskDO taskDO,String request) {
//        TypeUtil.run(taskDO,request,"C:\\Users\\Administrator\\Desktop\\task11-30\\type1.py");
        TypeUtil.run(taskDO,request,"/home/python/type2.py");
    }
}
