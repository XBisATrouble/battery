package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// 粒度管理表
@Data
@Entity
@Table(name = "data_management")
public class DataManagementDO extends BaseEntity<Long> {
    @Column(name = "gran_id")
    private Long granId; // 粒度id
    private Integer amount; // 数量
    @Column(name = "is_complete")
    private Boolean isComplete; // 是否完整
    private String deal; // 预处理情况（ 0~4位各表示一种预处理：0位表示SOC预处理，1位表示温度预处理，2位表示电压预处理，3位表示电流预处理，4位表示去重预处理。）
}
