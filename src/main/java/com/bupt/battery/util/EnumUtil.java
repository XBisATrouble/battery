package com.bupt.battery.util;

import com.bupt.battery.Enum.AreaType;
import com.bupt.battery.Enum.BatteryType;
import com.bupt.battery.Enum.TaskType;

public class EnumUtil {


    public static TaskType getTaskType(Integer index)
    {
        for(TaskType taskType:TaskType.values())
        {
            if(taskType.getIndex()==index)
            {
                return taskType;
            }
        }
        return null;
    }
    public static AreaType getAreaType(String value)
    {
        for(AreaType areaType:AreaType.values())
        {
            if(areaType.getValue().equals(value))
            {
                return areaType;
            }
        }
        return null;
    }
    public static BatteryType getBatteryType(String value)
    {
        for(BatteryType batteryType:BatteryType.values())
        {
            if(batteryType.getValue().equals(value))
            {
                return batteryType;
            }
        }
        return null;
    }
}
