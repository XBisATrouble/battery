package com.bupt.battery.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 处理Socket请求的线程类
 */
public class CallMonitorThread implements Runnable {

    public Process proc;

    @Override
    public void run() {
        try {
            proc = Runtime.getRuntime().exec("python D:\\Workshop\\untitled4\\PythonClient.py");
            System.out.println("in");
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