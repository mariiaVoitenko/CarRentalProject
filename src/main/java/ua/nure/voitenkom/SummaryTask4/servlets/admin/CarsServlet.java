package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CarsServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(CarsServlet.class);
    private CarService carService;

    @Override
    public void init() throws ServletException {
        carService = (CarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkRole(request, response);
        List<CarFormBean> cars = carService.getFullInformationForAll();
        request.setAttribute(Attributes.CARS, cars);

        logger.debug("All cars information has been got");

        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.CARS_PAGE);
        requestDispatcher.forward(request, response);
    }
}
