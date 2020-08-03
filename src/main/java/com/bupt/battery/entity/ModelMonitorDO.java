package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

// 监控表
@Data
@Entity
@Table(name = "model_monitoring", schema = "baas", catalog = "")
public class ModelMonitorDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "model_id")
    private Long modelId; // 模型id

    @Column(name = "creator")
    private String creator; // 创建人

    @Column(name = "create_time")
    private Timestamp createTime; // 创建日期

    @Column(name = "status")
    private String status; // 运行状态（0未就绪、1已就绪、2运行中、3已完成、4已失败）

    @Column(name = "port_id")
    private Long portId; // 端口id

    @Column(name = "start_time")
    private Date startTime; // 监控开始时间

    @Column(name = "end_time")
    private Date endTime; // 监控结束时间

    @Column(name = "vehicle_id")
    private String vehicleId; // 车辆编号
}