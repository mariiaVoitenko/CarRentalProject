package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.PageNames;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteCar")
public class DeleteCarServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeleteCarServlet.class);
    private CarService carService;

    @Override
    public void init() throws ServletException {
        carService = (CarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getRoleId(request) != 1) {
            response.sendRedirect(PageNames.EMPTY_PAGE + PageNames.ACCESS_DENIED_PAGE);
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        carService.delete(id);
        logger.debug("Deleted car with id {}", id);
        response.sendRedirect(PageNames.EMPTY_PAGE + PageNames.ADMIN + PageNames.CARS_MAPPING);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
