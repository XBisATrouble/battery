package com.bupt.battery.task;

import com.bupt.battery.entity.TaskDO;

/**
 * 运行压差变化趋势图
 */

public class Type11Task implements BaseTask {



    @Override
    public void excute(TaskDO taskDO,String request) {
        System.out.println("执行type7------------------------");

//        TypeUtil.run(taskDO,request,"F:\\type7.py");
        TypeUtil.run(taskDO,request,"/home/python/type11.py");

    }
}
