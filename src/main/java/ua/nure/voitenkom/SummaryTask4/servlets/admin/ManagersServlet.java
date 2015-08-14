package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin/managers")
public class ManagersServlet extends AdminServlet{

    private static final Logger logger = LoggerFactory.getLogger(UsersServlet.class);
    private UserService usersService;

    @Override
    public void init() throws ServletException {
        usersService = (UserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkRole(request, response);

        List<User> users = usersService.selectByRoleId(Integer.parseInt(Attributes.MANAGER_ROLE_ID));
        request.setAttribute(Attributes.USERS, users);

        logger.debug("All managers information has been got");

        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.USERS_PAGE);
        requestDispatcher.forward(request, response);
    }
}
