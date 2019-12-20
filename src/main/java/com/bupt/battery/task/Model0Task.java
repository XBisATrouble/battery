package com.bupt.battery.task;

import com.alibaba.fastjson.JSON;
import com.bupt.battery.AO.ErrorMsgAO;
import com.bupt.battery.config.WebSocket;
import com.bupt.battery.entity.ModelMonitorDO;
import com.bupt.battery.entity.MonitorResultDO;
import com.bupt.battery.service.IModelMonitorDOService;
import com.bupt.battery.service.IMonitorResultDOService;
import com.bupt.battery.util.SpringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Model0Task implements BaseMonitor {

    @Override
    public void excute(ModelMonitorDO monitorDO) {
        System.out.print("\n" + "start monitor" + monitorDO.getId());

        DateFormat fmt_s = new SimpleDateFormat("HH:mm:ss");
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (monitorDO.getStatus().equals("进行中")) {
            //no data count
            int no_data_ct = 0;
            //循环查找数据库
            while (true) {
                //获取新数据
                List<MonitorResultDO> resultDOList =
//                        SpringUtil.getBean(IMonitorResultDOService.class).findAllByVehicleIdAndPortIdAndIsRead(
//                                monitorDO.getVehicleId(), monitorDO.getPortId().intValue(), 0);
                        SpringUtil.getBean(IMonitorResultDOService.class).findAllByVehicleIdAndPortIdAndModelIdAndIsRead(
                                monitorDO.getVehicleId(), monitorDO.getPortId().intValue(), monitorDO.getModelId().intValue(), 0
                        );
                if (resultDOList.size() !=0 ) {
                    for (MonitorResultDO resultDO : resultDOList) {
                        ErrorMsgAO msgAO = new ErrorMsgAO();
                        //给前端短时间戳格式
                        msgAO.setDataTime(fmt_s.format(resultDO.getDataTime()));
                        //给前端结果（float）
                        msgAO.setResult(resultDO.getResult().toString());
                        String json = JSON.toJSONString(msgAO);
                        if (WebSocket.getOnlineCount() == 1) {
                            WebSocket.sendTextMessage("realtime" + monitorDO.getId(), json);
                        } else {
                            //前端实时监控关闭
                            break;
                        }
                        //更新数据为已读状态
                        resultDO.setIsRead(1);
                        SpringUtil.getBean(IMonitorResultDOService.class).update(resultDO);
                    }
                } else {
                    no_data_ct = no_data_ct+1;
                    //当获取无数据次数达到10次
                    if (no_data_ct == 10) {
                        monitorDO.setStatus("已失败");
                        SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
                        break;
                    }
                }
                //系统时间达到设定时间 跳出循环
                if (fmt.format(monitorDO.getStartTime()).equals(fmt.format(new Date()))) {
                    monitorDO.setStatus("已完成");
                    SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
                    break;
                }
            }
        }
        //前端websocket测试
//        System.out.print("socket_ct:" + WebSocket.getOnlineCount());
//        while (true) {
//            try {
//                TimeUnit.MILLISECONDS.sleep(3000);
//                ErrorMsgAO msgAO = new ErrorMsgAO();
//                msgAO.setDataTime(fmt_s.format(new Date()));
//                Random rand = new Random();
//                float result = rand.nextFloat();
//                msgAO.setResult(result+"");
//                String json = JSON.toJSONString(msgAO);
//                if (WebSocket.getOnlineCount() == 1) {
//                    WebSocket.sendTextMessage("realtime" + monitorDO.getId(), json);
//                } else {
//                    break;
//                }
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
