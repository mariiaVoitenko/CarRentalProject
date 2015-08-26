package ua.nure.voitenkom.SummaryTask4.service.account;

import java.util.UUID;

/**
 * @author Mariia Voitenko
 */
public class TokenService {
    /**
     * Creates unique token for user to register
     */
    public static String getToken() {
        return UUID.randomUUID().toString();
    }

}
