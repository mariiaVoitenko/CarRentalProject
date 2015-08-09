package ua.nure.voitenkom.SummaryTask4.service.account;

import java.util.UUID;

public class TokenService {

    public static String getToken(){
        UUID idOne = UUID.randomUUID();
        return String.valueOf(idOne);
    }

}
