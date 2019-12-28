package com.bupt.battery.form;

import lombok.Data;

@Data
public class PortQueryForm {
    // 用于查询Port
    private Long id;
    private Integer pageNum;
    private Integer pageSize;
    private String portName;
    private Integer status;
}