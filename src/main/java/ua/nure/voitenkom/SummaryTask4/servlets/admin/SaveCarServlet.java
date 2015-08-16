package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.*;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.BrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;
import ua.nure.voitenkom.SummaryTask4.service.color.ColorService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.MajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.photo.PhotoService;
import ua.nure.voitenkom.SummaryTask4.service.status.StatusService;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;
import ua.nure.voitenkom.SummaryTask4.validation.car.CarValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class SaveCarServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(SaveCarServlet.class);
    private PhotoService photoService;
    private CarService carService;
    private IValidator<CarFormBean> carFormBeanIValidator = new CarValidator();
    private StatusService statusService;
    private BrandService brandService;
    private ColorService colorService;
    private MajorityClassService majorityClassService;

    @Override
    public void init() throws ServletException {
        carService = (CarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
        brandService = (BrandService) getServletContext().getAttribute(ServiceConstant.BRAND_SERVICE_CONTEXT);
        majorityClassService = (MajorityClassService) getServletContext().getAttribute(ServiceConstant.CLASS_SERVICE_CONTEXT);
        colorService = (ColorService) getServletContext().getAttribute(ServiceConstant.COLOR_SERVICE_CONTEXT);
        statusService = (StatusService) getServletContext().getAttribute(ServiceConstant.STATUS_SERVICE_CONTEXT);
        photoService = (PhotoService) getServletContext().getAttribute(ServiceConstant.PHOTO_SERVICE_CONTEXT);
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

        Map<String, String> errors = validateData(carFormBean, carFormBeanIValidator);

        if (!errors.isEmpty()) {
            session.setAttribute(Attributes.MESSAGE, errors.get("error"));
            request.setAttribute(Attributes.CAR, car);
            logger.debug("Car selected");
            loadEntities(request, brandService, majorityClassService, colorService, statusService);
            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher(PageNames.EDIT_CARS_PAGE);
            requestDispatcher.forward(request, response);
            return;
        }
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
