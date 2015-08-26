package ua.nure.voitenkom.SummaryTask4.service.car;

import ua.nure.voitenkom.SummaryTask4.criteria.Criteria;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.util.List;

public interface ICarService {

    void add(final Car car);

    void delete(final int id);

    List<Car> getAll();

    List<CarFormBean> getFullInformationForAll();

    Car getById(int id);

    void update(Car car);

    List<CarFormBean> getNotRentedCars(List<Rent> rents, List<CarFormBean> carList);

    CarFormBean getFullCarInformationById(int id);

    List<CarFormBean> getSortedCars(Criteria criteria);

}
