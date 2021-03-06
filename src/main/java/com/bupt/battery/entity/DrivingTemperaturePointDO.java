package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

// 预处理后采集点温度表
@Data
@Entity
@Table(name = "driving_temperature_point")
public class DrivingTemperaturePointDO extends BaseEntity<Long> {
    private String vehicleId; // 车辆编号
    private Date time; // 时间
    private float T1; // 温度采集点1
    private float T2;
    private float T3;
    private float T4;
    private float T5;
    private float T6;
    private float T7;
    private float T8;
    private float T9;
    private float T10;
    private float T11;
    private float T12;
    private float T13;
    private float T14;
    private float T15;
    private float T16;
    private float T17;
    private float T18;
    private float T19;
    private float T20;
    private float T21;
    private float T22;
    private float T23;
    private float T24;
    private float T25;
    private float T26;
    private float T27;
    private float T28;
    private float T29;
    private float T30;
    private float T31;
    private float T32;
    private float T33;
    private float T34;
    private float T35;
    private float T36;
    private float T37;
    private float T38;
    private float T39;
    private float T40;
    private float T41;
    private float T42;
    private float T43;
    private float T44;
    private float T45;
    private float T46;
    private float T47;
    private float T48;
    private float T49;
    private float T50;
    private float T51;
    private float T52;
    private float T53;
    private float T54;
    private float T55;
    private float T56;
    private float T57;
    private float T58;
    private float T59;
    private float T60;
    private float T61;
    private float T62;
    private float T63;
    private float T64;
    private float T65;
    private float T66;
    private float T67;
    private float T68;
    private float T69;
    private float T70;
    private float T71;
    private float T72;
    private float T73;
    private float T74;
    private float T75;
    private float T76;
    private float T77;
    private float T78;
    private float T79;
    private float T80;
    private float T81;
    private float T82;
    private float T83;
    private float T84;
    private float T85;
    private float T86;
    private float T87;
    private float T88;
    private float T89;
    private float T90;
    private float T91;
    private float T92;
    private float T93;
    private float T94;
    private float T95;
    private float T96;
    private float T97;
    private float T98;
    private float T99;
    private float T100;
}
