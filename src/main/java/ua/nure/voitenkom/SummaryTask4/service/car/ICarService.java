package ua.nure.voitenkom.SummaryTask4.service.car;

import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.util.List;

/**
 * Created by Maria on 05.08.2015.
 */
public interface ICarService {

    void add(final Car car);

    void delete(final int id);

    List<Car> getAll();

    List<CarFormBean> getFullInformationForAll();

    String getBrandName(final int id);

    String getClassName(final int id);

    void updateAvailableCount(final Car car);

    void updateStatus(final Car car);

    void updatePrice(final Car car);

}
