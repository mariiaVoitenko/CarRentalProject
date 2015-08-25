package ua.nure.voitenkom.SummaryTask4.service.rent;

import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.ApplicationFormBean;
import ua.nure.voitenkom.SummaryTask4.formbean.RentFormBean;

import java.sql.Timestamp;
import java.util.List;

public interface IRentService {

    void insert(Rent rent);

    void update(Rent rent);

    List<Rent> selectAllForUser(int id);

    List<Rent> selectRentsForDates(Timestamp start, Timestamp end);

    List<RentFormBean> getUserRents(List<Rent> rentList);

    List<Rent> selectPayedUnapproved();

    Rent selectById(int id);

    void updateDecline(Rent rent);

    List<Rent> selectReturnedCarRents();

    void updateReturnedState(int rentId);

    void updateFinishedState(int rentId);

    void updateApprovedState(int rentId);

    RentFormBean rentToRentFormBean(Rent rent);

    List<RentFormBean> getReturnedRentFormBeanList();

    List<ApplicationFormBean> getApplications();
}
