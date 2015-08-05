package ua.nure.voitenkom.SummaryTask4.db.repository.car;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.repository.IAbstractRepository;

/**
 * Created by Maria on 05.08.2015.
 */
public interface ICarRepository extends IAbstractRepository<Car> {

    Car findById(int id);

    String getBrandName(int id);

    String getClassName(int id);

    void create(Car car);

    void updateAvailableCount(Car car);

    void updateStatus(Car car);

    void updatePrice(Car car);
}