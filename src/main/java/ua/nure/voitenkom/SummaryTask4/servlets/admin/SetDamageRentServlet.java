package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.entity.DamageCheck;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.check.CheckService;
import ua.nure.voitenkom.SummaryTask4.service.check.ICheckService;
import ua.nure.voitenkom.SummaryTask4.service.damage.DamageService;
import ua.nure.voitenkom.SummaryTask4.service.damage.IDamageService;
import ua.nure.voitenkom.SummaryTask4.service.damagecheck.DamageCheckService;
import ua.nure.voitenkom.SummaryTask4.service.damagecheck.IDamageCheckService;
import ua.nure.voitenkom.SummaryTask4.service.rent.RentService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetDamageRentServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServlet.class);
    private RentService rentService;
    private DamageCheckService damageCheckService;
    private ICheckService checkService;
    private IDamageService damageService;

    @Override
    public void init() throws ServletException {
        rentService = (RentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
        damageCheckService = (DamageCheckService) getServletContext().getAttribute(ServiceConstant.DAMAGE_CHECK_SERVICE_CONTEXT);
        checkService = (CheckService) getServletContext().getAttribute(ServiceConstant.CHECK_SERVICE_CONTEXT);
        damageService = (DamageService) getServletContext().getAttribute(ServiceConstant.DAMAGE_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkManagerRole(request, response);
        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        int damageId = Integer.parseInt(request.getParameter(Attributes.DAMAGE));
        rentService.updateFinishedState(id);
        Rent rent = rentService.selectById(id);
        int checkId = rent.getCheckId();
        DamageCheck damageCheck = new DamageCheck(damageId,checkId);
        damageCheckService.insert(damageCheck);
        Check check = checkService.selectById(checkId);
        int damageSum = damageService.selectSumById(damageId);
        checkService.updateSum(check, damageSum);

        //TODO generate pdf and send by email
        logger.debug("Rent with id {} was finished", id);
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.RETURNED_CARS_MAPPING);
    }
}
