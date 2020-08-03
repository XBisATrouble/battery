package com.bupt.battery.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class GranularityForm implements Serializable {
    private Long id;
    private Integer pageNum;
    private Integer pageSize;
    private String vehicleId;
    private String vin;
    private String plateNumber;
    private String carmaker;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;
    private String time;
    private String area;
    private String type;

}
