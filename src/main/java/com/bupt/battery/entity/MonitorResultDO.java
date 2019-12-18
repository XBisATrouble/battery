package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "monitor_result", schema = "baas", catalog = "")
public class MonitorResultDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "data_time")
    private Date dataTime;

    @Column(name = "is_read")
    private Long isRead;

    @Column(name = "result")
    private Float result;

    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "port_id")
    private Long portId;
}