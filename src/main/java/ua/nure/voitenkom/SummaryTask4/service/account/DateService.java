package ua.nure.voitenkom.SummaryTask4.service.account;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateService {

    public static Timestamp getCurrentDate() {
        return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public static boolean isWastedToken(Timestamp signupDate) {
        Date now = new Date(new java.util.Date().getTime());
        Calendar cal = new GregorianCalendar();
        cal.setTime(signupDate);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date signupDatePlusOne = new Date(cal.getTime().getTime());
        return now.after(signupDatePlusOne) ? true : false;
    }
}
