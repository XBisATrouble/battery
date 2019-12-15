package com.bupt.battery.Enum;

public enum TaskType {
    Type1("日吞吐量直方图",1,"/home/python/type1.py"),Type2("耗电量曲线图",2,"/home/python/type2.py"),Type3("温度概率分布图-最高温度",3,"/home/python/type3.py"),Type4("电流概率分布图",4,"/home/python/type4.py"),
    Type5("电压采集点分布图",5,"/home/python/type5.py"),Type6("温度采集点分布图",6,"/home/python/type6.py"),Type7("运行压差变化趋势图-按日统计",7,"/home/python/type7.py"),Type8("运行压差变化趋势图-按月统计",8,"/home/python/type8.py"),
    Type9("静态压差变化趋势图-按日统计",9,"/home/python/type9.py"),Type10("静态压差变化趋势图-按月统计",10,"/home/python/type10.py"),Type11("温度概率分布图-最低温度",11,"/home/python/type11.py"),Type12("回馈量",12,"/home/python/type12.py");
    private String name;
    private Integer index;
    private String url;

    TaskType(String name, Integer index,String url) {
        this.name = name;
        this.index = index;
        this.url=url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
