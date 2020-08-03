package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

// 用户表
@Data
@Entity
@Table(name = "user", schema = "baas", catalog = "")
public class UserDO extends BaseEntity<Long> implements Serializable {

    private String username; // 用户名
    private String truename; // 真实姓名
    private String password; // 密码
    private String role; // 角色（0超级管理员、1数据分析人员、2车辆数据管理人员、3数据采集人员）
    private String email; // 邮箱
    private String phone; // 电话
}
