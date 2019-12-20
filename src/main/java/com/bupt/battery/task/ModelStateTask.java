package com.bupt.battery.task;

import com.bupt.battery.entity.ModelMonitorDO;
import com.bupt.battery.entity.MonitorResultDO;
import com.bupt.battery.service.IModelMonitorDOService;
import com.bupt.battery.service.IMonitorResultDOService;
import com.bupt.battery.service.ITaskDOService;
import com.bupt.battery.util.SpringUtil;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ModelStateTask implements BaseMonitor{

    @Override
    public void excute(ModelMonitorDO monitorDO) {
        //监控状态
        String status;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (true) {
            int vehicleId = 0;
            int modelId = monitorDO.getModelId().intValue();
            int portId = monitorDO.getPortId().intValue();
            List<MonitorResultDO> resultDOList =
                    SpringUtil.getBean(IMonitorResultDOService.class).findAllByVehicleIdAndPortIdAndIsRead(vehicleId, modelId, portId);
            int data_size = resultDOList.size();
            if (data_size > 0 && fmt.format(monitorDO.getStartTime()).equals(fmt.format(new Date()))) {
                //数据不为空且开始时间达到系统时间
                status = "进行中";
                monitorDO.setStatus(status);
                //线程关闭
                break;
            } else if (data_size > 0 && !fmt.format(monitorDO.getStartTime()).equals(fmt.format(new Date()))) {
                status = "已就绪";
                monitorDO.setStatus(status);
                SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
            }
        }
        SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
    }
}
