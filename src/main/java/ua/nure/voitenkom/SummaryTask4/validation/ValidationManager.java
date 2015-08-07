package ua.nure.voitenkom.SummaryTask4.validation;

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
        return s.contains("@");
    }

    public static boolean ifNumber(String s) {
        return (s.matches("[0-9]+")) ? true : false;
    }

}
