package com.bupt.battery.AO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MonitorCopyAO implements Serializable {
    //private String modelName;
    //private String modelId;
    private String portId;
    private List<String> dataContainer;
}
