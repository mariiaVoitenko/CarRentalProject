package ua.nure.voitenkom.SummaryTask4.servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.RentFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.rent.IRentService;
import ua.nure.voitenkom.SummaryTask4.service.rent.RentService;
import ua.nure.voitenkom.SummaryTask4.servlets.authentication.AuthenticationServlet;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReturnCarServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(HistoryServlet.class);
    private IRentService rentService;

    @Override
    public void init() throws ServletException {
        rentService = (IRentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rentId = Integer.parseInt(request.getParameter(Attributes.ID));
        rentService.updateReturnedState(rentId);
        logger.debug("Rent with id{} has returned state now");

        int userId = getAuthUserId(request);
        List<Rent> rentList = rentService.selectAllForUser(userId);
        List<RentFormBean> rents = new ArrayList<>();
        if (rentList.size() != 0) {
            rents = rentService.getUserRents(rentList);
        }
        request.setAttribute(Attributes.RENTS, rents);
        logger.debug("All rents have been got");
        response.sendRedirect((Mappings.HISTORY_MAPPING));
    }

}
