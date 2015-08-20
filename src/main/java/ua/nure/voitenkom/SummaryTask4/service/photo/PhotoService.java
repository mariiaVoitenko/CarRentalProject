package ua.nure.voitenkom.SummaryTask4.service.photo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.exception.FileSaveException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PhotoService implements IPhotoService {

    public static final String FORMAT = "jpg";
    private static final Logger logger = LoggerFactory.getLogger(PhotoService.class);
    private final String folder;

    public PhotoService(String path) {
        this.folder = path;
    }

    @Override
    public void saveUserPicture(User user, Part picture) {
        checkFolder();
        String avatarName = generateAvatarName();
        String path = folder + "/" + avatarName;
        saveOnDisk(picture, path);
        user.setPhotoPath(avatarName);
        logger.debug("Picture has been saved {}", path);
    }

    @Override
    public void saveCarPicture(Car car, Part picture) {
        checkFolder();
        String avatarName = generateAvatarName();
        String path = folder + "/" + avatarName;
        saveOnDisk(picture, path);
        car.setPhotoPath(avatarName);
        logger.debug("Picture has been saved {}", path);
    }

    @Override
    public File getPicture(String picture) {
        return new File(folder + "/" + picture);
    }

    private String generateAvatarName() {
        return UUID.randomUUID().toString() + "." + FORMAT;
    }

    private void saveOnDisk(Part picture, String filePath) {
        try {
            picture.write(filePath);
        } catch (IOException e) {
            logger.warn("Unable to load picture", e);
            throw new FileSaveException("Unable to save picture", e);
        }
    }

    private void checkFolder() {
        File fileSaveDirectory = new File(folder);
        if (!fileSaveDirectory.exists()) {
            boolean isCreated = fileSaveDirectory.mkdirs();
            if (!isCreated) {
                logger.error("Unable to create folder for pictures folder");
                throw new FileSaveException("Unable to create folder for pictures folder");
            }
        }
    }
}