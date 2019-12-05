package com.bupt.battery.Enum;

public enum BatteryType {
    Area1("type1","碳酸铁锂"),Area2("type2","氢镍电池");
    BatteryType(String name, String value) {
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
