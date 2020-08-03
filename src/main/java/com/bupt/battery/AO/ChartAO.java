package com.bupt.battery.AO;

import lombok.Data;

import java.io.Serializable;

// 应用对象，在Web层与Service层之间抽象的复用对象模型， 极为贴近展示层，复用度不高。
@Data
public class ChartAO implements Serializable {
    private String time;
    private String area;
    private String type;
    private Integer amount;
    private Boolean isComplete;
    private String deal;
}
