package ua.nure.voitenkom.SummaryTask4.servlets.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.user.IUserService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.formbean.LoginFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private IUserService userService;

    @Override
    public void init() throws ServletException {
        userService = (IUserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginFormBean loginFormBean = parseLoginForm(request);

        String login = loginFormBean.getLogin();
        String password = loginFormBean.getPassword();
        if (userService.checkPassword(login, password)) {
            User user = userService.findByLogin(login);
            if (!user.isBlocked() && user.isRegistered()) {
                authorize(user, request);
                logger.debug("User {} was authenticated", user);
                HttpSession session = request.getSession();
                session.setAttribute(Attributes.MESSAGE,"");
                if (session.getAttribute(Attributes.START_DATE) != null && session.getAttribute(Attributes.END_DATE) != null) {
                    response.sendRedirect(Mappings.CAR_RENT_MAPPING);
                    return;
                }
                response.sendRedirect(PageNames.MAIN_PAGE);
            } else {
                logger.debug("User {} was blocked. Access denied", user);
                response.sendRedirect(PageNames.BANNED_PAGE);
            }
        } else {
            sendMessage(request, "Wrong login or password");
            logger.debug("Wrong login or password");
            response.sendRedirect(PageNames.LOGIN_PAGE);
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

    private void sendMessage(HttpServletRequest request, String message) {
        HttpSession session = request.getSession();
        session.setAttribute(Attributes.MESSAGE, message);
    }
}
