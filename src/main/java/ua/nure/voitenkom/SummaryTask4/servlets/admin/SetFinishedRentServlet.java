package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.rent.IRentService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetFinishedRentServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServlet.class);
    private IRentService rentService;

    @Override
    public void init() throws ServletException {
        rentService = (IRentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!checkManagerRole(request, response))return;
        finishRent(request);
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.RETURNED_CARS_MAPPING);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!checkManagerRole(request, response))return;
        finishRent(request);
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.RETURNED_CARS_MAPPING);
    }

    private void finishRent(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        rentService.updateFinishedState(id);
        logger.debug("Rent with id {} was finished", id);
    }

}
