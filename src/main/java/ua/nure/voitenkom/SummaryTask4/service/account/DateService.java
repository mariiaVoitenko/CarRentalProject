package ua.nure.voitenkom.SummaryTask4.service.account;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateService {

    public static Timestamp getCurrentDate(){
        return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }

}
