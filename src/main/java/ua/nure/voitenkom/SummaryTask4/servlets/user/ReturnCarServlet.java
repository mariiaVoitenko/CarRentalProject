package ua.nure.voitenkom.SummaryTask4.servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.rent.IRentService;
import ua.nure.voitenkom.SummaryTask4.servlets.authentication.AuthenticationServlet;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mariia Voitenko
 */
public class ReturnCarServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(HistoryServlet.class);
    private IRentService rentService;

    @Override
    public void init() throws ServletException {
        rentService = (IRentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setReturned(request);
        setUserRents(request);
        response.sendRedirect((Mappings.HISTORY_MAPPING));
    }

    private void setUserRents(HttpServletRequest request) {
        int userId = getAuthUserId(request);
        request.setAttribute(Attributes.RENTS, rentService.getUserRents(userId));
        logger.debug("All rents have been got");
    }

    private void setReturned(HttpServletRequest request) {
        int rentId = Integer.parseInt(request.getParameter(Attributes.ID));
        rentService.updateReturnedState(rentId);
        logger.debug("Rent with id{} has returned state now");
    }

}
