package com.bupt.battery.form;

import com.bupt.battery.AO.ParamAO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class TaskSaveForm implements Serializable {
    private String token;
    private String taskName;
    private String taskType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;
    private String vehicleId;
    private List<ParamAO> paramAOList;
    private Integer expired;
    private String shopId;
}
