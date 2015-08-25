package ua.nure.voitenkom.SummaryTask4.servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.rent.IRentService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.servlets.authentication.AuthenticationServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HistoryServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(HistoryServlet.class);
    private IRentService rentService;

    @Override
    public void init() throws ServletException {
        rentService = (IRentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = getAuthUserId(request);
        request.setAttribute(Attributes.RENTS, rentService.getUserRents(userId));
        logger.debug("All rents have been got");
        request.getRequestDispatcher(PageNames.HISTORY_PAGE).forward(request, response);
    }

}
