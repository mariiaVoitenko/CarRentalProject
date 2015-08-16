package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCarServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeleteCarServlet.class);
    private CarService carService;

    @Override
    public void init() throws ServletException {
        carService = (CarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkRole(request, response);
        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        carService.delete(id);
        logger.debug("Deleted car with id {}", id);
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.CARS_MAPPING);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
