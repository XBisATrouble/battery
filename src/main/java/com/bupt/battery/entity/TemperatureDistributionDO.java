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
@Table(name = "temperature_distribution", schema = "baas", catalog = "")
public class TemperatureDistributionDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    private Date date;
    private Float lower;
    private Float t022;
    private Float t017;
    private Float t012;
    private Float t07;
    private Float t02;
    private Float t3;
    private Float t8;
    private Float t13;
    private Float t18;
    private Float t23;
    private Float t28;
    private Float t33;
    private Float t38;
    private Float t43;
    private Float t48;
    private Float t53;
    private Float t58;
    private Float t63;
}
