package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.rent.IRentService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.RentFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AcceptApplicationServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServlet.class);
    private IRentService rentService;

    @Override
    public void init() throws ServletException {
        rentService = (IRentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkManagerRole(request, response);

        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        rentService.updateApprovedState(id);
        logger.debug("Rent with id {} was accepted", id);

        List<Rent> rentList = rentService.selectUnapproved();
        List<RentFormBean> rents = new ArrayList<>();
        if (!rentList.isEmpty()) {
            rents = rentService.getPayedUnapprovedRents(rentList);
        }

        request.setAttribute(Attributes.RENTS, rents);
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.APPLICATIONS_MAPPING);
    }
}
