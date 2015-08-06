package ua.nure.voitenkom.SummaryTask4.service.car;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.repository.car.ICarRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.util.List;

/**
 * Created by Maria on 05.08.2015.
 */
public class CarService implements ICarService{

    private final ITransactionManager transactionManager;
    private final ICarRepository carRepository;

    public CarService(ITransactionManager transactionManager, ICarRepository carRepository) {
        this.transactionManager = transactionManager;
        this.carRepository = carRepository;
    }

    @Override
    public void add(final Car car) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                carRepository.insert(car);
                return null;
            }
        });
    }

    @Override
    public void delete(final int id) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                carRepository.deleteById(id);
                return null;
            }
        });
    }

    @Override
    public List<Car> getAll() {
        return transactionManager.doInTransaction(new Operation<List<Car>>() {
            @Override
            public List<Car> doOperation() {
                return carRepository.selectAll();
            }
        });
    }

    @Override
    public List<CarFormBean> getFullInformationForAll() {
        return transactionManager.doInTransaction(new Operation<List<CarFormBean>>() {
            @Override
            public List<CarFormBean> doOperation() {
                return carRepository.getFullCarInformation();
            }
        });
    }

    @Override
    public String getBrandName(final int id) {
        return transactionManager.doInTransaction(new Operation<String>() {
            @Override
            public String doOperation() {
                return carRepository.getBrandName(id);
            }
        });
    }

    @Override
    public String getClassName(final int id) {
        return transactionManager.doInTransaction(new Operation<String>() {
            @Override
            public String doOperation() {
                return carRepository.getClassName(id);
            }
        });
    }

    @Override
    public void updateAvailableCount(final Car car) {
       transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                carRepository.updateAvailableCount(car);
                return null;
            }
        });
    }

    @Override
    public void updateStatus(final Car car) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                carRepository.updateStatus(car);
                return null;
            }
        });
    }

    @Override
    public void updatePrice(final Car car) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                carRepository.updatePrice(car);
                return null;
            }
        });
    }
}
