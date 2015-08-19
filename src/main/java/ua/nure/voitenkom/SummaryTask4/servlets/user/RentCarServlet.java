package ua.nure.voitenkom.SummaryTask4.servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.service.account.MailService;
import ua.nure.voitenkom.SummaryTask4.service.brand.IBrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.ICarService;
import ua.nure.voitenkom.SummaryTask4.service.check.ICheckService;
import ua.nure.voitenkom.SummaryTask4.service.color.IColorService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.IMajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.pdf.IPDFService;
import ua.nure.voitenkom.SummaryTask4.service.pdf.PDFService;
import ua.nure.voitenkom.SummaryTask4.service.rent.IRentService;
import ua.nure.voitenkom.SummaryTask4.service.status.IStatusService;
import ua.nure.voitenkom.SummaryTask4.service.user.IUserService;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.EntitiesValues;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;
import ua.nure.voitenkom.SummaryTask4.db.entity.*;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.BrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;
import ua.nure.voitenkom.SummaryTask4.service.check.CheckService;
import ua.nure.voitenkom.SummaryTask4.service.color.ColorService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.MajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.rent.RentService;
import ua.nure.voitenkom.SummaryTask4.service.status.StatusService;
import ua.nure.voitenkom.SummaryTask4.servlets.authentication.AuthenticationServlet;
import ua.nure.voitenkom.SummaryTask4.validation.DateValidator;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static ua.nure.voitenkom.SummaryTask4.util.DateManager.parseDate;
import static ua.nure.voitenkom.SummaryTask4.util.DateManager.getDaysCount;

public class RentCarServlet extends AuthenticationServlet {

    private static final Logger logger = LoggerFactory.getLogger(RentCarServlet.class);
    private ICarService carService;
    private IRentService rentService;
    private IBrandService brandService;
    private IMajorityClassService majorityClassService;
    private IColorService colorService;
    private IStatusService statusService;
    private IValidator<Date> dateValidator = new DateValidator();
    private ICheckService checkService;
    private IPDFService pdfService;
    private IUserService userService;
    private String host;
    private String port;
    private String userEmail;
    private String password;

    @Override
    public void init() throws ServletException {
        carService = (ICarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
        rentService = (IRentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
        brandService = (IBrandService) getServletContext().getAttribute(ServiceConstant.BRAND_SERVICE_CONTEXT);
        majorityClassService = (IMajorityClassService) getServletContext().getAttribute(ServiceConstant.CLASS_SERVICE_CONTEXT);
        colorService = (IColorService) getServletContext().getAttribute(ServiceConstant.COLOR_SERVICE_CONTEXT);
        statusService = (IStatusService) getServletContext().getAttribute(ServiceConstant.STATUS_SERVICE_CONTEXT);
        checkService = (ICheckService) getServletContext().getAttribute(ServiceConstant.CHECK_SERVICE_CONTEXT);
        pdfService = (IPDFService) getServletContext().getAttribute(ServiceConstant.PDF_SERVICE_CONTEXT);
        userService = (IUserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
        host = getServletContext().getInitParameter(ServiceConstant.HOST_PARAM);
        port = getServletContext().getInitParameter(ServiceConstant.PORT_PARAM);
        userEmail = getServletContext().getInitParameter(ServiceConstant.USER_EMAIL_PARAM);
        password = getServletContext().getInitParameter(ServiceConstant.USER_PASSWORD_PARAM);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        HttpSession session = request.getSession();
        String driver = session.getAttribute(Attributes.DRIVER).toString();
        String startDate = session.getAttribute(Attributes.START_DATE).toString();
        String endDate = session.getAttribute(Attributes.END_DATE).toString();
        Date start = parseDate(startDate, logger);
        Date end = parseDate(endDate, logger);
        long days = getDaysCount(startDate, endDate);
        boolean isDriven = driver.isEmpty() ? false : true;

        Car car = carService.getById(id);
        int sum = isDriven ? checkService.getSumWithDriver(car, days) : checkService.getSum(car, days);
        Check check = new Check(sum, false);
        checkService.insert(check);
        logger.debug("Check was inserted");

        int userId = Integer.parseInt(session.getAttribute(Attributes.USER_ID).toString());
        int checkId = checkService.selectLastInsertedId();
        Rent rent = new Rent(isDriven,
                car.getId(), userId, Integer.parseInt(EntitiesValues.NOT_APPROVEN_DECLINE_ID),
                checkId, new Timestamp(start.getTime()), new Timestamp(end.getTime()));
        rentService.insert(rent);
        logger.debug("Rent was inserted");

        CarFormBean carFormBean = carService.getFullCarInformationById(id);
        String fileName = pdfService.createFileName(userId, checkId);
        String path = pdfService.createPath(fileName);
        pdfService.createPdf(path, carFormBean, rent, check);

        String login = userService.selectById(rent.getUserId()).getLogin();

        try {
            MailService.sendEmailWithDocument(host, port, login, userEmail, password, path);
        } catch (MessagingException e) {
            logger.error("Unable to send mail");
        }
        response.sendRedirect(Mappings.HISTORY_MAPPING);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String startDate = null;
        String endDate = null;
        String driver = null;
        if (getAuthUserId(request) == null) {
            session.setAttribute(Attributes.START_DATE, request.getParameter(Attributes.START_DATE));
            session.setAttribute(Attributes.END_DATE, request.getParameter(Attributes.END_DATE));
            if (request.getParameter(Attributes.DRIVER) == null) {
                session.setAttribute(Attributes.DRIVER, "");
            } else {
                session.setAttribute(Attributes.DRIVER, request.getParameter(Attributes.DRIVER));
            }

            response.sendRedirect(PageNames.LOGIN_PAGE);
            return;
        }

        if (request.getParameter(Attributes.START_DATE) == null) {
            startDate = session.getAttribute(Attributes.START_DATE).toString();
            endDate = session.getAttribute(Attributes.END_DATE).toString();
            driver = session.getAttribute(Attributes.DRIVER).toString();
        } else {
            startDate = request.getParameter(Attributes.START_DATE);
            endDate = request.getParameter(Attributes.END_DATE);
            if (request.getParameter(Attributes.DRIVER) == null) {
                driver = "";
            } else {
                driver = request.getParameter(Attributes.DRIVER);
            }
            session.setAttribute(Attributes.START_DATE, startDate);
            session.setAttribute(Attributes.END_DATE, endDate);
            session.setAttribute(Attributes.DRIVER, driver);
        }

        Date start = parseDate(startDate, logger);
        Date end = parseDate(endDate, logger);

        if (start == null || end == null) {
            request.setAttribute(Attributes.MESSAGE, "Can't parse dates");
            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher(PageNames.MAIN_PAGE);
            requestDispatcher.forward(request, response);
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
            List<CarFormBean> notRentedCars = carService.getNotRentedCars(rents, carList);
            request.setAttribute(Attributes.CARS, notRentedCars);
        } else {
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
