package ua.nure.voitenkom.SummaryTask4.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.criteria.Criteria;
import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.IBrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.ICarService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.IMajorityClassService;
import ua.nure.voitenkom.SummaryTask4.servlets.admin.ApplicationServlet;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNotNull;

public class SortingServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServlet.class);
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
        Criteria criteria = fillCriteria(request);
        List<CarFormBean> carFormBeans = carService.getSortedCars(criteria);

        request.setAttribute(Attributes.CARS, carFormBeans);
        request.setAttribute(Attributes.BRANDS, brandService.getAll());
        request.setAttribute(Attributes.CLASSES, majorityClassService.getAll());
        request.setAttribute(Attributes.CRITERIA, criteria);

        logger.debug("All cars information has been got");

        request.getRequestDispatcher(PageNames.CARS_PAGE).forward(request, response);
    }

    private Criteria fillCriteria(HttpServletRequest request) {
        Criteria criteria = new Criteria();
        String brand = request.getParameter("brand");
        if (isNotNull(brand)) {
            criteria.setBrand(FieldsContainer.FIELD_BRANDS_ID);
            criteria.setBrandValue(Integer.parseInt(brand));
        }
        String classType = request.getParameter("class");
        if (isNotNull(classType)) {
            criteria.setClassType(FieldsContainer.FIELD_CLASSES_ID);
            criteria.setClassValue(Integer.parseInt(classType));
        }
        String nameSort = request.getParameter("nameSort");
        String priceSort = request.getParameter("priceSort");
        if (isNotNull(nameSort) || isNotNull(priceSort)) {
            criteria.setSortType(isNotNull(priceSort) ? priceSort : nameSort);
            criteria.setSortColumn(isNotNull(priceSort) ? FieldsContainer.FIELD_PRICE : FieldsContainer.FIELD_MODEL);
        }
        return criteria;
    }

}
