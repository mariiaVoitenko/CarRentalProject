package ua.nure.voitenkom.SummaryTask4.service.photo;

import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;

import javax.servlet.http.Part;
import java.io.File;

/**
 * Created by Maria on 05.08.2015.
 */
public interface IPhotoService {

    void saveUserPicture(User user, Part avatar);

    void saveCarPicture(Car car, Part picture);

    File getPicture(String path);

}
