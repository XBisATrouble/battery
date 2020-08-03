package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author nyq
 * @date 2020/4/27 22:55
 */
// 资源表
@Data
@Entity
@Table(name = "resource", schema = "baas", catalog = "")
public class ResourceDO extends BaseEntity<Long> implements Serializable {
    private String name; // 资源名（图片名称）
    private String type; // 资源类型（图片对应的类型，vehicleVersion，boxPosition，groupMode三种）
    private String url; // 路径（图片存储地址）
}
