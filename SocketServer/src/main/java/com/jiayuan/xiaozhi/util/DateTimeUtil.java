package com.jiayuan.xiaozhi.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类，进行各种日期时间格式的转化以及格式化
 * 
 * @author jerry
 * @version v1.0.0
 */
public class DateTimeUtil {

    // /
    // 定义时间日期显示格式
    // /
    private final static String DATE_FORMAT = "yyyy-MM-dd";

    private final static String DATE_FORMAT_CN = "yyyy年MM月dd日";

    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final static String TIME_FORMAT_CN = "yyyy年MM月dd日 HH:mm:ss";

    private final static String MONTH_FORMAT = "yyyy-MM";

    public final static String MONTH_FORMAT_CN = "yyyy年MM月";

    private final static String DAY_FORMAT = "yyyyMMdd";

    public final static String TIME_HOURMINUTE_FORMAT = "HH:mm";

    public final static String DATE_HOUR_FORMAT = "yyyy-MM-dd HH:mm";

    // private final static String TIME_FORMAT_MILLI =
    // "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 取得当前系统时间，返回java.util.Date类型
     * 
     * @see Date
     * @return java.util.Date 返回服务器当前系统时间
     */
    public static Date getCurrDate() {
        return new Date();
    }

    /**
     * 取得时间戳
     *
     * @see Timestamp
     * @return java.sql.Timestamp 系统时间戳
     */
    public static Timestamp getCurrTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp getTimestampByString(String str){
        return new Timestamp(getFormatDate(str).getTime());
    }

    /**
     * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15
     *
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(Date, String)
     * @return String 返回格式化后的日期，默认格式为为yyyy-MM-dd，如2006-02-15
     */
    public static String getFormatDate(Date currDate) {
        return getFormatDate(currDate, DATE_FORMAT);
    }

    /**
     * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15
     *
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(Date)
     * @return Date 返回格式化后的日期，默认格式为为yyyy-MM-dd，如2006-02-15
     */
    public static Date getFormatDateToDate(Date currDate) {
        return getFormatDate(getFormatDate(currDate));
    }

    /**
     * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日
     *
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(Date, String)
     * @return String 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日
     */
    public static String getFormatDate_CN(Date currDate) {
        return getFormatDate(currDate, DATE_FORMAT_CN);
    }

    /**
     * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日
     *
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate_CN(String)
     * @return Date 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日
     */
    public static Date getFormatDateToDate_CN(Date currDate) {
        return getFormatDate_CN(getFormatDate_CN(currDate));
    }

    /**
     * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15
     *
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(String, String)
     * @return Date 返回格式化后的日期，默认格式为yyyy-MM-dd，如2006-02-15
     */
    public static Date getFormatDate(String currDate) {
        return getFormatDate(currDate, DATE_FORMAT);
    }

    /**
     * 得到格式化后的日期，格式为yyyyMMdd，如20060215
     *
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(String, String)
     * @return Date 返回格式化后的日期，默认格式为yyyyMMdd，如20060215
     */
    public static Date getFormatDayDate(String currDate) {
        return getFormatDate(currDate, DAY_FORMAT);
    }

    /**
     * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日
     *
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(String, String)
     * @return 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日
     */
    public static Date getFormatDate_CN(String currDate) {
        return getFormatDate(currDate, DATE_FORMAT_CN);
    }

