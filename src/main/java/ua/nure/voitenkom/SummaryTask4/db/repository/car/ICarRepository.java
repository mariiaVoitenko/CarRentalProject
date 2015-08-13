package ua.nure.voitenkom.SummaryTask4.db.repository.car;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
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

    void updateCar(Car car);

}