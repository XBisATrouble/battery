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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Model0Task implements BaseMonitor {

    private IMonitorResultDOService monitorResultDOService;
    @Override
    public void excute(ModelMonitorDO monitorDO) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        if (monitorDO.getStatus().equals("进行中")) {
            while (true) {
                //获取新数据
                Long vehicleId = null;
                List<MonitorResultDO> resultDOList =
                        SpringUtil.getBean(IMonitorResultDOService.class).findAllByVehicleIdAndPortIdAndIsRead(vehicleId, monitorDO.getPortId(), Long.parseLong("0"));
                //通过websocket给前端发送
                for (int i = 0; i < resultDOList.size(); i++) {
                    MonitorResultDO resultDO = resultDOList.get(i);
                    ErrorMsgAO msgAO = new ErrorMsgAO();
                    msgAO.setDataTime(dateFormat.format(resultDO.getDataTime()));
                    msgAO.setResult(resultDO.getResult().toString());
                    String json = JSON.toJSONString(msgAO);
                    WebSocket.sendTextMessage("???", json);
                    resultDO.setIsRead(Long.parseLong("1"));
                    SpringUtil.getBean(IMonitorResultDOService.class).update(resultDO);
                }
                //系统时间达到设定时间 跳出循环
                if (monitorDO.getStartTime().equals(new Timestamp(System.currentTimeMillis()))) break;
            }
        }
    }
}
