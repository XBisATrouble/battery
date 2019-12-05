package com.bupt.battery.task;

import com.bupt.battery.Enum.StatusType;
import com.bupt.battery.entity.DrivingLogDO;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.entity.TemperatureDistributionDO;
import com.bupt.battery.request.TaskRequest;
import com.bupt.battery.service.IDrivingLogDOService;
import com.bupt.battery.service.ITaskDOService;
import com.bupt.battery.service.ITemperatureDistributionDOService;
import com.bupt.battery.util.SpringUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 温度概率分布图
 */

public class Type3Task implements BaseTask {
    @Override
    public void excute(TaskDO taskDO,String request) {
//        TypeUtil.run(taskDO,request,"F:\\new_tmat3.py");
//        TypeUtil.run(taskDO,request,"/home/python/new_tmat3.py");
        TypeUtil.run(taskDO,request,"/home/python/type3.py");

    }
}
