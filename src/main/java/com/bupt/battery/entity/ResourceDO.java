package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author nyq
 * @date 2020/4/27 22:55
 */
@Data
@Entity
@Table(name = "resource", schema = "baas", catalog = "")
public class ResourceDO extends BaseEntity<Long> implements Serializable {
    private String name;
    private String type;
    private String url;
}
