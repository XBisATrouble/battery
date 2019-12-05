package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dict")
public class DictDO extends BaseEntity<Long>{
    private String type;
    private String value;
}
