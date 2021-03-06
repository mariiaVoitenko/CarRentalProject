package ua.nure.voitenkom.SummaryTask4.util;

import org.slf4j.Logger;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Mariia Voitenko
 */
public class DateManager {

    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Timestamp getCurrentDate() {
        return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public static boolean isWastedToken(Timestamp signupDate) {
        Date now = new Date(new java.util.Date().getTime());
        Calendar cal = new GregorianCalendar();
        cal.setTime(signupDate);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date signupDatePlusOne = new Date(cal.getTime().getTime());
        return now.after(signupDatePlusOne);
    }

    public static java.util.Date parseDate(String date, Logger logger) {
        java.util.Date parsedDate = null;
        try {
            parsedDate = SIMPLE_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            logger.error("Can't parse date");
        }
        return parsedDate;
    }

    public static long getDaysCount(String start, String end) {
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        startCalendar.set(getYear(start), getMonth(start), getDay(start));
        endCalendar.set(getYear(end), getMonth(end), getDay(end));
        java.util.Date startDate = startCalendar.getTime();
        java.util.Date endDate = endCalendar.getTime();
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long diffTime = endTime - startTime;
        return diffTime / (1000 * 60 * 60 * 24);
    }

    private static int getYear(String date) {
        return Integer.parseInt(date.substring(0, 4));
    }

    private static int getMonth(String date) {
        return Integer.parseInt(date.substring(5, 7));
    }

    private static int getDay(String date) {
        return Integer.parseInt(date.substring(8, 10));
    }


    public static String timestampToString(Timestamp timestamp) {
        return SIMPLE_DATE_FORMAT.format(timestamp);
    }

    public static String dateToString(java.util.Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

}
