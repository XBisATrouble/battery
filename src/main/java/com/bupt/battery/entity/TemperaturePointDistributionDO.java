package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

// 未用 (温度分布)
@Data
@Entity
@Table(name = "temperature_point_distribution", schema = "baas", catalog = "")
public class TemperaturePointDistributionDO extends BaseEntity<Long> {

    @Column(name = "vehicle_id")
    private String vehicleId;
    private Date date;
    @Column(name = "temp_min_time")
    private String tempMinTime;
    @Column(name = "temp_max_time")
    private String tempMaxTime;
    @Column(name = "temp_diff_max_time")
    private String tempDiffMaxTime; // 温差最大时间
}
