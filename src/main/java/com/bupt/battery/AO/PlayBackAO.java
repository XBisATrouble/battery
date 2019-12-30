package com.bupt.battery.AO;

import lombok.Data;

import java.util.List;

@Data
public class PlayBackAO {
    private int len;
    private List<String> dataTime;
    private List<String> result;
}
