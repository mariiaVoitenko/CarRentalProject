package ua.nure.voitenkom.SummaryTask4.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationManager {

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isEmpty(String obj) {
        return obj == null || obj.trim().isEmpty();
    }

    public static boolean ifMail(String s) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches() ? true : false;
    }

    public static boolean ifNumber(String s) {
        return (s.matches("[0-9]+")) ? true : false;
    }

}
