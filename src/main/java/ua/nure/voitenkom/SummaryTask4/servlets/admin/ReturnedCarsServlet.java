package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.RentFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.damage.DamageService;
import ua.nure.voitenkom.SummaryTask4.service.decline.DeclineService;
import ua.nure.voitenkom.SummaryTask4.service.rent.RentService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReturnedCarsServlet extends AdminServlet{

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServlet.class);
    private RentService rentService;
    private DamageService damageService;

    @Override
    public void init() throws ServletException {
        rentService = (RentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
        damageService = (DamageService) getServletContext().getAttribute(ServiceConstant.DAMAGE_SERVICE_CONTEXT);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkManagerRole(request,response);

        List<Rent> rentList = rentService.selectReturnedCars();
        List<RentFormBean> rents = new ArrayList<>();
        if (rentList.size() != 0) {
            rents = rentService.getPayedUnapprovedRents(rentList);
        }
        request.setAttribute(Attributes.RENTS, rents);

        logger.debug("Applications have been got");

        List<Damage> damageList = damageService.getAll();
        request.setAttribute(Attributes.DAMAGES, damageList);

        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.APPLICATIONS_PAGE);
        requestDispatcher.forward(request, response);
    }
}
