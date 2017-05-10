package cn.edu.pku.app.familylibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jeanboy on 2017/5/10.
 */

public class DateUtil {

    public static final String FORMAT_HOURS_24_FULL = "yyyy-MM-dd HH:mm:ss";

    public static String dateFormat(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_HOURS_24_FULL);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return dateFormat.format(calendar.getTime());
    }

}
