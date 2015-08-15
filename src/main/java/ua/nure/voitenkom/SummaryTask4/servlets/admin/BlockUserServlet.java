package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.Mappings;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUserServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(UsersServlet.class);
    private UserService usersService;

    @Override
    public void init() throws ServletException {
        usersService = (UserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkRole(request, response);

        int id = Integer.parseInt(request.getParameter("id"));
        User user = usersService.selectById(id);
        logger.debug("User {} selected", user);
        if (user.isBlocked()) {
            usersService.unblock(id);
            logger.debug("User {} was unblocked", user);
        } else {
            usersService.makeBlocked(id);
            logger.debug("User {} was blocked", user);
        }

        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.USERS_MAPPING);
    }
}
