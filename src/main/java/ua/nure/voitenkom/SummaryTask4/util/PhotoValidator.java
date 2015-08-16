package ua.nure.voitenkom.SummaryTask4.util;

import javax.servlet.http.Part;

public class PhotoValidator {

    public static boolean isPhotoIncorrect(Part photo) {
        return photo == null || !"image/jpeg".equals(photo.getContentType());
    }
}
