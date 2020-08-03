package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

// 未用 （每日统计）
@Data
@Entity
@Table(name = "daily_statistics")
public class DailyStatisticsDO extends BaseEntity<Long> {
    @Column(name = "vehicle_id")
    private String vehivleId;
    private Date date;
    private Float throughput; // 吞吐量
    @Column(name = "power_consumption")
    private Float powerConsumption; // 功率消耗
}
