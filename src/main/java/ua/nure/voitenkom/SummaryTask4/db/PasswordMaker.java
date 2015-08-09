package ua.nure.voitenkom.SummaryTask4.db;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordMaker {

    public static String makePassword(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }

}
