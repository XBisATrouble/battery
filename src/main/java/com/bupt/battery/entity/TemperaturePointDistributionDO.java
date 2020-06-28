package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

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
    private String tempDiffMaxTime;
}
