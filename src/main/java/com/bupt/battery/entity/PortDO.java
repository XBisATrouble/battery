package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

// 端口表
@Data
@Entity
@Table(name = "port", schema = "baas", catalog = "")
public class PortDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "port_num")
    private Integer portNum; // 端口号
    private Integer status; // 端口状态（0正常、1异常）
    @Column(name = "port_name")
    private String portName; // 端口名
    @Column(name = "port_description")
    private String portDescription; // 端口描述
    private String ip; // ip

}
