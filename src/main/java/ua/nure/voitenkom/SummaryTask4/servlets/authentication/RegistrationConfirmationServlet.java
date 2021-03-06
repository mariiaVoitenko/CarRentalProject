package ua.nure.voitenkom.SummaryTask4.servlets.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.role.IRoleService;
import ua.nure.voitenkom.SummaryTask4.service.user.IUserService;
import ua.nure.voitenkom.SummaryTask4.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Mariia Voitenko
 */
public class RegistrationConfirmationServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);
    private IUserService userService;
    private IRoleService roleService;

    @Override
    public void init() throws ServletException {
        userService = (IUserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
        roleService = (IRoleService) getServletContext().getAttribute(ServiceConstant.ROLE_SERVICE_CONTEXT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] splits = request.getRequestURI().split("/");
        String token = splits[splits.length - 1];
        User user = userService.selectByToken(token);
        if (sendTokenError(request, user)) return;
        user.setIsRegistered(true);
        userService.setRegisteredState(user.getId());
        Role registeredUserRole = roleService.selectByName(EntitiesValues.USER_VALUE);
        userService.changeRole(registeredUserRole.getId(), user.getId());
        logger.debug("User {} was registered", user);

        HttpSession session = request.getSession();
        if (session.getAttribute(Attributes.START_DATE) != null && session.getAttribute(Attributes.END_DATE) != null) {
            response.sendRedirect(Mappings.CAR_RENT_MAPPING);
            return;
        }
        request.getRequestDispatcher("/" + PageNames.SUCCESS_CONFIRMATION_PAGE).forward(request, response);
    }

    private boolean sendTokenError(HttpServletRequest request, User user) {
        if (DateManager.isWastedToken(user.getRegistrationTime())) {
            HttpSession session = request.getSession();
            userService.deleteUser(user.getId());
            session.setAttribute(Attributes.MESSAGE, "Sorry. Your one-time code is out of date. Try to register one more time and immediately activate a code.");
            return true;
        }
        return false;
    }

}
