package com.bupt.battery.task;

import com.bupt.battery.Enum.StatusType;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.request.TaskRequest;
import com.bupt.battery.service.IDrivingLogDOService;
import com.bupt.battery.service.ITaskDOService;
import com.bupt.battery.service.IVoltageDifferenceDOService;
import com.bupt.battery.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 运行压差变化趋势图
 */

public class Type7Task implements BaseTask {



    @Override
    public void excute(TaskDO taskDO,String request) {
        System.out.println("执行type7------------------------");

//        TypeUtil.run(taskDO,request,"F:\\type7.py");
        TypeUtil.run(taskDO,request,"/home/python/type7.py");

    }
}
