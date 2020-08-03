package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// 任务类型表
@Data
@Entity
@Table(name = "task_type")
public class TaskTypeDO extends BaseEntity<Long> {
    private String type; // 任务类型名称（1日吞吐量、2耗电量、3温度概率分布、4电流概率分布、5电压采集点分布、6温度采集点分布、7运行压差变化趋势、8静态压差变化趋势）
    @Column(name = "param_name_code")
    private String paramNameCode; // 任务参数名code列表(开始时间,结束时间, 车辆编号)
    @Column(name = "param_name")
    private String paramName; // 任务参数名列表(startTime，endTime，vehicleId)
    @Column(name = "param_type")
    private String paramType; // 任务参数类型列表（String,Date1(时间类型精确到日，2019-08-11)，Date2（时间类型精确到月，2019-08），Date3（时间类型精确到日可选多个，2019-08-11|2019-08-12|2019-08-13））
    private String remark; // 备注
}
