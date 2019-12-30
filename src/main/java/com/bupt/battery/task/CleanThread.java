package com.bupt.battery.task;

public class CleanThread implements Runnable {
    private String request;
    private String shopId;

    public CleanThread(String request, String shopId) {
        this.request = request;
        this.shopId = shopId;
    }

    @Override
    public void run() {
        CleanTask cleanTask=new CleanTask();
        cleanTask.excute(request, shopId);
    }
}
