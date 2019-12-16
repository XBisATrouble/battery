package com.bupt.battery.AO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ParamListAO implements Serializable {
    String taskName;
    String taskType;
    Integer expired;
    String vehicleId;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    Date startTime;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    Date endTime;
    List<ParamAO> paramAOList;
}
