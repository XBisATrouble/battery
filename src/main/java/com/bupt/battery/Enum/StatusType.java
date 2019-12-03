package com.bupt.battery.Enum;

public enum StatusType {
    Ready("已就绪"),Process("运行中"),Success("已结束"),Fail("失败");
    private String name;

    StatusType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
