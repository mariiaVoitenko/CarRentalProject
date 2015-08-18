package ua.nure.voitenkom.SummaryTask4.service.rent;

import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.RentFormBean;
import ua.nure.voitenkom.SummaryTask4.service.car.CarService;
import ua.nure.voitenkom.SummaryTask4.service.check.CheckService;
import ua.nure.voitenkom.SummaryTask4.service.decline.DeclineService;
import ua.nure.voitenkom.SummaryTask4.service.user.UserService;

import java.sql.Timestamp;
import java.util.List;

public interface IRentService {

    void insert(Rent rent);

    void update(Rent rent);

    List<Rent> selectAllForUser(int id);

    List<Rent> selectRentsForDates(Timestamp start, Timestamp end);

    List<RentFormBean> getUserRents(List<Rent> rentList);

    List<Rent> selectUnapproved();

    List<RentFormBean> getPayedUnapprovedRents(List<Rent> rentList);

    Rent selectById(int id);

    void updateDecline(Rent rent);

    List<Rent> selectReturnedCars() ;
}
