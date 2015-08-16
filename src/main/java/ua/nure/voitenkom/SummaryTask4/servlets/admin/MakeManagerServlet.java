package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.util.EntitiesValues;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;
import ua.nure.voitenkom.SummaryTask4.db.entity.Role;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.role.RoleService;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeManagerServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(UsersServlet.class);
    private UserService userService;
    private RoleService roleService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
        roleService = (RoleService) getServletContext().getAttribute(ServiceConstant.ROLE_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkRole(request, response);
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.selectById(id);
        int managerRoleId = Integer.parseInt(EntitiesValues.MANAGER_ROLE_ID);
        if (user.getRoleId() != managerRoleId) {
            Role managerRole = roleService.selectByName(EntitiesValues.MANAGER_USER_VALUE);
            userService.changeRole(managerRole.getId(), user.getId());
        }
        else{
            Role userRole = roleService.selectByName(EntitiesValues.USER_VALUE);
            userService.changeRole(userRole.getId(), user.getId());
        }
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.USERS_MAPPING);
    }
}
