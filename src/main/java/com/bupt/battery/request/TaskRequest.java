package com.bupt.battery.request;

import com.bupt.battery.Enum.TaskType;
import java.util.Date;
import lombok.Data;

@Data
public class TaskRequest {

    Date startTime;
    Date endTime;
    TaskType taskType;

}
