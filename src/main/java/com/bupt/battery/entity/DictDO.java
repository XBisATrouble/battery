package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

// 字典管理表
@Data
@Entity
@Table(name = "dict")
public class DictDO extends BaseEntity<Long> {
    private String type; // 字典类型（包括整车厂，运行地区，运行模式等）
    private String value; // 字典值（对运行地区，字典值有北京，济南）
}
