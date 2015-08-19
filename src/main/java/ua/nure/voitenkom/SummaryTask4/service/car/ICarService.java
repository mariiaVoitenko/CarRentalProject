package ua.nure.voitenkom.SummaryTask4.service.car;

import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.util.List;

public interface ICarService {

    void add(final Car car);

    void delete(final int id);

    List<Car> getAll();

    List<CarFormBean> getFullInformationForAll();

    String getBrandName(final int id);

    String getClassName(final int id);

    void updateStatus(final Car car);

    void updatePrice(final Car car);

    Car getById(int id);

    void update(Car car);

    List<CarFormBean> getNotRentedCars(List<Rent> rents, List<CarFormBean> carList);

    CarFormBean getFullCarInformationById(int id);

    List<Car> getCarsByBrandId(int id);

    List<Car> getCarsByClassId(int id);


}
