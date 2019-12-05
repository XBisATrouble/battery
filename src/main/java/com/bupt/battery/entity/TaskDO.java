package com.bupt.battery.entity;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "task")
@Data
public class TaskDO extends BaseEntity<Long> implements Serializable {
    @Column(name = "task_name")
    private String taskName;
    private String creator;
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
//    @Column(name = "start_time")
//    private Date startTime;
//    @Column(name = "end_time")
//    private Date endTime;
    private Long type;
    @Column(name = "param_value")
    private String paramValue;
    @Column(name = "result_type")
    private Integer resultType;
    @Column(name = "pic_result")
    private String picResult;
    private String status;
    private Integer expired;
    @Column(name = "csv_result")
    private String csvResult;

    private String reason;
}
