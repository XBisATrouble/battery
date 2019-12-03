package com.bupt.battery.task;


import com.bupt.battery.Enum.StatusType;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.entity.TemperaturePointDistributionDO;
import com.bupt.battery.request.TaskRequest;
import com.bupt.battery.service.IDrivingLogDOService;
import com.bupt.battery.service.ITaskDOService;
import com.bupt.battery.service.ITemperaturePointDistributionDOService;
import com.bupt.battery.util.SpringUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 温度采集点分布图
 */

public class Type6Task implements BaseTask{


    @Override
    public void excute(TaskDO taskDO,String request) {
//        TypeUtil.run(taskDO,request,"F:\\tmp6.py");
        TypeUtil.run(taskDO,request,"/home/python/type6.py");

    }
}