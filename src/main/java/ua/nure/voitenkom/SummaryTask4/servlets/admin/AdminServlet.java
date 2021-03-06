package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import ua.nure.voitenkom.SummaryTask4.db.entity.*;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.service.brand.IBrandService;
import ua.nure.voitenkom.SummaryTask4.service.color.IColorService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.IMajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.status.IStatusService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.EntitiesValues;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNotNull;

/**
 * @author Mariia Voitenko
 */
public abstract class AdminServlet extends HttpServlet {

    protected Integer getRoleId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return isNotNull(session) ? (Integer) session.getAttribute(Attributes.ROLE_ID) : null;
    }

    protected void loadEntities(HttpServletRequest request, IBrandService brandService, IMajorityClassService majorityClassService, IColorService colorService, IStatusService statusService) {
        List<Brand> brands = brandService.getAll();
        request.setAttribute(Attributes.BRANDS, brands);

        List<MajorityClass> majorityClasses = majorityClassService.getAll();
        request.setAttribute(Attributes.CLASSES, majorityClasses);

        List<Color> colors = colorService.getAll();
        request.setAttribute(Attributes.COLORS, colors);

        List<Status> statuses = statusService.getAll();
        request.setAttribute(Attributes.STATUSES, statuses);
    }

    protected CarFormBean parseForm(HttpServletRequest request) {
        String model = request.getParameter(Attributes.MODEL);
        String brand = request.getParameter(Attributes.BRAND);
        String color = request.getParameter(Attributes.COLOR);
        String status = request.getParameter(Attributes.STATUS);
        String classType = request.getParameter(Attributes.CLASS);
        int sitsCount = Integer.parseInt(request.getParameter(Attributes.SITS_COUNT));
        int bigLuggageCount = Integer.parseInt(request.getParameter(Attributes.BIG_LUGGAGE_COUNT));
        int smallLuggageCount = Integer.parseInt(request.getParameter(Attributes.SMALL_LUGGAGE_COUNT));
        boolean hasConditioner = Boolean.valueOf(request.getParameter(Attributes.HAS_CONDITIONER));
        int price = Integer.parseInt(request.getParameter(Attributes.PRICE));
        int doorsCount = Integer.parseInt(request.getParameter(Attributes.DOORS_COUNT));
        return new CarFormBean(model, price, doorsCount, hasConditioner, bigLuggageCount, smallLuggageCount, sitsCount, brand, color, classType, status);
    }

    protected Map<String, String> validateData(CarFormBean carFormBean, IValidator<CarFormBean> carFormBeanIValidator) {
        return carFormBeanIValidator.validate(carFormBean);
    }

    protected void fillEntity(Car car, CarFormBean carFormBean, IStatusService statusService, IBrandService brandService, IColorService colorService, IMajorityClassService majorityClassService) {
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
    }

    protected boolean checkRole(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (getRoleId(request) == null || getRoleId(request) != Integer.parseInt(EntitiesValues.ADMIN_ROLE_ID)) {
            response.sendRedirect("/" + PageNames.ACCESS_DENIED_PAGE);
            return false;
        }
        return true;
    }

    protected boolean checkManagerRole(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (getRoleId(request) == null || getRoleId(request) != Integer.parseInt(EntitiesValues.MANAGER_ROLE_ID)) {
            response.sendRedirect("/" + PageNames.ACCESS_DENIED_PAGE);
            return false;
        }
        return true;
    }

}
