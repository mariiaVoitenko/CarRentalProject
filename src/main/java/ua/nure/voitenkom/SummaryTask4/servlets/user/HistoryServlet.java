package ua.nure.voitenkom.SummaryTask4.servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.RentFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.BrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;
import ua.nure.voitenkom.SummaryTask4.service.check.CheckService;
import ua.nure.voitenkom.SummaryTask4.service.color.ColorService;
import ua.nure.voitenkom.SummaryTask4.service.decline.DeclineService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.MajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.rent.RentService;
import ua.nure.voitenkom.SummaryTask4.service.status.StatusService;
import ua.nure.voitenkom.SummaryTask4.servlets.authentication.AuthenticationServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(HistoryServlet.class);
    private CarService carService;
    private RentService rentService;
    private BrandService brandService;
    private MajorityClassService majorityClassService;
    private ColorService colorService;
    private StatusService statusService;
    private CheckService checkService;
    private DeclineService declineService;

    @Override
    public void init() throws ServletException {
        carService = (CarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
        rentService = (RentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
        brandService = (BrandService) getServletContext().getAttribute(ServiceConstant.BRAND_SERVICE_CONTEXT);
        majorityClassService = (MajorityClassService) getServletContext().getAttribute(ServiceConstant.CLASS_SERVICE_CONTEXT);
        colorService = (ColorService) getServletContext().getAttribute(ServiceConstant.COLOR_SERVICE_CONTEXT);
        statusService = (StatusService) getServletContext().getAttribute(ServiceConstant.STATUS_SERVICE_CONTEXT);
        checkService = (CheckService) getServletContext().getAttribute(ServiceConstant.CHECK_SERVICE_CONTEXT);
        declineService = (DeclineService) getServletContext().getAttribute(ServiceConstant.DECLINE_SERVICE_CONTEXT);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = getAuthUserId(request);
        List<Rent> rentList = rentService.selectAllForUser(userId);
        List<RentFormBean> rents = new ArrayList<>();
        if (rentList.size() != 0) {
            rents = rentService.getUserRents(rentList, carService, declineService, checkService);
        }
        request.setAttribute(Attributes.RENTS, rents);
        logger.debug("All rents have been got");
        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.HISTORY_PAGE);
        requestDispatcher.forward(request, response);
    }

}
