package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "model", schema = "baas", catalog = "")
public class ModelDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "model_name")
    private String modelName;
    @Column(name = "creator")
    private String creator;
    @Column(name = "create_time")
    private Timestamp createTime;
    private String url;
    @Column(name = "train_data_description")
    private String trainDataDescription;
    @Column(name = "model_type")
    private Integer modelType;

}