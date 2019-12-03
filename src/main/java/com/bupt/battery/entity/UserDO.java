package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user", schema = "baas", catalog = "")
public class UserDO extends BaseEntity<Long> implements Serializable {

    private String username;
    private String truename;
    private String password;
    private String role;
    private String email;
    private String phone;
}
