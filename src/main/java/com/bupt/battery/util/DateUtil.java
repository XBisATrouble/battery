package com.bupt.battery.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        //格式化日期
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd",
            new Locale("zh", "CN"));
        Date date = null;
        try {
            date = format.parse(format.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd",
            new Locale("zh", "CN"));
        Date date = null;
        try {
            date = format.parse(format.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static void main(String[] args) {
        System.out.println(getFirstDayOfMonth(2019,10));

    }

}
