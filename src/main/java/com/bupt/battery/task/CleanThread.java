package com.bupt.battery.task;

public class CleanThread implements Runnable {
    private String request;

    public CleanThread(String request) {
        this.request = request;
    }

    @Override
    public void run() {
        CleanTask cleanTask=new CleanTask();
        cleanTask.excute(request);
    }
}
