package ua.nure.voitenkom.SummaryTask4.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.formbean.LoginFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;
import ua.nure.voitenkom.SummaryTask4.validation.user.LoginValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private UserService userService;
    private IValidator<LoginFormBean> userValidator = new LoginValidator();

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginFormBean loginFormBean = parseLoginForm(request);
        Map<String, String> errors = userValidator.validate(loginFormBean);
        if (errors.isEmpty()) {
            String login = loginFormBean.getLogin();
            String password = loginFormBean.getPassword();
            if (userService.checkPassword(login, password)) {
                User user = userService.findByLogin(login);
                if (!user.isBlocked()) {
                    authorize(user, request);
                    logger.debug("User {} was authenticated", user);
                    response.sendRedirect(PageNames.MAIN_PAGE);
                    return;
                }
                else{
                    //TODO tell user he's blocked by special page
                }
            }
            else{
                //TODO tell user password doesn't match. redirect to login page
            }
        }
        else{
            //TODO tell user about mistakes
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(PageNames.LOGIN_PAGE);
    }

    private LoginFormBean parseLoginForm(HttpServletRequest request) {
        String login = request.getParameter(Attributes.LOGIN);
        String password = request.getParameter(Attributes.PASSWORD);
        return new LoginFormBean(login, password);
    }
}
