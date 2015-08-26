package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.user.IUserService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mariia Voitenko
 */
public class BlockUserServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(UsersServlet.class);
    private IUserService usersService;

    @Override
    public void init() throws ServletException {
        usersService = (IUserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!checkRole(request, response)) return;
        changeUserState(request);
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.USERS_MAPPING);
    }

    private void changeUserState(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        User user = usersService.selectById(id);
        logger.debug("User {} selected", user);
        if (user.isBlocked()) {
            usersService.unblock(id);
            logger.debug("User {} was unblocked", user);
        } else {
            usersService.makeBlocked(id);
            logger.debug("User {} was blocked", user);
        }
    }

}
