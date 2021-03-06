package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.IBrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.ICarService;
import ua.nure.voitenkom.SummaryTask4.service.color.IColorService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.IMajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.status.IStatusService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mariia Voitenko
 */
public class EditCarServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(EditCarServlet.class);
    private ICarService carService;
    private IBrandService brandService;
    private IMajorityClassService majorityClassService;
    private IColorService colorService;
    private IStatusService statusService;

    @Override
    public void init() throws ServletException {
        carService = (ICarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
        brandService = (IBrandService) getServletContext().getAttribute(ServiceConstant.BRAND_SERVICE_CONTEXT);
        majorityClassService = (IMajorityClassService) getServletContext().getAttribute(ServiceConstant.CLASS_SERVICE_CONTEXT);
        colorService = (IColorService) getServletContext().getAttribute(ServiceConstant.COLOR_SERVICE_CONTEXT);
        statusService = (IStatusService) getServletContext().getAttribute(ServiceConstant.STATUS_SERVICE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!checkRole(request, response)) return;
        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        Car car = carService.getById(id);
        request.setAttribute(Attributes.CAR, car);
        logger.debug("Car selected");

        loadEntities(request, brandService, majorityClassService, colorService, statusService);

        request.getRequestDispatcher(PageNames.EDIT_CARS_PAGE).forward(request, response);
    }

}
