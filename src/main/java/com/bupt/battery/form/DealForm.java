package com.bupt.battery.form;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class DealForm implements Serializable {
    public String time;
    public String type;
    public String area;
    public String code;
    public List<String> dealList;
}
