package ua.nure.voitenkom.SummaryTask4.service.account;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordMaker {

    public static String makePassword(String password) {
        return DigestUtils.md5Hex(password);
    }

}
