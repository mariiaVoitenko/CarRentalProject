package ua.nure.voitenkom.SummaryTask4.db.repository.car;

import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.util.List;

public interface ICarRepository extends IAbstractRepository<Car> {

    String getBrandName(int id);

    String getClassName(int id);

    void updateStatus(Car car);

    void updatePrice(Car car);

    void insert(Car car);

    List<CarFormBean> getFullCarInformation();

    CarFormBean getFullCarInformationById(int id);

    void updateCar(Car car);

    List<Car> getCarsByBrandId(int id);

    List<Car> getCarsByClassId(int id);

    List<CarFormBean> getSortedCars(String sql);

}