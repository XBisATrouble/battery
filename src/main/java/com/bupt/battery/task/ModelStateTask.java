package com.bupt.battery.task;

import com.bupt.battery.entity.ModelMonitorDO;
import com.bupt.battery.entity.MonitorResultDO;
import com.bupt.battery.service.IModelMonitorDOService;
import com.bupt.battery.service.IMonitorResultDOService;
import com.bupt.battery.service.ITaskDOService;
import com.bupt.battery.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.List;

public class ModelStateTask implements BaseMonitor{

    @Override
    public void excute(ModelMonitorDO monitorDO) {

        //监控状态
        String status;
        while (true) {
            //数据库查询是否有新数据
            //获取新数据个数
            Long vehicleId = null;
            List<MonitorResultDO> resultDOList =
                    SpringUtil.getBean(IMonitorResultDOService.class).findAllByVehicleIdAndPortIdAndIsRead(vehicleId, monitorDO.getPortId(), Long.parseLong("0"));
            int datasize = resultDOList.size();
            if (datasize > 0 && monitorDO.getStartTime().equals(new Timestamp(System.currentTimeMillis()))) {
                //数据不为空且开始时间达到系统时间
                status = "进行中";
                monitorDO.setStatus(status);
                //线程关闭
                break;
            } else if (datasize > 0 && !monitorDO.getStartTime().equals(new Timestamp(System.currentTimeMillis()))) {
                status = "已就绪";
                monitorDO.setStatus(status);
                SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
            }
        }
        SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
    }
}
