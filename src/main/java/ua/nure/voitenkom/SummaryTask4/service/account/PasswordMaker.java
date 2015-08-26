package ua.nure.voitenkom.SummaryTask4.service.account;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Mariia Voitenko
 */
public class PasswordMaker {
    /**
     * Gets user password from form and gets hash of it to set in database
     */
    public static String makePassword(String password) {
        return DigestUtils.md5Hex(password);
    }

}
