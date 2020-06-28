package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "driving_log")
public class DrivingLogDO extends BaseEntity<Long> implements Serializable {
    private Long id;
    @Column(name = "vehicle_id")
    private String vehicleId;
    private Date time;
    private Float GPS_Speed;
    private Float GPS_Mileage;
    private Float Cell_V_max;
    private Integer Cell_V_max_num;
    private Float Cell_V_min;
    private Integer Cell_V_min_num;
    private Float Voltage;
    private Float CurrentLimitCharge;
    private Float CurrentLimitDischarge;
    private Float Current;
    private Float Temp_max;
    private Float Temp_min;
    private Integer SOC;
    private Float Charge_Voltage;
    private Float Charge_Current;
    private Float Charge_Cell_V_max;
    private Integer Charge_MaxCellVoltageNumber;
    private Integer Charge_SOC;
    private Float TemperatureLimitMax;
    private Float Diff_Voltage;
    private Float ave_Voltage;
    private Integer MaxCellVoltageNumber;
    private Integer MinCellVoltageNumber;
    private Integer MaxTempBoxNum;
    private Integer MinTempBoxNum;
    private Float MaxSumVLimit;
    private Float MinSumVLimit;
    private Float BatteryPower;
    private Float PowerLimitDischarge;
    private Float PowerLimitCharge;

}
