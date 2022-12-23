package com.example.simpleexpensemanager.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String getDateTime(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        Date date = calendar.getTime();
        return date.toString();
    }

    public static String getDate(long timestamp) {
        Date paymentDate = new Date();
        paymentDate.setTime(timestamp);
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        return format.format(paymentDate);
    }
}
