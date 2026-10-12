package com.nexon.core.util;

import com.adjust.sdk.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXDateUtil {
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_RESOURCES_FORMAT = "{%DATE}";

    public static Date addHour(String str, String str2, int i) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        try {
            calendar.setTime(new SimpleDateFormat(str2).parse(str));
            calendar.add(11, i);
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String changeHour(String str, String str2, int i) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(timeZone);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        if (NXStringUtil.isNull(str)) {
            simpleDateFormat.setTimeZone(timeZone);
            str = simpleDateFormat.format(calendar.getTime());
        }
        try {
            calendar.setTime(simpleDateFormat.parse(str));
            calendar.add(11, i);
            return simpleDateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String formattedDate(String str, String str2, String str3) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(str3);
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            date = new Date();
        }
        return simpleDateFormat2.format(date);
    }

    public static String formattedDate(String str, String str2, String str3, Locale locale) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(str3, locale);
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            date = new Date();
        }
        return simpleDateFormat2.format(date);
    }

    public static String formattedDate(Date date, String str) {
        return (date == null || NXStringUtil.isNull(str)) ? "" : new SimpleDateFormat(str).format(date);
    }

    public static String getCurrentTimeFormat(String str) {
        return new SimpleDateFormat(str).format(new Date(System.currentTimeMillis()));
    }

    public static String getCurrentTimeZoneOffset() {
        return String.valueOf(TimeZone.getDefault().getRawOffset() / Constants.ONE_HOUR);
    }

    public static String getTodayDateString(String str) {
        return new SimpleDateFormat(str).format(Calendar.getInstance().getTime());
    }
}
