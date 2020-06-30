package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "granularity_vehicle")
public class GranularityVehicleDO extends BaseEntity<Long> {
    private Long granularityId;
    private String vehicleId;
}
