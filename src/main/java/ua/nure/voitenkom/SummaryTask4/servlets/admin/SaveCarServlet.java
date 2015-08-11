package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.*;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.formbean.RegistrationFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.BrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;
import ua.nure.voitenkom.SummaryTask4.service.color.ColorService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.MajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.photo.PhotoService;
import ua.nure.voitenkom.SummaryTask4.service.role.RoleService;
import ua.nure.voitenkom.SummaryTask4.service.status.StatusService;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;
import ua.nure.voitenkom.SummaryTask4.validation.car.CarValidator;
import ua.nure.voitenkom.SummaryTask4.validation.user.RegistrationValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "saveCar")
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
        if (getRoleId(request) != 1) {
            response.sendRedirect(PageNames.EMPTY_PAGE + PageNames.ACCESS_DENIED_PAGE);
            return;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getRoleId(request) != 1) {
            response.sendRedirect(PageNames.EMPTY_PAGE + PageNames.ACCESS_DENIED_PAGE);
            return;
        }
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        Car car = carService.getById(id);
        CarFormBean carFormBean = parseForm(request);
        Part photo = request.getPart(Attributes.PHOTO);

        Map<String, String> errors = validateData(carFormBean);
        if (!errors.isEmpty()) {
            session.setAttribute(Attributes.MESSAGE, errors.get("error"));
            request.setAttribute(Attributes.CAR, car);
            logger.debug("Car selected");

            List<Brand> brands = brandService.getAll();
            request.setAttribute(Attributes.BRANDS, brands);
            logger.debug("Brands selected");

            List<MajorityClass> majorityClasses = majorityClassService.getAll();
            request.setAttribute(Attributes.CLASSES, majorityClasses);
            logger.debug("Classes selected");

            List<Color> colors = colorService.getAll();
            request.setAttribute(Attributes.COLORS, colors);
            logger.debug("Colors selected");

            List<Status> statuses = statusService.getAll();
            request.setAttribute(Attributes.STATUSES, statuses);
            logger.debug("Statuses selected");
            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher(PageNames.EDIT_CARS_PAGE);
            requestDispatcher.forward(request, response);
            return;
        }
        if (photo != null) {
            if (isPhotoIncorrect(photo)) {
                errors.put("error", "Some error occured");
            }
            photoService.saveCarPicture(car, photo);
        }
        car.setModel(carFormBean.getModel());
        car.setBigLuggageCount(carFormBean.getBigLuggageCount());
        car.setDoorsCount(carFormBean.getDoorsCount());
        car.setHasConditioner(carFormBean.isHasConditioner());
        car.setSmallLuggageCount(carFormBean.getSmallLuggageCount());
        car.setPrice(carFormBean.getPrice());
        car.setSitsCount(carFormBean.getSitsCount());
        Brand brand = brandService.selectByName(carFormBean.getBrandName());
        car.setBrandId(brand.getId());
        Color color = colorService.selectByName(carFormBean.getColorName());
        car.setColorId(color.getId());
        Status status = statusService.selectByName(carFormBean.getStatusName());
        car.setStatusId(status.getId());
        MajorityClass majorityClass = majorityClassService.selectByName(carFormBean.getClassName());
        car.setClassTypeId(majorityClass.getId());
        car.setAvailableCount(carFormBean.getAvailableCount());
        carService.update(car);
        logger.debug("Car {} was updated", id);
        response.sendRedirect(PageNames.EMPTY_PAGE + PageNames.ADMIN + "cars");
    }

    private boolean isPhotoIncorrect(Part photo) {
        return photo == null || !"image/jpeg".equals(photo.getContentType()) || photo.getSize() == 0;
    }

    private CarFormBean parseForm(HttpServletRequest request) {
        String model = request.getParameter(Attributes.MODEL);
        String brand = request.getParameter(Attributes.BRAND);
        String color = request.getParameter(Attributes.COLOR);
        String status = request.getParameter(Attributes.STATUS);
        String classType = request.getParameter(Attributes.CLASS);
        int sitsCount = Integer.parseInt(request.getParameter(Attributes.SITS_COUNT));
        int bigLuggageCount = Integer.parseInt(request.getParameter(Attributes.BIG_LUGGAGE_COUNT));
        int smallLuggageCount = Integer.parseInt(request.getParameter(Attributes.SMALL_LUGGAGE_COUNT));
        int availableCount = Integer.parseInt(request.getParameter(Attributes.AVAILABLE_COUNT));
        boolean hasConditioner = Boolean.valueOf(request.getParameter(Attributes.HAS_CONDITIONER));
        int price = Integer.parseInt(request.getParameter(Attributes.PRICE));
        int doorsCount = Integer.parseInt(request.getParameter(Attributes.DOORS_COUNT));
        CarFormBean carFormBean = new CarFormBean(model, price, doorsCount, hasConditioner, bigLuggageCount, smallLuggageCount, sitsCount, brand, color, classType, status, availableCount);
        return carFormBean;
    }

    private Map<String, String> validateData(CarFormBean carFormBean) {
        Map<String, String> errors = carFormBeanIValidator.validate(carFormBean);
        return errors;
    }
}
