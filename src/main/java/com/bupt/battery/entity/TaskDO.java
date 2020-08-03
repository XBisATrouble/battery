package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

// 任务表
@Entity
@Table(name = "task")
@Data
public class TaskDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "task_name")
    private String taskName; // 任务名
    private String creator; // 创建人
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime; // 创建日期
//    @Column(name = "start_time")
//    private Date startTime;
//    @Column(name = "end_time")
//    private Date endTime;
    private Long type; // 创建类型
    @Column(name = "param_value")
    private String paramValue; // 参数值
    @Column(name = "result_type")
    private Integer resultType; // 结果类型
    @Column(name = "pic_result")
    private String picResult; // 图片结果
    private String status; // 任务状态
    private Integer expired; // 周期（30天、60天）
    @Column(name = "csv_result")
    private String csvResult; // csv结果

    private String reason; // 任务失败原因
}