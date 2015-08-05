package ua.nure.voitenkom.SummaryTask4.db.repository.car;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 05.08.2015.
 */
public interface ICarRepository extends IAbstractRepository<Car> {

    String getBrandName(int id);

    String getClassName(int id);

    void updateAvailableCount(Car car);

    void updateStatus(Car car);

    void updatePrice(Car car);

    void insert(Car car);

}