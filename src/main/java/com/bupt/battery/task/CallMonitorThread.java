package com.bupt.battery.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 处理Socket请求的线程类
 */
public class CallMonitorThread implements Runnable {

    public Process proc;
    public String port;

    public CallMonitorThread(String port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            String command = "python D:\\Workshop\\untitled4\\Model1.py " + port;
            System.out.println(command);
            proc = Runtime.getRuntime().exec(command);
            proc.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}