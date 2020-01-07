package com.bupt.battery.task;


/**
 * 处理Socket请求的线程类
 */
public class IntMonitorThread implements Runnable {

    public Process proc;
    public String port;
    public int modelId;
    public IntMonitorThread(String port, int modelId) {
        this.port = port;
        this.modelId = modelId;
    }

    @Override
    public void run() {
        try {
            // String command = "python D:\\Workshop\\untitled4\\interrupt.py " + port;
            String command = "python /home/python/Model" + modelId + ".py " + port;
            System.out.println(command);
            proc = Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}