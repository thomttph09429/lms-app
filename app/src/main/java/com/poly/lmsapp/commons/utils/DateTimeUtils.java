package com.poly.lmsapp.commons.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
    //    "2021-11-14 16:03:47"
    public static final String SERVER_DATE = "yyyy/MM/dd HH:mm:ss";
    public static final String SERVER_DATE_2 = "dd/MM/yyyy HH:mm:ss";
    public static final String BIRTH_DATE = "dd/MM/yyyy";
    public static final String TIME_DATE = "HH:mm dd/MM/yyyy";

    public static Date parseDate(String s, String pattern) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String toDateFormat(String s, String to, String from) {
        try {
            Date date = parseDate(s, to);
            if (date != null) {
                return new SimpleDateFormat(from).format(date);
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean after(Date d1, Date d2) {
        if (d1 != null && d2 != null)
            return d1.after(d2);
        return false;
    }

    public static boolean nowAfter(Date d2) {
        if (d2 != null)
            return new Date().after(d2);
        return false;
    }

    public static boolean afterNow(Date d1) {
        if (d1 != null)
            return d1.after(new Date());
        return false;
    }

    public static String diffDate(Date event_date){
        long diff = event_date.getTime() - new Date().getTime();
        long seconds = diff / 1000 % 60;
        long minutes = diff / (60 * 1000) % 60;
        long hours = diff / (60 * 60 * 1000) % 24;
        long day = diff / (24 * 60 * 60 * 1000);
        System.out.println("Thời gian còn lại: " + day + " ngày, " + hours + ":" + minutes + ":" + seconds);
        return  day + " ngày, " + hours + ":" + minutes + ":" + seconds;
    }

    public static long apartDate(Date event_date){
        return  event_date.getTime() - new Date().getTime();
    }
}
