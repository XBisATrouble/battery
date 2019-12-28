package com.bupt.battery.form;

import lombok.Data;

import javax.persistence.Column;


@Data
public class PortForm {
    // 用于新增Port

    private Long id;
    private String portName;
    private Integer status;
    private Integer portNum;
    private String portDescription;
    private String ip;
}
