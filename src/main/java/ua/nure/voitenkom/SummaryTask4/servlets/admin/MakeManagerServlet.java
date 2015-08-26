package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.role.IRoleService;
import ua.nure.voitenkom.SummaryTask4.service.user.IUserService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.EntitiesValues;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeManagerServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(UsersServlet.class);
    private IUserService userService;
    private IRoleService roleService;

    @Override
    public void init() throws ServletException {
        userService = (IUserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
        roleService = (IRoleService) getServletContext().getAttribute(ServiceConstant.ROLE_SERVICE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkRole(request, response);
        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        User user = userService.selectById(id);
        int managerRoleId = Integer.parseInt(EntitiesValues.MANAGER_ROLE_ID);
        changeRole(user, managerRoleId);
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.USERS_MAPPING);
    }

    private void changeRole(User user, int managerRoleId) {
        if (user.getRoleId() != managerRoleId) {
            Role managerRole = roleService.selectByName(EntitiesValues.MANAGER_USER_VALUE);
            userService.changeRole(managerRole.getId(), user.getId());
            logger.debug("User was made manager");
        } else {
            Role userRole = roleService.selectByName(EntitiesValues.USER_VALUE);
            userService.changeRole(userRole.getId(), user.getId());
            logger.debug("Manager was made simple user");
        }
    }
}
