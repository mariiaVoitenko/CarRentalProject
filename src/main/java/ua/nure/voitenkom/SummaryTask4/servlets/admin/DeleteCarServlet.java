package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.car.ICarService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mariia Voitenko
 */
public class DeleteCarServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeleteCarServlet.class);
    private ICarService carService;

    @Override
    public void init() throws ServletException {
        carService = (ICarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!checkRole(request, response)) return;
        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        carService.delete(id);
        logger.debug("Deleted car with id {}", id);
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.CARS_MAPPING);
    }

}
