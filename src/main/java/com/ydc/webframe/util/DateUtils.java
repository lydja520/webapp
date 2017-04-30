package com.ydc.webframe.util;

import com.ydc.webframe.common.StartDateAndEndDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ShenYunjie on 2015/12/18.
 */
public class DateUtils {

    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    public static String formatDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        return sdf.format(date);
    }

    public static Date StringToDate(String target) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            return sdf.parse(target);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date StringToDateTime(String target) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        try {
            return sdf.parse(target);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date dateConvert(Date date){
        return StringToDate(formatDate(date));
    }

    public static Date DateTimeConvert(Date date){
        return StringToDateTime(formatDateTime(date));
    }


    /**
     * 获取当前时间的上个周的起止时间（此方法适用于一周的第一天是星期一）
     *
     * @param
     * @return
     */
    public static StartDateAndEndDate lastWeekStartAndEnd() {
        StartDateAndEndDate startDateAndEndDate = new StartDateAndEndDate();

        Calendar nowCal = Calendar.getInstance();
        Calendar nowCal1 = (Calendar) nowCal.clone();
        Calendar nowCal2 = (Calendar) nowCal.clone();
        // 一周第一天是否为星期天
        boolean isFirstSunday = (nowCal.getFirstDayOfWeek() == Calendar.SUNDAY);
        // 获取周几
        int dayOfWeek = nowCal.get(Calendar.DAY_OF_WEEK);
        // 若一周第一天为星期天，则-1
        if (isFirstSunday) {
            dayOfWeek = dayOfWeek - 1;
            if (dayOfWeek == 0) {
                dayOfWeek = 7;
            }
        }
        nowCal1.add(Calendar.DATE, -dayOfWeek - 6);
        Date startDate = nowCal1.getTime();
        nowCal2.add(Calendar.DATE, -dayOfWeek);
        Date endDate = nowCal2.getTime();
        startDate = StringToDateTime(formatDate(startDate) + " 00:00:00");
        endDate = StringToDateTime(formatDate(endDate) + " 23:59:59");

        startDateAndEndDate.setStartDate(startDate);
        startDateAndEndDate.setEndDate(endDate);
        return startDateAndEndDate;
    }

    /**
     * 获取本周的起止时间
     *
     * @return
     */
    public static StartDateAndEndDate thisWeekStartAndEnd() {
        StartDateAndEndDate startDateAndEndDate = new StartDateAndEndDate();
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 2;
        cal.add(Calendar.DATE, -dayOfWeek);
        Date startDate = cal.getTime();
        startDate = DateUtils.StringToDateTime(DateUtils.formatDate(startDate) + " 00:00:00");
        cal.add(Calendar.DATE, 6);
        Date endDate = cal.getTime();
        endDate = DateUtils.StringToDateTime(DateUtils.formatDate(endDate) + " 23:59:59");

        startDateAndEndDate.setStartDate(startDate);
        startDateAndEndDate.setEndDate(endDate);
        return startDateAndEndDate;
    }

    /**
     *获取本天的开始结束时间
     * @return
     */
    public static StartDateAndEndDate thisDayStartAndEnd() {
        StartDateAndEndDate startDateAndEndDate = new StartDateAndEndDate();
        Date now = new Date();
        String nowDate = formatDate(now);
        String startDateStr = nowDate + " 00:00:00";
        String endDateStr = nowDate + " 23:59:59";
        Date startDate = StringToDateTime(startDateStr);
        Date endDate = StringToDateTime(endDateStr);
        startDateAndEndDate.setStartDate(startDate);
        startDateAndEndDate.setEndDate(endDate);
        return startDateAndEndDate;
    }


}
