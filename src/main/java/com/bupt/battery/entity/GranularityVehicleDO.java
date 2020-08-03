package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

// 车辆粒度与运行数据关联表
@Data
@Entity
@Table(name = "granularity_vehicle")
public class GranularityVehicleDO extends BaseEntity<Long> {
    private Long granularityId; // 粒度id
    private String vehicleId; // 车辆编号
}
