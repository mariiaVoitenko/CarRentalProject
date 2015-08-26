package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.formbean.ApplicationFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.IBrandService;
import ua.nure.voitenkom.SummaryTask4.service.decline.IDeclineService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.IMajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.rent.IRentService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ApplicationServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServlet.class);
    private IRentService rentService;
    private IDeclineService declineService;
    private IBrandService brandService;
    private IMajorityClassService majorityClassService;

    @Override
    public void init() throws ServletException {
        rentService = (IRentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
        declineService = (IDeclineService) getServletContext().getAttribute(ServiceConstant.DECLINE_SERVICE_CONTEXT);
        brandService = (IBrandService) getServletContext().getAttribute(ServiceConstant.BRAND_SERVICE_CONTEXT);
        majorityClassService = (IMajorityClassService) getServletContext().getAttribute(ServiceConstant.CLASS_SERVICE_CONTEXT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkManagerRole(request, response);
        setAttributes(request);
        logger.debug("Applications have been got");
        request.getRequestDispatcher(PageNames.APPLICATIONS_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkManagerRole(request, response);
        setAttributes(request);
        logger.debug("Applications have been got");
        request.getRequestDispatcher(PageNames.APPLICATIONS_PAGE).forward(request, response);
    }

    private void setAttributes(HttpServletRequest request) {
        List<ApplicationFormBean> applications = rentService.getApplications();
        request.setAttribute(Attributes.RENTS, applications);
        request.setAttribute(Attributes.BRANDS, brandService.getAll());
        request.setAttribute(Attributes.CLASSES, majorityClassService.getAll());
        request.setAttribute(Attributes.DECLINES, declineService.getAll());
    }

}
