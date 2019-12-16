package com.bupt.battery.task;

import com.bupt.battery.entity.ModelMonitorDO;
import com.bupt.battery.util.SpringUtil;

public class MonitorThread implements Runnable {
    private String name;
    private ModelMonitorDO monitorDO;

    public MonitorThread(ModelMonitorDO monitorDO, String name) {
        this.name = "com.bupt.battery.task."+name+"Task";
        this.monitorDO = monitorDO;
    }
    @Override
    public void run(){
        BaseMonitor baseMonitor = SpringUtil.getBean(TaskFactory.class).getMonitor(name);
        System.out.print(baseMonitor.toString());
        baseMonitor.excute(monitorDO);
    }
}
