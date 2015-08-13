package ua.nure.voitenkom.SummaryTask4.servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.*;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.BrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;
import ua.nure.voitenkom.SummaryTask4.service.color.ColorService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.MajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.rent.RentService;
import ua.nure.voitenkom.SummaryTask4.service.status.StatusService;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;
import ua.nure.voitenkom.SummaryTask4.servlets.admin.AdminServlet;
import ua.nure.voitenkom.SummaryTask4.servlets.authentication.AuthenticationServlet;
import ua.nure.voitenkom.SummaryTask4.validation.DateValidator;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;
import ua.nure.voitenkom.SummaryTask4.validation.car.CarValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "rentCar")
public class RentCarServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(RentCarServlet.class);
    private CarService carService;
    private RentService rentService;
    private BrandService brandService;
    private MajorityClassService majorityClassService;
    private ColorService colorService;
    private StatusService statusService;
    private IValidator<Date> dateValidator = new DateValidator();

    @Override
    public void init() throws ServletException {
        carService = (CarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
        rentService = (RentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
        brandService = (BrandService) getServletContext().getAttribute(ServiceConstant.BRAND_SERVICE_CONTEXT);
        majorityClassService = (MajorityClassService) getServletContext().getAttribute(ServiceConstant.CLASS_SERVICE_CONTEXT);
        colorService = (ColorService) getServletContext().getAttribute(ServiceConstant.COLOR_SERVICE_CONTEXT);
        statusService = (StatusService) getServletContext().getAttribute(ServiceConstant.STATUS_SERVICE_CONTEXT);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (getAuthUserId(request) == null) {
            session.setAttribute(Attributes.START_DATE, request.getParameter(Attributes.START_DATE));
            session.setAttribute(Attributes.END_DATE, request.getParameter(Attributes.END_DATE));
            response.sendRedirect(PageNames.EMPTY_PAGE + PageNames.LOGIN_PAGE);
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = null;
        String endDate = null;

        if (session.getAttribute(Attributes.START_DATE) != null && session.getAttribute(Attributes.END_DATE) != null) {
            startDate = session.getAttribute(Attributes.START_DATE).toString();
            endDate = session.getAttribute(Attributes.END_DATE).toString();
        } else {
            startDate = request.getParameter(Attributes.START_DATE);
            endDate = request.getParameter(Attributes.END_DATE);
            session.setAttribute(Attributes.START_DATE, startDate);
            session.setAttribute(Attributes.END_DATE, endDate);
        }

        Date start = null;
        Date end = null;
        try {
            start = simpleDateFormat.parse(startDate);
            end = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            logger.error("Can't parse date");
            return;
        }

        Map<String, String> errors = dateValidator.validate(start);
        errors = dateValidator.validate(end);
        errors = validateDates(start, end, errors);

        if (errors.size() != 0) {
            request.setAttribute(Attributes.MESSAGE, errors.get("error"));
            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher(PageNames.MAIN_PAGE);
            requestDispatcher.forward(request, response);
            return;
        }

        List<CarFormBean> carList = carService.getFullInformationForAll();

        loadLists(request);

        Timestamp startTimestamp = new Timestamp(start.getTime());
        Timestamp endTimestamp = new Timestamp(end.getTime());
        List<Rent> rents = rentService.selectRentsForDates(startTimestamp, endTimestamp);
        if (rents.size() != 0) {
            List<CarFormBean> notRentedCars = getNotRentedCars(request, rents, carList);
            request.setAttribute(Attributes.CARS, notRentedCars);
        }
        else{
            request.setAttribute(Attributes.CARS, carList);
        }
        logger.debug("All cars information has been got");

        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.RENT_CARS_PAGE);
        requestDispatcher.forward(request, response);
    }

    private void loadLists(HttpServletRequest request) {
        List<Brand> brands = brandService.getAll();
        request.setAttribute(Attributes.BRANDS, brands);

        List<MajorityClass> majorityClasses = majorityClassService.getAll();
        request.setAttribute(Attributes.CLASSES, majorityClasses);

        List<Color> colors = colorService.getAll();
        request.setAttribute(Attributes.COLORS, colors);

        List<Status> statuses = statusService.getAll();
        request.setAttribute(Attributes.STATUSES, statuses);
    }

    private List<CarFormBean> getNotRentedCars(HttpServletRequest request, List<Rent> rents, List<CarFormBean> carList) {
        List<CarFormBean> notRentedCars = new ArrayList<>();
        for (Rent rent : rents) {
            int carId = rent.getCarId();
            for (CarFormBean car : carList) {
                if (car.getId() != carId && car.getStatusName().equals("Free")) {
                    notRentedCars.add(car);
                }
            }
        }
        return notRentedCars;
    }

    private Map<String, String> validateDates(Date start, Date end, Map<String, String> errorMap) {
        if (end.before(start)) {
            String message = "Start date can't be before end date";
            writeMessage(message, errorMap);
        }
        return errorMap;
    }

    private void writeMessage(String message, Map<String, String> errorMap) {
        String error = errorMap.get("error");
        if (error != null) {
            errorMap.put("error", error + message);
        } else {
            errorMap.put("error", message);
        }
    }
}
