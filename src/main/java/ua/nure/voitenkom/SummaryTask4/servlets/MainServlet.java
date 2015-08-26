package ua.nure.voitenkom.SummaryTask4.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.car.ICarService;
import ua.nure.voitenkom.SummaryTask4.servlets.authentication.RegistrationServlet;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mariia Voitenko
 */
public class MainServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);
    private ICarService carService;

    @Override
    public void init() throws ServletException {
        carService = (ICarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Attributes.CARS, carService.getFullInformationForAll());
        logger.debug("All car information has been got");
        request.getRequestDispatcher(PageNames.INDEX_PAGE).forward(request, response);
    }

}
