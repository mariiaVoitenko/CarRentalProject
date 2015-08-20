package ua.nure.voitenkom.SummaryTask4.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.comparator.SortedByModel;
import ua.nure.voitenkom.SummaryTask4.comparator.SortedByModelDescending;
import ua.nure.voitenkom.SummaryTask4.comparator.SortedByPrice;
import ua.nure.voitenkom.SummaryTask4.comparator.SortedByPriceDescending;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.brand.IBrandService;
import ua.nure.voitenkom.SummaryTask4.service.car.ICarService;
import ua.nure.voitenkom.SummaryTask4.service.majorityclass.IMajorityClassService;
import ua.nure.voitenkom.SummaryTask4.servlets.admin.ApplicationServlet;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CarFormBean> carFormBeans = new ArrayList<>();
        if (request.getParameter(Attributes.BRAND) != null) {
            int brandId = Integer.parseInt(request.getParameter(Attributes.BRAND));
            List<Car> cars = carService.getCarsByBrandId(brandId);
            for (Car car : cars) {
                carFormBeans.add(carService.getFullCarInformationById(car.getId()));
            }
        } else {
            if (request.getParameter(Attributes.CLASS) != null) {
                int classId = Integer.parseInt(request.getParameter(Attributes.CLASS));
                List<Car> cars = carService.getCarsByClassId(classId);
                for (Car car : cars) {
                    carFormBeans.add(carService.getFullCarInformationById(car.getId()));
                }
            }
            else{
                if (request.getParameter(Attributes.NAME_SORT) != null) {
                    carFormBeans = carService.getFullInformationForAll();
                    if(request.getParameter(Attributes.NAME_SORT).equals(Attributes.ASCENDING)){
                        Collections.sort(carFormBeans, new SortedByModel());
                    }
                    else{
                        Collections.sort(carFormBeans, new SortedByModelDescending());
                    }
                }
                else{
                    if (request.getParameter(Attributes.PRICE_SORT) != null) {
                        carFormBeans = carService.getFullInformationForAll();
                        if(request.getParameter(Attributes.PRICE_SORT).equals(Attributes.ASCENDING)){
                            Collections.sort(carFormBeans, new SortedByPrice());
                        }
                        else{
                            Collections.sort(carFormBeans, new SortedByPriceDescending());
                        }
                    }
                }
            }
        }

        request.setAttribute(Attributes.CARS, carFormBeans);
        request.setAttribute(Attributes.BRANDS, brandService.getAll());
        request.setAttribute(Attributes.CLASSES, majorityClassService.getAll());

        logger.debug("All cars information has been got");

        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.CARS_PAGE);
        requestDispatcher.forward(request, response);


    }

}
