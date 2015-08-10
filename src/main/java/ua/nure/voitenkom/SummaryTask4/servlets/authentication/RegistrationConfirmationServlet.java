package ua.nure.voitenkom.SummaryTask4.servlets.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.account.DateService;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "confirm")
public class RegistrationConfirmationServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] splits = request.getRequestURI().split("/");
        String token = splits[splits.length - 1];
        User user = userService.selectByToken(token);
        if (DateService.isWastedToken(user.getRegistrationTime())) {
            HttpSession session = request.getSession();
            userService.deleteUser(user.getId());
            session.setAttribute(Attributes.MESSAGE, "Sorry. Your one-time code is out of date. Try to register one more time and immediately activate a code.");
            return;
        }
        user.setIsRegistered(true);
        userService.setRegisteredState(user.getId());
        logger.debug("User {} was registered", user);
        response.sendRedirect(PageNames.EMPTY_PAGE + PageNames.SUCCESS_CONFIRMATION_PAGE);
    }

}
