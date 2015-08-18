package ua.nure.voitenkom.SummaryTask4.servlets.user;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.photo.PhotoService;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;
import ua.nure.voitenkom.SummaryTask4.servlets.authentication.AuthenticationServlet;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.EntitiesValues;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

import static ua.nure.voitenkom.SummaryTask4.util.PhotoValidator.isPhotoIncorrect;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class ProfileServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(HistoryServlet.class);
    private UserService userService;
    private PhotoService photoService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
        photoService = (PhotoService) getServletContext().getAttribute(ServiceConstant.PHOTO_SERVICE_CONTEXT);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        boolean canChange = false;
        if (request.getParameter(Attributes.EDIT) == null) {
            id = getAuthUserId(request);
            canChange = true;

        } else {
            id = Integer.parseInt(request.getParameter(Attributes.ID));
        }
        User user = userService.selectById(id);
        request.setAttribute(Attributes.USER, user);
        request.setAttribute(Attributes.EDIT, canChange);
        logger.debug("Information about user with id {} has been got", id);
        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.PROFILE_PAGE);
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = getAuthUserId(request);
        User user = userService.selectById(id);
        Part photo = request.getPart(Attributes.PHOTO);
        if (photo.getSize() != 0) {
            if (isPhotoIncorrect(photo)) {
                logger.debug("Some error with photo has occurred");
                return;
            }
            photoService.saveUserPicture(user, photo);
        }
        userService.updatePhoto(user);
        request.setAttribute(Attributes.USER, user);
        request.setAttribute(Attributes.EDIT, true);
        logger.debug("Photo was saved", id);
        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.PROFILE_PAGE);
        requestDispatcher.forward(request, response);
    }
}