package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "port", schema = "baas", catalog = "")
public class PortDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "port_num")
    private Integer portNum;
    private Integer status;
    @Column(name = "port_name")
    private String portName;
    @Column(name = "port_description")
    private String portDescription;
    private String ip;

}
