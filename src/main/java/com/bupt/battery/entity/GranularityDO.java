package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "granularity")
public class GranularityDO extends BaseEntity<Long> {
    private String time;
    private String area;

    private String type;
}
