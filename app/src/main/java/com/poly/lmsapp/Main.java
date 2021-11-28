package com.poly.lmsapp;

import android.os.Build;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.poly.lmsapp.commons.utils.DateTimeUtils;
import com.poly.lmsapp.commons.utils.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;

public class Main {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) throws ParseException {
//        Date date1 = DateTimeUtils.parseDate("2021-11-14 16:03:47", DateTimeUtils.serverDate);
//        Date date2 = DateTimeUtils.parseDate("2021-11-14 16:15:47", DateTimeUtils.serverDate);
//        System.out.println((date2.getTime() - date1.getTime()) / 60000);
        System.out.println(DateTimeUtils.toDateFormat("2021/11/28 20:44:50", DateTimeUtils.SERVER_DATE, DateTimeUtils.TIME_DATE));
        Date endDate = DateTimeUtils.parseDate("2021/11/16 11:31:35", DateTimeUtils.SERVER_DATE);
        System.out.println(DateTimeUtils.afterNow(endDate));
        Base64.Encoder enc = Base64.getEncoder();
        Base64.Decoder dec = Base64.getDecoder();
        String str = "77+9x6s=";

        // encode data using BASE64
        String encoded = enc.encodeToString(str.getBytes());
        System.out.println("encoded value is \t" + encoded);

        // Decode data
        System.out.println("decoded value is \t" + dec.decode("rO0ABXNyAAxqYXZhLmlvLkZpbGUELaRFDg3k/wMAAUwABHBhdGh0ABJMamF2YS9sYW5nL1N0cmluZzt4cHQAOy9zdG9yYWdlL2VtdWxhdGVkLzAvRG93bmxvYWQvZ2VuU2lnbmNvbnRyYWN0UGRmRmlsZSAoMSkucGRmdwIAL3g="));
        System.out.println("original value is \t" + str);
        Runnable runnable = () -> {
            Date event_date = DateTimeUtils.parseDate("28/11/2021 16:52:00", DateTimeUtils.SERVER_DATE_2);
            if (DateTimeUtils.afterNow(event_date)) {
                long diff = event_date.getTime() - new Date().getTime();
                long minutes = diff / (60 * 1000) % 60;
                long seconds = diff / 1000 % 60;
                System.out.println(minutes + " : " + seconds);
            } else {
                System.out.println("Đã hết giờ");
            }
        };
        Date event_date = DateTimeUtils.parseDate("30/1/2022 20:25:00", DateTimeUtils.SERVER_DATE_2);
        long diff = event_date.getTime() - new Date().getTime();
        long seconds = diff / 1000 % 60;
        long minutes = diff / (60 * 1000) % 60;
        long hours = diff / (60 * 60 * 1000) % 24;
        long day = diff / (24 * 60 * 60 * 1000);
        System.out.println("Thời gian còn lại: " + day + " ngày, " + hours + ":" + minutes + ":" + seconds);
//        Date event_date = DateTimeUtils.parseDate("28/11/2021 16:52:00", DateTimeUtils.SERVER_DATE_2);
//        while (DateTimeUtils.afterNow(event_date)){
//            new Thread(runnable).start();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

}
