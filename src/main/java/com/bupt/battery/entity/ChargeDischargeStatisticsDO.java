package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "charge_discharge_statistics", schema = "baas", catalog = "")
public class ChargeDischargeStatisticsDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "vehicle_id")
    private String vehicleId;
    @Column(name = "is_charge")
    private String isCharge;
    @Column(name = "start_time")
    private Timestamp startTime;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "start_soc")
    private Integer startSoc;
    @Column(name = "end_soc")
    private Integer endSoc;
    @Column(name = "charging_power")
    private Float chargingPower;
    @Column(name = "max_temperature")
    private Float maxTemperature;
    @Column(name = "min_temperature")
    private Float minTemperature;
    @Column(name = "max_voltage")
    private Float maxVoltage;
    @Column(name = "max_current")
    private Float maxCurrent;
    @Column(name = "max_voltage_cell")
    private Float maxVoltageCell;
}
