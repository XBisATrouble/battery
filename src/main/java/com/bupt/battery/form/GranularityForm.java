package com.bupt.battery.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class GranularityForm implements Serializable {
    private Long id;
    private Integer pageNum;
    private Integer pageSize;
    private Integer vehicleId;
    private String vin;
    private String plateNumber;
    private String carmaker;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private String time;
    private String area;
    private String type;

}
