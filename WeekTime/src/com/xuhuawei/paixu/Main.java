package com.xuhuawei.paixu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Date currentDate=TimeUtils.getDateByStr("17-04-23");
        if(currentDate==null){
            System.out.println("时间格式不正确");
            return;
        }
        //将日期对象 转化为时间戳  这个时间戳从January 1, 1970, 00:00:00开始计时 到现在一共的毫秒值
        long currentMilliseconds=currentDate.getTime();
        long mondayMilliseconds=TimeUtils.getMondayDayOfWeek().getTime();
        long sundayMilliseconds=TimeUtils.getSunDayOfWeek().getTime();
        //如果指定的时间点 比本周的周一时间点大 并且比本周周日的时间点小 那么就在本周了
        if(currentMilliseconds>=mondayMilliseconds&&currentMilliseconds<=sundayMilliseconds){
            System.out.println("date is in this week");
        }else{
            System.out.println("date is not in this week");
        }
    }
}
