package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "daily_statistics")
public class DailyStatisticsDO extends BaseEntity<Long> {
    @Column(name = "vehicle_id")
    private String vehivleId;
    private Date date;
    private Float throughput;
    @Column(name = "power_consumption")
    private Float powerConsumption;
}
