package com.bupt.battery.task;

import com.alibaba.fastjson.JSON;
import com.bupt.battery.AO.ErrorMsgAO;
import com.bupt.battery.config.WebSocket;
import com.bupt.battery.entity.ModelMonitorDO;
import com.bupt.battery.service.IModelMonitorDOService;
import com.bupt.battery.util.SpringUtil;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Model2Task implements BaseMonitor {
    private Timer timer = new Timer();
    private String status;
    @Override
    public void excute(ModelMonitorDO monitorDO){


//        System.out.print("Start thread of monitor" + monitorDO.getId() + "\n");
//        //Random r = new Random();
//        //int status_R = r.nextInt(100);
//        //monitorDO.setStatus("运行中");
//        status = "运行中";
//        monitorDO.setStatus(status);
//        SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
//        System.out.print("monitor" + monitorDO.getId() + "运行中\n");
//        while (true) {
//            Random r = new Random();
//            int status_R = r.nextInt(500);
//            if (status_R < 5 || status_R > 490) {
//                if (status_R > 490) {
//                    status = "已完成";
//                } else {
//                    status = "已失败";
//                }
//                break;
//            } else {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(3000);
//                    Random rand = new Random();
//                    ErrorMsgAO msgAO = new ErrorMsgAO();
//                    msgAO.setError(rand.nextInt(2));
//                    if (msgAO.getError() == 1) {
//                        List<Integer> bList = new ArrayList<>();
//                        for (int j = 0; j < rand.nextInt(10) + 1; j++) {
//                            bList.add(rand.nextInt(300) + 1);
//                        }
//                        msgAO.setNo(bList);
//                    } else {
//                        msgAO.setNo(null);
//                    }
//                    String json = JSON.toJSONString(msgAO);
//                    WebSocket.sendTextMessage(monitorDO.getId() + "", json);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        timer.cancel();
//        System.out.print(status);
//        //更新监控状态
//        monitorDO.setStatus(status);
//        SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
    }
}
