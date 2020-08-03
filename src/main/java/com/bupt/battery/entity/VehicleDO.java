package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

// 车辆基本信息表
@Data
@Entity
@Table(name = "vehicle", schema = "baas", catalog = "")
public class VehicleDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "vehicle_id")
    private String vehicleId; // 车辆编号
    @Column(name = "order_number")
    private String orderNumber; // 订单号
    private String carmaker; // 整车厂（生产）
    @Column(name = "driving_area")
    private String drivingArea; // 运行地区
    private String mode; // 运行模式（公交）
    @Column(name = "vehicle_usage")
    private String vehicleUsage; // 用法（PHEV）
    @Column(name = "battery_type")
    private String batteryType; // 电池类型
    @Column(name = "group_mode")
    private String groupMode; // 成组方式
    private String vin; // 车架号、Vin码（2P96S）
    @Column(name = "plate_number")
    private String plateNumber; // 车牌号
    @Column(name = "vehicle_version")
    private String vehicleVersion; // 版本号（8.0.0.2）
    @Column(name = "box_position")
    private String boxPosition; // 装箱位置（2箱下置）
    @Column(name = "car_length")
    private Float carLength; // 车长（单位：米）
    @Column(name = "online_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date onlineDate; // 上线时间
    private Integer amount; // 数量
    @Column(name = "thermal_management")
    private String thermalManagement; // 热管理（风冷）
    @Column(name = "vehicle_system")
    private String vehicleSystem; // 整车系统
    @Column(name = "bus_company")
    private String busCompany; // 公交公司（北京公交）
    private Integer mileage; // 运行里程（单位：公里）
    @Column(name = "bus_routes")
    private String busRoutes; // 公交路线（23路）
    @Column(name = "analysis_result")
    private String analysisResult; // 运行分析结果（分析报告地址）

}
