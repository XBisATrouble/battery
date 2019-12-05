package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "task_type")
public class TaskTypeDO extends BaseEntity<Long> {
    private String type;
    @Column(name = "param_name_code")
    private String paramNameCode;
    @Column(name = "param_name")
    private String paramName;
    @Column(name = "param_type")
    private String paramType;
    private String remark;
}
