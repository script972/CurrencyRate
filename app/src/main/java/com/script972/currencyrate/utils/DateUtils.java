package com.script972.currencyrate.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private final static String DATE_TIME_PATTERN = "EE MMM d 'at' h:mma";
    private final static String DATE_PATTERN_API = "yyyyMMdd";
    private final static String DATE_PATTERN = "yyyy MM dd";
    private final static String DATE_CHART_PATTERN = "MM dd";
    private final static String DATE_PATTERN_PARSE_API = "dd.MM.yyyy";

    public static String soutDateWithTime(long value) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN, Locale.US);
        return simpleDateFormat.format((value));
    }

    public static String soutDate(long value) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.US);
        return simpleDateFormat.format((value));
    }

    public static String soutDateForApi(long value) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN_API, Locale.US);
        return simpleDateFormat.format((value));
    }

    public static long entityDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN_PARSE_API, Locale.US);
        try {
            long value = simpleDateFormat.parse((date)).getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setTimeInMillis(value);
            calendar.set(Calendar.HOUR, 10);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static long entityDateFromCalendar(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN_PARSE_API, Locale.US);
        return entityDate(simpleDateFormat.format(calendar.getTime()));
    }

    public static Long roundDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * Date format for abscissa line (X)
     *
     * @return
     */
    public static DateFormat getChartDataFormat() {
        return new SimpleDateFormat(DATE_CHART_PATTERN, Locale.US);
    }

    public static Date chartValueFromStr(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy mm dd", Locale.US);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Calendar.getInstance().getTime();
    }
}
