package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

//@Data 注解的主要作用是提高代码的简洁，使用这个注解可以省去代码中大量的get()、 set()、 toString()等方法；

// 未用（电流分布）
@Data
@Entity
@Table(name = "current_distribution", schema = "baas", catalog = "")
public class CurrentDistributionDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "vehicle_id")
    private String vehicleId;
    private Date date;
    private Float lower;
    private Float t0350;
    private Float t0340;
    private Float t0330;
    private Float t0320;
    private Float t0310;
    private Float t0300;
    private Float t0290;
    private Float t0280;
    private Float t0270;
    private Float t0260;
    private Float t0250;
    private Float t0240;
    private Float t0230;
    private Float t0220;
    private Float t0210;
    private Float t0200;
    private Float t0190;
    private Float t0180;
    private Float t0170;
    private Float t0160;
    private Float t0150;
    private Float t0140;
    private Float t0130;
    private Float t0120;
    private Float t0110;
    private Float t0100;
    private Float t090;
    private Float t080;
    private Float t070;
    private Float t060;
    private Float t050;
    private Float t040;
    private Float t030;
    private Float t020;
    private Float t010;
    private Float t0;
    private Float t10;
    private Float t20;
    private Float t30;
    private Float t40;
    private Float t50;
    private Float t60;
    private Float t70;
    private Float t80;
    private Float t90;
    private Float t100;
    private Float t110;
    private Float t120;
    private Float t130;
    private Float t140;
    private Float t150;
    private Float t160;
    private Float t170;
    private Float t180;
    private Float t190;
    private Float t200;
    private Float t210;
    private Float t220;
    private Float t230;
    private Float t240;
    private Float t250;
    private Float t260;
    private Float t270;
    private Float t280;
    private Float t290;
    private Float t300;
    private Float t310;
    private Float t320;
    private Float t330;
    private Float t340;
    private Float t350;
}
