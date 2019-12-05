package com.bupt.battery.AO;

import java.io.Serializable;
import lombok.Data;

@Data
public class ChartAO implements Serializable {
    private String time;
    private String area;
    private String type;
    private Integer amount;
    private Boolean isComplete;
    private String deal;
}
