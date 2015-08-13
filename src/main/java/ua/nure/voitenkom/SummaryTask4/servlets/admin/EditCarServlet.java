package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.*;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.BrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;
import ua.nure.voitenkom.SummaryTask4.service.color.ColorService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.MajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.status.StatusService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "editCar")
public class EditCarServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(EditCarServlet.class);
    private CarService carService;
    private BrandService brandService;
    private MajorityClassService majorityClassService;
    private ColorService colorService;
    private StatusService statusService;

    @Override
    public void init() throws ServletException {
        carService = (CarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
        brandService = (BrandService) getServletContext().getAttribute(ServiceConstant.BRAND_SERVICE_CONTEXT);
        majorityClassService = (MajorityClassService) getServletContext().getAttribute(ServiceConstant.CLASS_SERVICE_CONTEXT);
        colorService = (ColorService) getServletContext().getAttribute(ServiceConstant.COLOR_SERVICE_CONTEXT);
        statusService = (StatusService) getServletContext().getAttribute(ServiceConstant.STATUS_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkRole(request, response);
        int id = Integer.parseInt(request.getParameter("id"));
        Car car = carService.getById(id);
        request.setAttribute(Attributes.CAR, car);
        logger.debug("Car selected");

        loadEntities(request, brandService, majorityClassService, colorService, statusService);

        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.EDIT_CARS_PAGE);
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkRole(request, response);
    }
}
