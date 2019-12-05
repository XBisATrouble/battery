package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.io.Serializable;
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
@Table(name = "voltage_point_distribution", schema = "baas", catalog = "")
public class VoltagePointDistributionDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    private Date date;
    @Column(name = "max_current_time1")
    private String maxCurrentTime1;
    @Column(name = "max_current_time2")
    private String maxCurrentTime2;
    @Column(name = "max_half_current_time1")
    private String maxHalfCurrentTime1;
    @Column(name = "max_half_current_time2")
    private String maxHalfCurrentTime2;
    @Column(name = "rms_current_time1")
    private String rmsCurrentTime1;
    @Column(name = "rms_current_time2")
    private String rmsCurrentTime2;
}
