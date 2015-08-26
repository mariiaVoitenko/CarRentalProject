package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.rent.IRentService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeclineApplicationServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeclineApplicationServlet.class);
    private IRentService rentService;

    @Override
    public void init() throws ServletException {
        rentService = (IRentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkManagerRole(request, response);
        setDeclineReason(request);
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.APPLICATIONS_MAPPING);
    }

    private void setDeclineReason(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        Rent rent = rentService.selectById(id);
        rent.setDeclineId(Integer.parseInt(request.getParameter(Attributes.DECLINE)));
        rentService.updateDecline(rent);
        logger.debug("Rent with id {} was not accepted", id);
    }

}
