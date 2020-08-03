package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

// 车辆数据粒度表
@Data
@Entity
@Table(name = "granularity")
public class GranularityDO extends BaseEntity<Long> {
    private String time; // 时间
    private String area; // 地域
    private String type; // 电池类型
}
