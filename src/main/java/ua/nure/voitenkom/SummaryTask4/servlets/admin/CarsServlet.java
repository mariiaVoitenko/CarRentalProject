package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.IBrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.ICarService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.IMajorityClassService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Mariia Voitenko
 */
public class CarsServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(CarsServlet.class);
    private ICarService carService;
    private IBrandService brandService;
    private IMajorityClassService majorityClassService;

    @Override
    public void init() throws ServletException {
        carService = (ICarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
        brandService = (IBrandService) getServletContext().getAttribute(ServiceConstant.BRAND_SERVICE_CONTEXT);
        majorityClassService = (IMajorityClassService) getServletContext().getAttribute(ServiceConstant.CLASS_SERVICE_CONTEXT);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAttributes(request);
        request.getRequestDispatcher(PageNames.CARS_PAGE).forward(request, response);
    }

    private void setAttributes(HttpServletRequest request) {
        List<CarFormBean> cars = carService.getFullInformationForAll();
        request.setAttribute(Attributes.CARS, cars);
        request.setAttribute(Attributes.BRANDS, brandService.getAll());
        request.setAttribute(Attributes.CLASSES, majorityClassService.getAll());
        request.setAttribute(Attributes.ROLE_ID, request.getSession().getAttribute(Attributes.ROLE_ID));
        logger.debug("All cars information has been got");
    }

}
