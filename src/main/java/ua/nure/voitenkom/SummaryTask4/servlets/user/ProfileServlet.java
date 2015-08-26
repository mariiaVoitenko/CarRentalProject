package ua.nure.voitenkom.SummaryTask4.servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.photo.IPhotoService;
import ua.nure.voitenkom.SummaryTask4.service.user.IUserService;
import ua.nure.voitenkom.SummaryTask4.servlets.authentication.AuthenticationServlet;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.EntitiesValues;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

import static ua.nure.voitenkom.SummaryTask4.util.PhotoValidator.isPhotoIncorrect;
import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNotNull;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class ProfileServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(HistoryServlet.class);
    private IUserService userService;
    private IPhotoService photoService;

    @Override
    public void init() throws ServletException {
        userService = (IUserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
        photoService = (IPhotoService) getServletContext().getAttribute(ServiceConstant.PHOTO_SERVICE_CONTEXT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        if (getAuthUserId(request) == null) {
            request.getRequestDispatcher(PageNames.ACCESS_DENIED_PAGE).forward(request, response);
            return;
        }
        boolean canChange = false;
        if (request.getParameter(Attributes.EDIT) == null) {
            id = getAuthUserId(request);
            canChange = true;

        } else {
            id = Integer.parseInt(request.getParameter(Attributes.ID));
            if (getRoleId(request) != Integer.parseInt(EntitiesValues.MANAGER_ROLE_ID) ||
                    getRoleId(request) != Integer.parseInt(EntitiesValues.ADMIN_ROLE_ID)) {
                request.getRequestDispatcher(PageNames.ACCESS_DENIED_PAGE).forward(request, response);
                return;
            }
        }

        User user = userService.selectById(id);
        setAttributes(request, canChange, user);
        logger.debug("Information about user with id {} has been got", id);
        request.getRequestDispatcher(PageNames.PROFILE_PAGE).forward(request, response);
    }

    @Override
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
        setAttributes(request, true, user);
        logger.debug("Photo was saved", id);
        request.getRequestDispatcher(PageNames.PROFILE_PAGE).forward(request, response);
    }

    private void setAttributes(HttpServletRequest request, boolean canChange, User user) {
        request.setAttribute(Attributes.USER, user);
        request.setAttribute(Attributes.EDIT, canChange);
    }

    private Integer getRoleId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return isNotNull(session) ? (Integer) session.getAttribute(Attributes.ROLE_ID) : null;
    }

}
