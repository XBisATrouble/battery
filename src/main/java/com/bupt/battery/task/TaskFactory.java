package com.bupt.battery.task;

import org.springframework.stereotype.Component;

@Component
public class TaskFactory {
    BaseTask getTask(String name) {
        System.out.println(name+"-------name");
        try {
            Class<?> factoryClass = Class.forName(name);
            return (BaseTask) factoryClass.newInstance();
        }catch (Exception e)
        {
            return null;
        }
    }
    BaseMonitor getMonitor(String name) {
        try {
            System.out.println(name+"-------task_name");
            Class<?> factoryClass = Class.forName(name);
            return (BaseMonitor) factoryClass.newInstance();
        }catch (Exception e)
        {
            System.out.println(name+"-------can't get this task");
            return null;
        }
    }
}