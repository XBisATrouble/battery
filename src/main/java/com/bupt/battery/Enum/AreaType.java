package com.bupt.battery.Enum;

// 枚举中添加方法
public enum AreaType {
    Area1("bj","北京"),Area2("sh","上海"),Area3("jn","济南"),Area4("gz","广州");
    AreaType(String name, String value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String name;
    String value;
}
