package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

// 预处理后车辆运行数据表
@Data
@Entity
@Table(name = "driving_log")
public class DrivingLogDO extends BaseEntity<Long> implements Serializable {
    private Long id;
    @Column(name = "vehicle_id")
    private String vehicleId; // 车辆编号
    private Date time; // 时间
    private Float GPS_Speed; // 车速
    private Float GPS_Mileage; // 里程
    private Float Cell_V_max; // 单体最高电压
    private Integer Cell_V_max_num; // 单体最高电压编号
    private Float Cell_V_min; // 单体最低电压
    private Integer Cell_V_min_num; // 单体最低电压编号
    private Float Voltage; // 总电压
    private Float CurrentLimitCharge; // 充电电流限值
    private Float CurrentLimitDischarge; // 放电电流限值
    private Float Current; // 总电流
    private Float Temp_max; // 最高温度
    private Float Temp_min; // 最低温度
    private Integer SOC; // soc电池容量
    private Float Charge_Voltage; // 充电电压
    private Float Charge_Current; // 充电电流
    private Float Charge_Cell_V_max; // 充电单体最高电压
    private Integer Charge_MaxCellVoltageNumber; // 充电单体最高电压组号
    private Integer Charge_SOC; // 充电SOC
    private Float TemperatureLimitMax; // 最高温度限值
    private Float Diff_Voltage; // 压差
    private Float ave_Voltage; // 单体电池平均电压
    private Integer MaxCellVoltageNumber; // 单体最高电压所在串数
    private Integer MinCellVoltageNumber; // 单体最低电压所在串数
    private Integer MaxTempBoxNum; // 最高温度所在模块数
    private Integer MinTempBoxNum; // 最低温度所在模块数
    private Float MaxSumVLimit; // 电池总电压上限
    private Float MinSumVLimit; // 电池总电压下限
    private Float BatteryPower; // 功率
    private Float PowerLimitDischarge; // 放电功率限值
    private Float PowerLimitCharge; // 充电功率限值

}
