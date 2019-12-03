package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers.Base;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "data_management")
public class DataManagementDO extends BaseEntity<Long> {
    @Column(name = "gran_id")
    private Long granId;
    private Integer amount;
    @Column(name = "is_complete")
    private Boolean isComplete;
    private String deal;
}
