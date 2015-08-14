package ua.nure.voitenkom.SummaryTask4.service.car;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.db.repository.car.ICarRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.util.ArrayList;
import java.util.List;

public class CarService implements ICarService {

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

    @Override
    public Car getById(final int id) {
        return transactionManager.doInTransaction(new Operation<Car>() {
            @Override
            public Car doOperation() {
                return carRepository.selectById(id);
            }
        });
    }

    @Override
    public void update(final Car car) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                carRepository.updateCar(car);
                return null;
            }
        });
    }

    @Override
    public List<CarFormBean> getNotRentedCars(List<Rent> rents, List<CarFormBean> carList) {
        List<CarFormBean> notRentedCars = new ArrayList<>();
        for (Rent rent : rents) {
            int carId = rent.getCarId();
            for (CarFormBean car : carList) {
                if (car.getId() != carId && car.getStatusName().equals("Free")) {
                    notRentedCars.add(car);
                }
            }
        }
        return notRentedCars;
    }
}
