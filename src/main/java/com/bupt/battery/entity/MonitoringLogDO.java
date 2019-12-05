package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.util.Date;
import lombok.Data;

@Data
public class MonitoringLogDO extends BaseEntity<Long> {
    private Long monitor_id;
    private Date time;
    private String data;
    private Float result;
}
