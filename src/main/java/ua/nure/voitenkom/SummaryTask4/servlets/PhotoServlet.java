package ua.nure.voitenkom.SummaryTask4.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.photo.IPhotoService;
import ua.nure.voitenkom.SummaryTask4.service.photo.PhotoService;
import ua.nure.voitenkom.SummaryTask4.util.FileManager;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PhotoServlet extends HttpServlet {

    public static final Logger logger = LoggerFactory.getLogger(PhotoServlet.class);
    private IPhotoService photoService;

    @Override
    public void init() throws ServletException {
        photoService = (IPhotoService) getServletContext().getAttribute(ServiceConstant.PHOTO_SERVICE_CONTEXT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String pictureName = requestURI.replaceAll("/photo/", "");
        pictureName = pictureName.replaceAll("/admin","");
        File picture = photoService.getPicture(pictureName);
        if (!picture.isFile()) {
            logger.warn("Unable to get picture {}", picture.getAbsoluteFile());
            response.sendError(404);
            return;
        }
        sendPicture(picture, response);
    }

    private void sendPicture(File picture, HttpServletResponse response) {
        try (ServletOutputStream output = response.getOutputStream();
             FileInputStream input = new FileInputStream(picture)) {
                FileManager.copyStream(input, output);
        } catch (IOException e) {
            logger.warn("Unable send picture on client by file stream", e);
        }
    }
}
