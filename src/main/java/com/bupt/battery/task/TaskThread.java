package com.bupt.battery.task;

import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.request.TaskRequest;
import com.bupt.battery.util.SpringUtil;
import javafx.concurrent.Task;

public class TaskThread implements Runnable {
    private String name;
    private String taskRequest;
    private TaskDO taskDO;
//    private volatile static String fix="Task";
    public TaskThread(TaskDO taskDO,String name,String taskRequest)
    {
        this.name="com.bupt.battery.task."+name+"Task";
        this.taskRequest=taskRequest;
        this.taskDO=taskDO;
    }
    @Override
    public void run() {
        BaseTask baseTask = SpringUtil.getBean(TaskFactory.class).getTask(name);
        System.out.println(baseTask.toString());
        baseTask.excute(taskDO,taskRequest);
    }
}
