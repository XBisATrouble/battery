package com.bupt.battery.task;

import org.springframework.stereotype.Component;

@Component
public class TaskFactory {
    public BaseTask getTask(String name) {
        System.out.println(name+"-------name");
        try {
            Class<?> factoryClass = Class.forName(name);
            return (BaseTask) factoryClass.newInstance();
        }catch (Exception e)
        {
            return null;
        }
    }
    public BaseMonitor getMonitor(String name) {
        System.out.println(name+"-------name");
        try {
            Class<?> factoryClass = Class.forName(name);
            return (BaseMonitor) factoryClass.newInstance();
        }catch (Exception e)
        {
            return null;
        }
    }
}
