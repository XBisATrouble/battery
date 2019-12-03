package com.bupt.battery.form;

import com.bupt.battery.AO.ParamAO;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class TaskQueryForm implements Serializable {
    private Long taskId;
    private Integer pageNum;
    private Integer pageSize;
    private String taskName;
    private String taskType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private String vehicleId;
    private List<ParamAO> paramAOList;
    private String shopId;
}
