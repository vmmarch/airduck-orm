package com.airduck.commons;

import lombok.SneakyThrows;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期操作工具类
 *
 * @author airduck
 * Create time 2022/3/30
 */
public class DateUtils {

    /**
     * 当前时间在是否在某个日期范围之内
     */
    public static boolean withinRange(Date min, Date max) {
        return withinRange(min, new Date(), max);
    }

    /**
     * 指定时间在是否在某个日期时间范围之内
     */
    public static boolean withinRange(Date min, Date date, Date max) {
        return lteq(min, date) && gteq(max, date);
    }

    /**
     * x是否大于y
     */
    public static boolean gteq(Date x, Date y) {
        return x.compareTo(y) >= 0;
    }

    /**
     * x是否小于y
     */
    public static boolean lteq(Date x, Date y) {
        return x.compareTo(y) <= 0;
    }

    /**
     * 计算两个日期相差多少天
     *
     * @param x 日期1
     * @param y 日期2
     * @return 相差天数
     */
    public static int betweenDays(Date x, Date y) {
        return Math.abs((int) ((y.getTime() - x.getTime()) / (1000 * 60 * 60 * 24)));
    }

    /**
     * 计算两个日期相差多少小时
     *
     * @param x 日期1
     * @param y 日期2
     * @return 相差小时
     */
    public static int betweenHours(Date x, Date y) {
        return Math.abs((int) ((y.getTime() - x.getTime()) / (1000 * 60 * 60)));
    }

    /**
     * 计算两个日期相差多少分钟
     *
     * @param x 日期1
     * @param y 日期2
     * @return 相差分钟
     */
    public static int betweenMinutes(Date x, Date y) {
        return Math.abs((int) ((y.getTime() - x.getTime()) / (1000 * 60)));
    }

    /**
     * 日期格式化成执行类型, 格式化类型默认：yyyy-MM-dd HH:mm:ss
     *
     * @param date 需要格式化的日期
     * @return 格式化后的字符串
     */
    public static String format(Date date) {
        return format("yyyy-MM-dd HH:mm:ss", date);
    }

    /**
     * 日期格式化成执行类型
     *
     * @param pattern 日期格式化表达式，比如：yyyy-MM-dd
     * @param date    需要格式化的日期
     * @return 格式化后的字符串
     */
    @SneakyThrows
    public static String format(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * string转日期, 格式化类型默认：yyyy-MM-dd HH:mm:ss
     *
     * @param date    日期字符串
     * @return 格式化后的Date对象
     */
    @SneakyThrows
    public static Date parse(String date) {
        return parse("yyyy-MM-dd HH:mm:ss", date);
    }

    /**
     * string转日期
     *
     * @param pattern 日期格式化表达式，比如：yyyy-MM-dd
     * @param date    日期字符串
     * @return 格式化后的Date对象
     */
    @SneakyThrows
    public static Date parse(String pattern, String date) {
        return new SimpleDateFormat(pattern).parse(date);
    }

    /**
     * 日期增加计算
     *
     * @param date     计算的日期
     * @param number   增加的天数或者是其他
     * @param timeUnit 计算类型
     * @return 计算后的日期
     */
    public static Date plus(Date date, int number, TimeUnit timeUnit) {
        DateTime dateTime = new DateTime(date);
        return switch (timeUnit) {
            case SECOND -> dateTime.plusSeconds(number).toDate();
            case MINUTE -> dateTime.plusMinutes(number).toDate();
            case HOUR -> dateTime.plusHours(number).toDate();
            case DAY -> dateTime.plusDays(number).toDate();
            case MONTH -> dateTime.plusMonths(number).toDate();
            case YEAR -> dateTime.plusYears(number).toDate();
        };
    }

    /**
     * 日期减少计算
     *
     * @param date     计算的日期
     * @param number   减少的天数或者是其他
     * @param timeUnit 计算类型
     * @return 计算后的日期
     */
    public static Date minus(Date date, int number, TimeUnit timeUnit) {
        DateTime dateTime = new DateTime(date);
        return switch (timeUnit) {
            case SECOND -> dateTime.minusSeconds(number).toDate();
            case MINUTE -> dateTime.minusMinutes(number).toDate();
            case HOUR -> dateTime.minusHours(number).toDate();
            case DAY -> dateTime.minusDays(number).toDate();
            case MONTH -> dateTime.minusMonths(number).toDate();
            case YEAR -> dateTime.minusYears(number).toDate();
        };
    }

}
