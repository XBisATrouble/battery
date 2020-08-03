package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

// 此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。
// 这里的schema 指数据库的用户名

// 未用（充放电统计）
@Data
@Entity
@Table(name = "charge_discharge_statistics", schema = "baas", catalog = "")
public class ChargeDischargeStatisticsDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "vehicle_id")
    private String vehicleId;
    @Column(name = "is_charge") // 是充电吗
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
    private Float chargingPower; // 充电电源
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
