package com.bupt.battery.AO;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class TableAO implements Serializable {
    private List<String> timeList;
    private List<String> areaList;
    private List<String> typeList;
    private List<List<Object>> amountList;
}
