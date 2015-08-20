package ua.nure.voitenkom.SummaryTask4.servlets.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.photo.IPhotoService;
import ua.nure.voitenkom.SummaryTask4.service.role.IRoleService;
import ua.nure.voitenkom.SummaryTask4.service.user.IUserService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.EntitiesValues;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.util.DateManager;
import ua.nure.voitenkom.SummaryTask4.service.account.MailService;
import ua.nure.voitenkom.SummaryTask4.service.account.PasswordMaker;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.formbean.RegistrationFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.account.TokenService;

import javax.mail.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import static ua.nure.voitenkom.SummaryTask4.util.PhotoValidator.isPhotoIncorrect;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class RegistrationServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);
    private IUserService userService;
    private IPhotoService photoService;
    private IRoleService roleService;
    private String host;
    private String port;
    private String userEmail;
    private String password;

    @Override
    public void init() throws ServletException {
        roleService = (IRoleService) getServletContext().getAttribute(ServiceConstant.ROLE_SERVICE_CONTEXT);
        userService = (IUserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
        photoService = (IPhotoService) getServletContext().getAttribute(ServiceConstant.PHOTO_SERVICE_CONTEXT);
        host = getServletContext().getInitParameter(ServiceConstant.HOST_PARAM);
        port = getServletContext().getInitParameter(ServiceConstant.PORT_PARAM);
        userEmail = getServletContext().getInitParameter(ServiceConstant.USER_EMAIL_PARAM);
        password = getServletContext().getInitParameter(ServiceConstant.USER_PASSWORD_PARAM);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(PageNames.REGISTRATION_PAGE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute(Attributes.PHOTO_MESSAGE, "");
        session.setAttribute(Attributes.LOGIN_MESSAGE, "");
        session.setAttribute(Attributes.PASSWORD_MESSAGE, "");
        RegistrationFormBean registrationFormBean = parseForm(request);
        Part photo = request.getPart(Attributes.PHOTO);
        Map<String, String> errors = validateData(registrationFormBean);

        if (!errors.isEmpty()) {
            logger.debug("Validation problems");
            if (errors.get("login") != null) {
                session.setAttribute(Attributes.LOGIN_MESSAGE, errors.get("login"));
            }
            if (errors.get("photo") != null) {
                session.setAttribute(Attributes.PHOTO_MESSAGE, errors.get("photo"));
            }
            response.sendRedirect(PageNames.REGISTRATION_PAGE);
            return;
        }
        Role unregistered = roleService.selectByName(EntitiesValues.UNREGISTERED_USER_VALUE);
        int roleId = unregistered.getId();
        String cipherPassword = PasswordMaker.makePassword(registrationFormBean.getPassword());
        String token = TokenService.getToken();
        Timestamp now = DateManager.getCurrentDate();
        User newUser = new User(registrationFormBean.getFullName(), token, registrationFormBean.getPassportNumber(), roleId, cipherPassword, registrationFormBean.getLogin(), now);

        if (!(photo.getSize() == 0)) {
            if (isPhotoIncorrect(photo)) {
                errors.put("error", "Some error with photo has occurred");
                return;
            }
            photoService.saveUserPicture(newUser, photo);
        } else {
            newUser.setPhotoPath("user.png");
        }
        userService.insertWithPhoto(newUser);
        logger.debug("User {} was added", newUser);
        try {
            MailService.sendEmail(host, port, newUser.getLogin(), userEmail, password, token);
        } catch (MessagingException e) {
            logger.error("Unable to send mail");
        }
        session.setAttribute(Attributes.MESSAGE,"");

        response.sendRedirect(PageNames.SUCCESS_REGISTRATION_PAGE);
    }

    private Map<String, String> validateData(RegistrationFormBean registrationFormBean) {
        Map<String, String> errors = new HashMap<>();
        if (userService.findByLogin(registrationFormBean.getLogin()) != null) {
            errors.put("login", "Login already exists");
        }
        return errors;
    }

    private RegistrationFormBean parseForm(HttpServletRequest request) {
        String login = request.getParameter(Attributes.LOGIN);
        String password = request.getParameter(Attributes.PASSWORD);
        String repeatedPassword = request.getParameter(Attributes.PASSWORD2);
        String passport = request.getParameter(Attributes.PASSPORT);
        String fullName = request.getParameter(Attributes.FULL_NAME);
        return new RegistrationFormBean(login, password, passport, fullName, repeatedPassword);
    }


}
