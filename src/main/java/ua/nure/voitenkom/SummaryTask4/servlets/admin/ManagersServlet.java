package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.user.IUserService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.EntitiesValues;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Mariia Voitenko
 */
public class ManagersServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(UsersServlet.class);
    private IUserService usersService;

    @Override
    public void init() throws ServletException {
        usersService = (IUserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!checkRole(request, response)) return;
        List<User> users = usersService.selectByRoleId(Integer.parseInt(EntitiesValues.MANAGER_ROLE_ID));
        request.setAttribute(Attributes.USERS, users);
        logger.debug("All managers information has been got");
        request.getRequestDispatcher(PageNames.USERS_PAGE).forward(request, response);
    }
}
