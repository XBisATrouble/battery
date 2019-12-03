package com.bupt.battery.Enum;

public enum TaskStatus {
    WAITING("未运行"),RUNNING("运行中"),COMPLETED("已完成");
    private String label;

    TaskStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
