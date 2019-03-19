package com.script972.currencyrate.utils;


import android.util.Log;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;

import java.util.Date;

import androidx.annotation.Nullable;

public class DateDiffUtils {

    /**
     * {@link String} constant of the tag
     */
    private static final String TAG = DateDiffUtils.class.getSimpleName();

    /**
     * Method which provide the checking if dates is reverse
     *
     * @param startDate instance of the {@link Date}
     * @param endDate   instance of the {@link Date}
     * @return {@link Boolean} value of the difference
     */
    private static boolean isReverseDates(@Nullable final Date startDate,
                                          @Nullable final Date endDate) {
        try {
            if (startDate.compareTo(endDate) > 0) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return false;
    }


    /**
     * Method which provide to get difference between dates
     *
     * @param startDate instance of the {@link Date}
     * @return instance of the {@link Period}
     */
    @Nullable
    public static Period getDifference(@Nullable final Date startDate) {
        return getDifference(startDate, new Date());
    }

    /**
     * Method which provide to get difference between dates
     *
     * @param startDate instance of the {@link Date}
     * @param endDate   instance of the {@link Date}
     * @return instance of the {@link Period}
     */
    @Nullable
    public static Period getDifference(@Nullable final Date startDate,
                                       @Nullable final Date endDate) {
        try {
            LocalDate startLocalDate = isReverseDates(startDate, endDate) ? asLocalDate(endDate) : asLocalDate(startDate);
            LocalDate endLocalDate = isReverseDates(startDate, endDate) ? asLocalDate(startDate) : asLocalDate(endDate);
            return Period.between(startLocalDate, endLocalDate);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }

    /**
     * Calls {@link #asLocalDate(Date, ZoneId)} with the system default time zone.
     */
    private static LocalDate asLocalDate(java.util.Date date) {
        return asLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link LocalDate} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    private static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
        try {
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
        } catch (Exception ex) {
            Log.e(TAG, "asLocalDate: ", ex);
        }
        return null;
    }

    public static int daysBetween(Date d1, Date d2) {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

}
