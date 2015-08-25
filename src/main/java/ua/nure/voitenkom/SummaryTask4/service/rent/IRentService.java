package ua.nure.voitenkom.SummaryTask4.service.rent;

import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.ApplicationFormBean;
import ua.nure.voitenkom.SummaryTask4.formbean.HistoryFormBean;

import java.sql.Timestamp;
import java.util.List;

public interface IRentService {

    void insert(Rent rent);

    void update(Rent rent);

    List<Rent> selectRentsForDates(Timestamp start, Timestamp end);

    Rent selectById(int id);

    void updateDecline(Rent rent);

    void updateReturnedState(int rentId);

    void updateFinishedState(int rentId);

    void updateApprovedState(int rentId);

    List<ApplicationFormBean> getApplications();

    List<ApplicationFormBean> getReturned();

    List<HistoryFormBean> getUserRentsWithDeclines(int id);

    List<HistoryFormBean> getUserRentsWithoutDeclines(int id);

    List<HistoryFormBean> getUserRents(int id);

}
