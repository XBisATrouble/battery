package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

// 模型表
@Data
@Entity
@Table(name = "model", schema = "baas", catalog = "")
public class ModelDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "model_name")
    private String modelName; // 模型名
    @Column(name = "creator")
    private String creator; // 创建人
    @Column(name = "create_time")
    private Timestamp createTime; // 创建时间
    private String url; // 模型路径
    @Column(name = "train_data_description")
    private String trainDataDescription; // 模型描述
    @Column(name = "model_type")
    private Integer modelType; // 模型类型（100短期故障预警模型（101热失控预警模型、102内短路预警模型、103液漏预警模型、104阻异常预警模型、105不一致预警模型）、200中长期故障预警模型）

}