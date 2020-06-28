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
@Table(name = "voltage_difference", schema = "baas", catalog = "")
public class VoltageDifferenceDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "vehicle_id")
    private String vehicleId;
    private Date date;
    private String type;
    private Float average;
    @Column(name = "standard_deviation")
    private Float standardDeviation;

}
