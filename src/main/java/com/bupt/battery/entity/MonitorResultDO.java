package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

// 监控结果表
@Data
@Entity
@Table(name = "monitor_result", schema = "baas", catalog = "")
public class MonitorResultDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "vehicle_id")
    private String vehicleId; // 车辆编号

    @Column(name = "data_time")
    private Date dataTime; // 起始时间

    @Column(name = "is_read")
    private int isRead; // 读取状态（0未读、1已读）

    @Column(name = "result")
    private Float result; // 结果（结果数据）

    @Column(name = "model_id")
    private int modelId; // 模型编号

    @Column(name = "port_id")
    private int portId; // 端口id
}
