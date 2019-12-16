package com.bupt.battery.task;

import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.form.ThreadForm;
import com.bupt.battery.request.TaskRequest;
import com.bupt.battery.util.SpringUtil;

//import javafx.concurrent.Task;


public class TaskThread implements Runnable {
    private ThreadForm form;

    //    private volatile static String fix="Task";
    public TaskThread(ThreadForm form) {
        this.form = form;
    }

    @Override
    public void run() {
        //        BaseTask baseTask = SpringUtil.getBean(TaskFactory.class).getTask("com.bupt.battery.task."+form.getName()+"Task");
        //        System.out.println(baseTask.toString());
        TypeUtil typeUtil = SpringUtil.getBean(TypeUtil.class);
        typeUtil.run(form);
    }
}
