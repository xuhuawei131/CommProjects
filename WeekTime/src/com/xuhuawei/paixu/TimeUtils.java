package com.xuhuawei.paixu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/26 0026.
 */
public class TimeUtils {



    /**
     * 获取自定字符串日期的Date对象
     * @param dateStr
     * @return
     */
    public static Date getDateByStr(String dateStr) {
        String timeFormat = converTimeFormat(dateStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(timeFormat);
            return date;

        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 转化时间格式07-04-26 转化为 2017-04-26
     *
     * @return
     */
    private static String converTimeFormat(String time) {
        //如果时间是空
        if (time == null || time.trim().length() == 0) {
            return null;
        }
        StringBuffer buffer = new StringBuffer(time);
        buffer.insert(0, 20);
        return buffer.toString();
    }

    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static Date getMondayDayOfWeek() {
        Calendar c = Calendar.getInstance();
        int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayofweek == 0)
            dayofweek = 7;
        c.add(Calendar.DATE, -dayofweek + 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return c.getTime();
    }

    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static Date getSunDayOfWeek() {
        Calendar c = Calendar.getInstance();
        int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayofweek == 0)
            dayofweek = 7;
        c.add(Calendar.DATE, -dayofweek + 7);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }


}
