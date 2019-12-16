package com.bupt.battery.AO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class MonitorQueryAO implements Serializable {
    private String monitorId;
    private String modelId;
    private String creator;
    private String createTime;
    private String postId;
    private String startTime;
    private String endTime;
    private String status;
//    private List<String> monitorIdList;
//    private List<String> modelIdList;
//    private List<String> creatorList;
//    private List<String> createTimeList;
//    private List<String> postIdList;
//    private List<String> startTimeList;
//    private List<String> endTimeList;
//    private List<String> statusList;
}
