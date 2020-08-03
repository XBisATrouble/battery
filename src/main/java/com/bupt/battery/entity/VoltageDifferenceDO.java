package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

// 未用 （电压差）
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
    private Float standardDeviation; // 标准偏差

}
