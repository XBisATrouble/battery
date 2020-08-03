package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import java.util.Date;

// 监控日志表
@Data
public class MonitoringLogDO extends BaseEntity<Long> {
    private Long monitor_id; // 监控id
    private Date time; // 时间
    private String data; // 输入数据
    private Float result; // 结果
}
