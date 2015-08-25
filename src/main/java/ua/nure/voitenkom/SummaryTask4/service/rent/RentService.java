package ua.nure.voitenkom.SummaryTask4.service.rent;

import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.db.repository.car.ICarRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.check.ICheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.decline.IDeclineRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.rent.IRentRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.user.IUserRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import ua.nure.voitenkom.SummaryTask4.formbean.ApplicationFormBean;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.formbean.RentFormBean;

import static ua.nure.voitenkom.SummaryTask4.util.DateManager.timestampToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RentService implements IRentService {

    private final ITransactionManager transactionManager;
    private final IRentRepository rentRepository;
    private final ICheckRepository checkRepository;
    private final ICarRepository carRepository;
    private final IDeclineRepository declineRepository;
    private final IUserRepository userRepository;


    public RentService(ITransactionManager transactionManager, IRentRepository rentRepository,
                       ICheckRepository checkRepository, IDeclineRepository declineRepository,
                       ICarRepository carRepository, IUserRepository userRepository) {
        this.transactionManager = transactionManager;
        this.rentRepository = rentRepository;
        this.checkRepository = checkRepository;
        this.carRepository = carRepository;
        this.declineRepository = declineRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void insert(final Rent rent) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                rentRepository.insert(rent);
                return null;
            }
        });
    }

    @Override
    public void update(final Rent rent) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                rentRepository.update(rent);
                return null;
            }
        });
    }

    @Override
    public void updateDecline(final Rent rent) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                rentRepository.updateDecline(rent);
                return null;
            }
        });
    }

    @Override
    public List<Rent> selectReturnedCarRents() {
        return transactionManager.doInTransaction(new Operation<List<Rent>>() {
            @Override
            public List<Rent> doOperation() {
                return rentRepository.selectReturnedCars();
            }
        });
    }

    @Override
    public void updateReturnedState(final int rentId) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                rentRepository.updateReturnedState(rentId);
                return null;
            }
        });
    }

    @Override
    public void updateApprovedState(final int rentId) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                rentRepository.updateApprovedState(rentId);
                return null;
            }
        });
    }

    @Override
    public void updateFinishedState(final int rentId) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                rentRepository.updateFinishedState(rentId);
                return null;
            }
        });
    }

    @Override
    public List<Rent> selectAllForUser(final int id) {
        return transactionManager.doInTransaction(new Operation<List<Rent>>() {
            @Override
            public List<Rent> doOperation() {
                return rentRepository.selectAllForUser(id);
            }
        });
    }

    @Override
    public List<Rent> selectRentsForDates(final Timestamp start, final Timestamp end) {
        return transactionManager.doInTransaction(new Operation<List<Rent>>() {
            @Override
            public List<Rent> doOperation() {
                return rentRepository.selectRentsForDates(start, end);
            }
        });
    }

    @Override
    public List<RentFormBean> getUserRents(final List<Rent> rentList) {
        return transactionManager.doInTransaction(new Operation<List<RentFormBean>>() {
            @Override
            public List<RentFormBean> doOperation() {
                List<RentFormBean> rents = new ArrayList<>();
                for (Rent rent : rentList) {
                    CarFormBean car = carRepository.getFullCarInformationById(rent.getCarId());
                    Check check = checkRepository.selectById(rent.getCheckId());
                    Decline decline = declineRepository.selectById(rent.getDeclineId());
                    RentFormBean rentFormBean = new RentFormBean(rent.isDriven(), car, check, decline,
                            timestampToString(rent.getStartDate()), timestampToString(rent.getEndDate()),
                            rent.isReturned(), rent.isApproved(), rent.isFinished(), rent.getId());
                    rents.add(rentFormBean);
                }
                return rents;
            }
        });
    }

    @Override
    public RentFormBean rentToRentFormBean(final Rent rent) {
        return transactionManager.doInTransaction(new Operation<RentFormBean>() {
            @Override
            public RentFormBean doOperation() {
                Check check = checkRepository.selectById(rent.getCheckId());
                CarFormBean car = carRepository.getFullCarInformationById(rent.getCarId());
                User user = userRepository.selectById(rent.getUserId());
                return new RentFormBean(rent.isDriven(), car, check,
                        timestampToString(rent.getStartDate()), timestampToString(rent.getEndDate()), user, rent.getId());
            }
        });
    }


    @Override
    public Rent selectById(final int id) {
        return transactionManager.doInTransaction(new Operation<Rent>() {
            @Override
            public Rent doOperation() {
                return rentRepository.selectById(id);
            }
        });
    }

    @Override
    public List<Rent> selectPayedUnapproved() {
        return transactionManager.doInTransaction(new Operation<List<Rent>>() {
            @Override
            public List<Rent> doOperation() {
                return rentRepository.selectPayedUnapproved();
            }
        });
    }

    @Override
    public List<RentFormBean> getReturnedRentFormBeanList() {
        List<Rent> returnedCarRents = selectReturnedCarRents();
        List<RentFormBean> returnedRentFormBeansList = new ArrayList<>();
        if (returnedCarRents.size() != 0) {
            for (Rent rent : returnedCarRents) {
                returnedRentFormBeansList.add(rentToRentFormBean(rent));
            }
        }
        return returnedRentFormBeansList;
    }

    @Override
    public List<ApplicationFormBean> getApplications() {
        return transactionManager.doInTransaction(new Operation<List<ApplicationFormBean>>() {
            @Override
            public List<ApplicationFormBean> doOperation() {
                return rentRepository.getApplications();
            }
        });
    }

}
