package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "addCar")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class AddCarServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(AddCarServlet.class);
    private CarService carService;
    private BrandService brandService;
    private MajorityClassService majorityClassService;
    private ColorService colorService;
    private StatusService statusService;
    private PhotoService photoService;
    private IValidator<CarFormBean> carFormBeanIValidator = new CarValidator();

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
        checkRole(request, response);

        loadEntities(request, brandService, majorityClassService, colorService, statusService);
        logger.debug("Dropdowns are loaded");

        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.ADD_CARS_PAGE);
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkRole(request, response);

        HttpSession session = request.getSession();
        CarFormBean carFormBean = parseForm(request);
        Part photo = request.getPart(Attributes.PHOTO);
        Car car = new Car();
        Map<String, String> errors = validateData(carFormBean, carFormBeanIValidator);

        if (!errors.isEmpty()) {
            session.setAttribute(Attributes.MESSAGE, errors.get("error"));
            logger.debug("Car selected");
            loadEntities(request, brandService, majorityClassService, colorService, statusService);
            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher(PageNames.ADD_CARS_PAGE);
            requestDispatcher.forward(request, response);
            return;
        }
        if (photo != null) {
            if (isPhotoIncorrect(photo)) {
                errors.put("error", "Some error with photo has occurred");
            }
            photoService.saveCarPicture(car, photo);
        }

        fillEntity(car, carFormBean, statusService, brandService, colorService, majorityClassService);

        carService.add(car);
        logger.debug("Car was updated");
        response.sendRedirect(PageNames.EMPTY_PAGE + PageNames.ADMIN + PageNames.CARS_MAPPING);
    }
}