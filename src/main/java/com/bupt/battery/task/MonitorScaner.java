package com.bupt.battery.task;

import com.bupt.battery.entity.ModelMonitorDO;
import com.bupt.battery.service.IModelMonitorDOService;
import com.bupt.battery.util.SpringUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Order(value = 1)
public class MonitorScaner implements ApplicationRunner  {
    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.print("run monitor_scaner\n");
        while (true) {
            List<ModelMonitorDO> list = SpringUtil.getBean(IModelMonitorDOService.class).findAll();
            if (list.size() > 0) {
                for(ModelMonitorDO monitorDO : list) {
                    if (fmt.format(monitorDO.getStartTime()).equals(fmt.format(new Date()))) {
                        monitorDO.setStatus("进行中");
                        SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
                        //启动模型python程序
                        new Thread(new CallMonitorThread(monitorDO.getPortId().toString(), monitorDO.getModelId().intValue())).start();
                    } else if (fmt.format(monitorDO.getEndTime()).equals(fmt.format(new Date()))) {
                        monitorDO.setStatus("已完成");
                        SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
                    }
                }
            }
            //1s执行一次
            TimeUnit.MILLISECONDS.sleep(10000);
        }
    }
}
