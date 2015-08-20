package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.brand.IBrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.ICarService;
import ua.nure.voitenkom.SummaryTask4.service.color.IColorService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.IMajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.photo.IPhotoService;
import ua.nure.voitenkom.SummaryTask4.service.status.IStatusService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;
import ua.nure.voitenkom.SummaryTask4.db.entity.*;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import static ua.nure.voitenkom.SummaryTask4.util.PhotoValidator.isPhotoIncorrect;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class SaveCarServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(SaveCarServlet.class);
    private IPhotoService photoService;
    private ICarService carService;
    private IStatusService statusService;
    private IBrandService brandService;
    private IColorService colorService;
    private IMajorityClassService majorityClassService;

    @Override
    public void init() throws ServletException {
        carService = (ICarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
        brandService = (IBrandService) getServletContext().getAttribute(ServiceConstant.BRAND_SERVICE_CONTEXT);
        majorityClassService = (IMajorityClassService) getServletContext().getAttribute(ServiceConstant.CLASS_SERVICE_CONTEXT);
        colorService = (IColorService) getServletContext().getAttribute(ServiceConstant.COLOR_SERVICE_CONTEXT);
        statusService = (IStatusService) getServletContext().getAttribute(ServiceConstant.STATUS_SERVICE_CONTEXT);
        photoService = (IPhotoService) getServletContext().getAttribute(ServiceConstant.PHOTO_SERVICE_CONTEXT);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkRole(request, response);
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        Car car = carService.getById(id);
        CarFormBean carFormBean = parseForm(request);
        Part photo = request.getPart(Attributes.PHOTO);

        Map<String, String> errors = new HashMap<>();
        if (photo.getSize() != 0) {
            if (isPhotoIncorrect(photo)) {
                errors.put("error", "Some error with photo has occurred");
            }
            photoService.saveCarPicture(car, photo);
        }

        fillEntity(car, carFormBean, statusService, brandService, colorService, majorityClassService);

        carService.update(car);
        logger.debug("Car {} was updated", id);
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.CARS_MAPPING);
    }
}
