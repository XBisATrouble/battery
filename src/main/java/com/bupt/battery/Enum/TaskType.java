package com.bupt.battery.Enum;

public enum TaskType {
    Type1("日吞吐量直方图",1),Type2("耗电量直方图",2),Type3("温度概率分布图-最高温度",3),Type4("电流概率分布图",4),
    Type5("电压采集点分布图",5),Type6("温度采集点分布图",6),Type7("运行压差变化趋势图-按日统计",7),Type8("运行压差变化趋势图-按月统计",8),
    Type9("静态压差变化趋势图-按日统计",9),Type10("静态压差变化趋势图-按月统计",10),Type11("温度概率分布图-最低温度",11),Type12("回馈量",12);
    private String name;
    private Integer index;

    TaskType(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
