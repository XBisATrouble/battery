package com.bupt.battery.task;


import java.util.HashMap;

/**
 * 处理Socket请求的线程类
 */
public class CallMonitorThread implements Runnable {

    public Process proc;
    public String port;
    public int modelId;
    public CallMonitorThread(String port, int modelId) {
        this.port = port;
        this.modelId = modelId;
    }

    @Override
    public void run() {
        try {
            HashMap<Integer, String> typeMap = new HashMap<Integer, String>();
            typeMap.put(0,"hot");
            typeMap.put(1,"resistance");
            typeMap.put(2,"shortcircuit");
            typeMap.put(3,"unconsistency");
            //String command = "python D:\\Workshop\\untitled4\\early_warning.py " + port +" " + typeMap.get(modelId);
            String command = "python /home/python/early_warning.py " + port +" " + typeMap.get(modelId);
            //String command = "python D:\\model\\Model" + modelId + ".py " + port;
            System.out.println(command);
            proc = Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}