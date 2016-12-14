package com.guidovezzoni.mvplibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by guido on 11/12/16.
 */

public class DateHelper {
    public static final String TIME_FORMAT = "dd MMMM 'at' HH:mm";
    public static final String YESTERDAY_TIME_FORMAT = "'Yesterday at' HH:mm";
    public static final String TIME_FORMAT_OF = "dd 'of' MMMM yyyy";

    public static final String DAY_OF_WEEK_TEXT_FORMAT = "EEEE dd";
    public static final String TIME_HOURS_ONLY_FORMAT = "hh:mma";
    public static final String MONTH_OF_YEAR_FORMAT = "MMM";

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static String getFormattedDate(String format, Date date) {
        if (date==null){
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        String time = dateFormat.format(date);
        if (time.startsWith("0")) {
            time = time.substring(1);
        }
        return time;
    }
}
