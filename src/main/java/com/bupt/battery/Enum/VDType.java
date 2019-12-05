package com.bupt.battery.Enum;

public enum VDType {
    Running("运行压差变化趋势图"),Static("静态压差变化趋势图");
    private String label;

    VDType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
