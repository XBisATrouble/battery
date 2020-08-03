package com.bupt.battery.form;

import lombok.Data;

import java.io.Serializable;

// 显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
@Data
public class DealForm implements Serializable {
    public String time;
    public String type;
    public String area;
    //public String code;
    public String shopId;
    //public List<String> dealList;
}
