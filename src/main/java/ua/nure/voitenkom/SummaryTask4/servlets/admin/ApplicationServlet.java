package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.RentFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;
import ua.nure.voitenkom.SummaryTask4.service.check.CheckService;
import ua.nure.voitenkom.SummaryTask4.service.decline.DeclineService;
import ua.nure.voitenkom.SummaryTask4.service.rent.RentService;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationServlet extends AdminServlet{

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServlet.class);
    private CarService carService;
    private RentService rentService;
    private CheckService checkService;
    private DeclineService declineService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        carService = (CarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
        rentService = (RentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
        userService = (UserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
        checkService = (CheckService) getServletContext().getAttribute(ServiceConstant.CHECK_SERVICE_CONTEXT);
        declineService = (DeclineService) getServletContext().getAttribute(ServiceConstant.DECLINE_SERVICE_CONTEXT);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkManagerRole(request,response);

        List<Rent> rentList = rentService.selectUnapproved();
        List<RentFormBean> rents = new ArrayList<>();
        if (rentList.size() != 0) {
            rents = rentService.getPayedUnapprovedRents(rentList, carService, declineService, checkService, userService);
        }
        request.setAttribute(Attributes.RENTS, rents);

        List<Decline> declines = declineService.getAll();
        request.setAttribute(Attributes.DECLINES, declines);

        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.APPLICATIONS_PAGE);
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
