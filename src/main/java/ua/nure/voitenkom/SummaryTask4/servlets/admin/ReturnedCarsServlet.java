package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.IBrandService;
import ua.nure.voitenkom.SummaryTask4.service.damage.IDamageService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.IMajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.rent.IRentService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mariia Voitenko
 */
public class ReturnedCarsServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServlet.class);
    private IRentService rentService;
    private IDamageService damageService;
    private IBrandService brandService;
    private IMajorityClassService majorityClassService;

    @Override
    public void init() throws ServletException {
        rentService = (IRentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
        damageService = (IDamageService) getServletContext().getAttribute(ServiceConstant.DAMAGE_SERVICE_CONTEXT);
        brandService = (IBrandService) getServletContext().getAttribute(ServiceConstant.BRAND_SERVICE_CONTEXT);
        majorityClassService = (IMajorityClassService) getServletContext().getAttribute(ServiceConstant.CLASS_SERVICE_CONTEXT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!checkManagerRole(request, response)) return;
        setAttributes(request);
        logger.debug("Data have been got");
        request.getRequestDispatcher(PageNames.RETURNED_CARS_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!checkManagerRole(request, response)) return;
        setAttributes(request);
        logger.debug("Data have been got");
        request.getRequestDispatcher(PageNames.RETURNED_CARS_PAGE).forward(request, response);
    }

    private void setAttributes(HttpServletRequest request) {
        request.setAttribute(Attributes.RENTS, rentService.getReturned());
        request.setAttribute(Attributes.DAMAGES, damageService.getAll());
        request.setAttribute(Attributes.BRANDS, brandService.getAll());
        request.setAttribute(Attributes.CLASSES, majorityClassService.getAll());
    }
}
