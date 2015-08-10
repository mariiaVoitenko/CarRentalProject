package ua.nure.voitenkom.SummaryTask4.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.service.account.DateService;
import ua.nure.voitenkom.SummaryTask4.service.account.MailService;
import ua.nure.voitenkom.SummaryTask4.service.account.PasswordMaker;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.formbean.RegistrationFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.account.TokenService;
import ua.nure.voitenkom.SummaryTask4.service.photo.PhotoService;
import ua.nure.voitenkom.SummaryTask4.service.role.RoleService;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;
import ua.nure.voitenkom.SummaryTask4.validation.user.RegistrationValidator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Properties;


@WebServlet(name = "registration")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class RegistrationServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);
    private UserService userService;
    private PhotoService photoService;
    private RoleService roleService;
    private IValidator<RegistrationFormBean> userValidator = new RegistrationValidator();
    private String host;
    private String port;
    private String userEmail;
    private String password;

    @Override
    public void init() throws ServletException {
        roleService = (RoleService) getServletContext().getAttribute(ServiceConstant.ROLE_SERVICE_CONTEXT);
        userService = (UserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
        photoService = (PhotoService) getServletContext().getAttribute(ServiceConstant.PHOTO_SERVICE_CONTEXT);
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
        Map<String, String> errors = validateData(registrationFormBean, photo);
        if (!errors.isEmpty()) {
            logger.debug("Validation problems");
            if (errors.get("login") != null) {
                session.setAttribute(Attributes.LOGIN_MESSAGE, errors.get("login"));
            }
            if (errors.get("photo") != null) {
                session.setAttribute(Attributes.PHOTO_MESSAGE, errors.get("photo"));
            }
            if (errors.get("password") != null) {
                session.setAttribute(Attributes.PASSWORD_MESSAGE, errors.get("password"));
            }
            response.sendRedirect(PageNames.REGISTRATION_PAGE);
            return;
        }
        Role unregistered = roleService.selectByName(Attributes.UNREGISTERED_USER_VALUE);
        int roleId = unregistered.getId();
        String cipherPassword = PasswordMaker.makePassword(registrationFormBean.getPassword());
        String token = TokenService.getToken();
        Timestamp now = DateService.getCurrentDate();
        User newUser = new User(registrationFormBean.getFullName(), token, registrationFormBean.getPassportNumber(), roleId, cipherPassword, registrationFormBean.getLogin(), now);
        photoService.saveUserPicture(newUser, photo);
        userService.insertWithPhoto(newUser);
        logger.debug("User {} was added", newUser);
        try {
            MailService.sendEmail(newUser.getLogin(), host, port, userEmail, password, token);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        response.sendRedirect(PageNames.SUCCESS_REGISTRATION_PAGE);
    }

    private Map<String, String> validateData(RegistrationFormBean registrationFormBean, Part photo) {
        Map<String, String> errors = userValidator.validate(registrationFormBean);
        if (isPhotoIncorrect(photo)) {
            errors.put("photo", "Some error occured");
        }
        if (errors.isEmpty() && userService.findByLogin(registrationFormBean.getLogin()) != null) {
            errors.put("login", "Login already exists");
        }
        if (!registrationFormBean.getPassword().equals(registrationFormBean.getRepeatedPassword())) {
            errors.put("password", "Passwords don't match");
        }
        return errors;
    }

    private boolean isPhotoIncorrect(Part photo) {
        return photo == null || !"image/jpeg".equals(photo.getContentType()) || photo.getSize() == 0;
    }

    private RegistrationFormBean parseForm(HttpServletRequest request) {
        String login = request.getParameter(Attributes.LOGIN);
        String password = request.getParameter(Attributes.PASSWORD);
        String repeatedPassword = request.getParameter(Attributes.PASSWORD2);
        String passport = request.getParameter(Attributes.PASSPORT);
        String fullName = request.getParameter(Attributes.FULL_NAME);
        RegistrationFormBean registrationFormBean = new RegistrationFormBean(login, password, passport, fullName, repeatedPassword);
        return registrationFormBean;
    }


}