    /**
     * 根据格式得到格式化后的日期
     *
     * @param currDate
     *            要格式化的日期
     * @param format
     *            日期格式，如yyyy-MM-dd
     * @see SimpleDateFormat#format(Date)
     * @return String 返回格式化后的日期，格式由参数<code>format</code>
     *         定义，如yyyy-MM-dd，如2006-02-15
     */
    public static String getFormatDate(Date currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            return dtFormatdB.format(currDate);
        }
    }

    /**
     * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     *
     * @param currDate
     *            要格式化的时间
     * @see #getFormatDateTime(Date, String)
     * @return String 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     */
    public static String getFormatDateTime(Date currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT);
    }

    /**
     * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     *
     * @param currDate
     *            要格式环的时间
     * @see #getFormatDateTime(String)
     * @return Date 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     */
    public static Date getFormatDateTimeToTime(Date currDate) {
        return getFormatDateTime(getFormatDateTime(currDate));
    }

    /**
     * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     *
     * @param currDate
     *            要格式化的时间
     * @see #getFormatDateTime(String, String)
     * @return Date 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     */
    public static Date getFormatDateTime(String currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT);
    }

    /**
     * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     *
     * @param currDate
     *            要格式化的时间
     * @see #getFormatDateTime(Date, String)
     * @return String 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     */
    public static String getFormatDateTime_CN(Date currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT_CN);
    }

    /**
     * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     *
     * @param currDate
     *            要格式化的时间
     * @see #getFormatDateTime_CN(String)
     * @return Date 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     */
    public static Date getFormatDateTimeToTime_CN(Date currDate) {
        return getFormatDateTime_CN(getFormatDateTime_CN(currDate));
    }

    /**
     * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     *
     * @param currDate
     *            要格式化的时间
     * @see #getFormatDateTime(String, String)
     * @return Date 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     */
    public static Date getFormatDateTime_CN(String currDate) {
        return getFormatDateTime(currDate, TIME_FORMAT_CN);
    }

    /**
     * 根据格式得到格式化后的时间
     *
     * @param currDate
     *            要格式化的时间
     * @param format
     *            时间格式，如yyyy-MM-dd HH:mm:ss
     * @see SimpleDateFormat#format(Date)
     * @return String 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
     */
    public static String getFormatDateTime(Date currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(TIME_FORMAT);
            return dtFormatdB.format(currDate);
        }
    }

    /**
     * 根据格式得到格式化后的日期
     *
     * @param currDate
     *            要格式化的日期
     * @param format
     *            日期格式，如yyyy-MM-dd
     * @see SimpleDateFormat#parse(String)
     * @return Date 返回格式化后的日期，格式由参数<code>format</code>定义，如yyyy-MM-dd，如2006-02-15
     */
    public static Date getFormatDate(String currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            try {
                return dtFormatdB.parse(currDate);
            } catch (Exception ex) {}
        }
        return null;
    }

    /**
     * 根据格式得到格式化后的时间
     *
     * @param currDate
     *            要格式化的时间
     * @param format
     *            时间格式，如yyyy-MM-dd HH:mm:ss
     * @see SimpleDateFormat#parse(String)
     * @return Date 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
     */
    public static Date getFormatDateTime(String currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(TIME_FORMAT);
            try {
                return dtFormatdB.parse(currDate);
            } catch (Exception ex) {}
        }
        return null;
    }

    /**
     * 得到格式化后的当前系统日期，格式为yyyy-MM-dd，如2006-02-15
     *
     * @see #getFormatDate(Date)
     * @return String 返回格式化后的当前服务器系统日期，格式为yyyy-MM-dd，如2006-02-15
     */
    public static String getCurrDateStr() {
        return getFormatDate(getCurrDate());
    }

    /**
     * 得到格式化后的当前系统时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     *
     * @see #getFormatDateTime(Date)
     * @return String 返回格式化后的当前服务器系统时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15
     *         15:23:45
     */
    public static String getCurrDateTimeStr() {
        return getFormatDateTime(getCurrDate());
    }

    /**
     * 得到格式化后的当前系统日期，格式为yyyy年MM月dd日，如2006年02月15日
     *
     * @see #getFormatDate(Date, String)
     * @return String 返回当前服务器系统日期，格式为yyyy年MM月dd日，如2006年02月15日
     */
    public static String getCurrDateStr_CN() {
        return getFormatDate(getCurrDate(), DATE_FORMAT_CN);
    }

    /**
     * 得到格式化后的当前系统时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日 15:23:45
     *
     * @see #getFormatDateTime(Date, String)
     * @return String 返回格式化后的当前服务器系统时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日
     *         15:23:45
     */
    public static String getCurrDateTimeStr_CN() {
        return getFormatDateTime(getCurrDate(), TIME_FORMAT_CN);
    }

    /**
     * 得到系统当前日期的前或者后几天
     *
     * @param iDate
     *            如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
     * @see Calendar#add(int, int)
     * @return Date 返回系统当前日期的前或者后几天
     */
    public static Date getDateBeforeOrAfter(int iDate) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, iDate);
        return cal.getTime();
    }

    /**
     * 得到日期的前或者后几天
     *
     * @param iDate
     *            如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
     * @see Calendar#add(int, int)
     * @return Date 返回参数<code>curDate</code>定义日期的前或者后几天
     */
    public static Date getDateBeforeOrAfter(Date curDate, int iDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DAY_OF_MONTH, iDate);
        return cal.getTime();
    }

    /**
     * 得到格式化后的月份，格式为yyyy-MM，如2006-02
     *
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(Date, String)
     * @return String 返回格式化后的月份，格式为yyyy-MM，如2006-02
     */
    public static String getFormatMonth(Date currDate) {
        return getFormatDate(currDate, MONTH_FORMAT);
    }

    /**
     * 得到格式化后的日，格式为yyyyMMdd，如20060210
     *
     * @param currDate
     *            要格式化的日期
     * @see #getFormatDate(Date, String)
     * @return String 返回格式化后的日，格式为yyyyMMdd，如20060210
     */
    public static String getFormatDay(Date currDate) {
        return getFormatDate(currDate, DAY_FORMAT);
    }

    /**
     * 得到格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     *
     *
     * @see Calendar#getMinimum(int)
     * @see #getFormatDate(Date, String)
     * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     */
    public static String getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    /**
     * 得到格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     *
     * @param currDate
     *            要格式化的日期
     * @see Calendar#getMinimum(int)
     * @see #getFormatDate(Date, String)
     * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
     */
    public static String getFirstDayOfMonth(Date currDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    public static Date getDateBeforeOrAfterMonths(Date currDate, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 得到日期的前或者后几小时
     *
     * @param iHour
     *            如果要获得前几小时日期，该参数为负数； 如果要获得后几小时日期，该参数为正数
     * @see Calendar#add(int, int)
     * @return Date 返回参数<code>curDate</code>定义日期的前或者后几小时
     */
    public static Date getDateBeforeOrAfterHours(Date curDate, int iHour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.HOUR_OF_DAY, iHour);
        return cal.getTime();
    }

    /**
     * 得到日期的前或者后若干分钟
     *
     * @param curDate
     *            如果要获得前若干分钟日期，该参数为负数； 如果要获得后若干分钟日期，该参数为正数
     * @see Calendar#add(int, int)
     * @return Date 返回参数<code>curDate</code>定义日期的前或者后若干分钟
     */
    public static Date getDateBeforeOrAfterMinutes(Date curDate, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 得到日期的小时
     * 
     * @param date
     * @return
     */
    public static int getHourOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 通过日期和小时组合成新日期
     * 
     * @param date
     * @param hours
     * @return
     */
    public static Date getDateContainHour(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, hours);
        return calendar.getTime();
    }

    public static String formatFormTimestamp(Timestamp t) {
        return getFormatDateTime(new Date(t.getTime()));
    }
}