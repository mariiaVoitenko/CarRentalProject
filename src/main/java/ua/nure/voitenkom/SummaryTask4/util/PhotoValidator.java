package ua.nure.voitenkom.SummaryTask4.util;

import javax.servlet.http.Part;

public final class PhotoValidator {

    public static boolean isPhotoIncorrect(Part photo) {
        return photo == null || !"image/jpeg".equals(photo.getContentType());
    }

    private PhotoValidator(){

    }

}
