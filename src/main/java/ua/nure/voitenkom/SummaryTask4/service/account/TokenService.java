package ua.nure.voitenkom.SummaryTask4.service.account;

import java.util.UUID;

public class TokenService {

    public static String getToken(){
        return UUID.randomUUID().toString();
    }

}
