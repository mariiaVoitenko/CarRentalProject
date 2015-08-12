package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.db.entity.*;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.service.brand.BrandService;
import ua.nure.voitenkom.SummaryTask4.service.color.ColorService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.MajorityClassService;
import ua.nure.voitenkom.SummaryTask4.service.status.StatusService;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNotNull;

public abstract class AdminServlet extends HttpServlet {

    protected Integer getRoleId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return isNotNull(session) ? (Integer) session.getAttribute(Attributes.ROLE_ID) : null;
    }

    protected void loadEntities(HttpServletRequest request, BrandService brandService, MajorityClassService majorityClassService, ColorService colorService, StatusService statusService) {
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
        int availableCount = Integer.parseInt(request.getParameter(Attributes.AVAILABLE_COUNT));
        boolean hasConditioner = Boolean.valueOf(request.getParameter(Attributes.HAS_CONDITIONER));
        int price = Integer.parseInt(request.getParameter(Attributes.PRICE));
        int doorsCount = Integer.parseInt(request.getParameter(Attributes.DOORS_COUNT));
        CarFormBean carFormBean = new CarFormBean(model, price, doorsCount, hasConditioner, bigLuggageCount, smallLuggageCount, sitsCount, brand, color, classType, status, availableCount);
        return carFormBean;
    }

    protected Map<String, String> validateData(CarFormBean carFormBean, IValidator<CarFormBean> carFormBeanIValidator) {
        Map<String, String> errors = carFormBeanIValidator.validate(carFormBean);
        return errors;
    }

    protected void fillEntity(Car car, CarFormBean carFormBean, StatusService statusService, BrandService brandService, ColorService colorService, MajorityClassService majorityClassService) {
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
    }

    protected boolean isPhotoIncorrect(Part photo) {
        return photo == null || !"image/jpeg".equals(photo.getContentType()) || photo.getSize() == 0;
    }
}
