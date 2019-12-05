package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "vehicle", schema = "baas", catalog = "")
public class VehicleDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    @Column(name = "order_number")
    private String orderNumber;
    private String carmaker;
    @Column(name = "driving_area")
    private String drivingArea;
    private String mode;
    @Column(name = "vehicle_usage")
    private String vehicleUsage;
    @Column(name = "battery_type")
    private String batteryType;
    @Column(name = "group_mode")
    private String groupMode;
    private String vin;
    @Column(name = "plate_number")
    private String plateNumber;
    @Column(name = "vehicle_version")
    private String vehicleVersion;
    @Column(name = "box_position")
    private String boxPosition;
    @Column(name = "car_length")
    private Float carLength;
    @Column(name = "online_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date onlineDate;
    private Integer amount;
    @Column(name = "thermal_management")
    private String thermalManagement;
    @Column(name = "vehicle_system")
    private String vehicleSystem;
    @Column(name = "bus_company")
    private String busCompany;
    private Integer mileage;
    @Column(name = "bus_routes")
    private String busRoutes;
    @Column(name = "analysis_result")
    private String analysisResult;

}
