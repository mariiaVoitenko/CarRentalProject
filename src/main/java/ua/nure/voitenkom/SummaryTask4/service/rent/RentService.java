package ua.nure.voitenkom.SummaryTask4.service.rent;

import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;
import ua.nure.voitenkom.SummaryTask4.db.repository.rent.IRentRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.formbean.RentFormBean;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;
import ua.nure.voitenkom.SummaryTask4.service.check.CheckService;
import ua.nure.voitenkom.SummaryTask4.service.decline.DeclineService;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;

import static ua.nure.voitenkom.SummaryTask4.util.DateManager.timestampToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RentService implements IRentService {

    private final ITransactionManager transactionManager;
    private final IRentRepository rentRepository;

    public RentService(ITransactionManager transactionManager, IRentRepository rentRepository) {
        this.transactionManager = transactionManager;
        this.rentRepository = rentRepository;
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
    public List<RentFormBean> getUserRents(List<Rent> rentList, CarService carService, DeclineService declineService, CheckService checkService) {
        List<RentFormBean> rents = new ArrayList<>();
        for (Rent rent : rentList) {
            CarFormBean car = carService.getFullCarInformationById(rent.getCarId());
            Check check = checkService.selectById(rent.getCheckId());
            Decline decline = declineService.selectById(rent.getDeclineId());
            RentFormBean rentFormBean = new RentFormBean(rent.isDriven(), car, check,decline,
                    timestampToString(rent.getStartDate()), timestampToString(rent.getEndDate()));
            rents.add(rentFormBean);
        }
        return rents;
    }

    @Override
    public List<RentFormBean> getPayedUnapprovedRents(List<Rent> rentList, CarService carService, DeclineService declineService, CheckService checkService, UserService userService){
        List<RentFormBean> payedRents = new ArrayList<>();
        for (Rent rent : rentList) {
            Check check = checkService.selectById(rent.getCheckId());
            if(!check.isPayed()){
                continue;
            }
            CarFormBean car = carService.getFullCarInformationById(rent.getCarId());
            Decline decline = declineService.selectById(rent.getDeclineId());
            User user = userService.selectById(rent.getUserId());
            RentFormBean rentFormBean = new RentFormBean(rent.isDriven(), car, check,decline,
                    timestampToString(rent.getStartDate()), timestampToString(rent.getEndDate()), user, rent.getId());
            payedRents.add(rentFormBean);
        }
        return payedRents;
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
    public List<Rent> selectUnapproved() {
        return transactionManager.doInTransaction(new Operation<List<Rent>>() {
            @Override
            public List<Rent> doOperation() {
                return rentRepository.selectUnapproved();
            }
        });
    }
}
