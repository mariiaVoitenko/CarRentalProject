package ua.nure.voitenkom.SummaryTask4.service.photo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.exception.FileSaveException;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PhotoService implements IPhotoService {

    public static final String FORMAT = "jpg";
    private static final Logger logger = LoggerFactory.getLogger(PhotoService.class);
    private static final int IMG_WIDTH = 200;
    private static final int IMG_HEIGHT = 200;
    private final String folder;

    public PhotoService(String path) {
        this.folder = path;
    }

    @Override
    public void saveUserPicture(User user, Part picture) {
        File fileSaveDirectory = new File(folder);
        if (!fileSaveDirectory.exists()) {
            boolean isCreated = fileSaveDirectory.mkdirs();
            if (!isCreated) {
                logger.error("Unable to create folder for pictures folder");
                throw new FileSaveException("Unable to create folder for pictures folder");
            }
        }
        String avatarName = generateAvatarName();
        String path = folder + avatarName;
        saveOnDisk(picture, path);
        user.setPhotoPath(avatarName);
        logger.debug("Picture has been saved {}", path);
    }

    @Override
    public void saveCarPicture(Car car, Part picture) {
        File fileSaveDirectory = new File(folder);
        if (!fileSaveDirectory.exists()) {
            boolean isCreated = fileSaveDirectory.mkdirs();
            if (!isCreated) {
                logger.error("Unable to create folder for pictures folder");
                throw new FileSaveException("Unable to create folder for pictures folder");
            }
        }
        String avatarName = generateAvatarName();
        String path = folder + "/" + avatarName;
        saveOnDisk(picture, path);
        car.setPhotoPath(avatarName);
        logger.debug("Picture has been saved {}", path);
    }

    @Override
    public File getPicture(String picture) {
        return new File(folder + picture);
    }

    private String generateAvatarName() {
        return UUID.randomUUID().toString() + "." + FORMAT;
    }

    private void saveOnDisk(Part picture, String filePath) {
        try {
            picture.write(filePath);
            resize(filePath);
        } catch (IOException e) {
            logger.warn("Unable to load picture", e);
            throw new FileSaveException("Unable to save picture", e);
        }
    }

    private static void resize(String picture) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(picture));
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        ImageIO.write(resizedImage, FORMAT, new File(picture));
    }
}