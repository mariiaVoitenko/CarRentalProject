package ua.nure.voitenkom.SummaryTask4.service.rent;

import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.db.repository.rent.IRentRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import ua.nure.voitenkom.SummaryTask4.formbean.ApplicationFormBean;
import ua.nure.voitenkom.SummaryTask4.formbean.HistoryFormBean;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Mariia Voitenko
 */
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
    public List<Rent> selectRentsForDates(final Timestamp start, final Timestamp end) {
        return transactionManager.doInTransaction(new Operation<List<Rent>>() {
            @Override
            public List<Rent> doOperation() {
                return rentRepository.selectRentsForDates(start, end);
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
    public List<ApplicationFormBean> getApplications() {
        return transactionManager.doInTransaction(new Operation<List<ApplicationFormBean>>() {
            @Override
            public List<ApplicationFormBean> doOperation() {
                return rentRepository.getApplications();
            }
        });
    }

    @Override
    public List<ApplicationFormBean> getReturned() {
        return transactionManager.doInTransaction(new Operation<List<ApplicationFormBean>>() {
            @Override
            public List<ApplicationFormBean> doOperation() {
                return rentRepository.getReturned();
            }
        });
    }

    @Override
    public List<HistoryFormBean> getUserRentsWithDeclines(final int id) {
        return transactionManager.doInTransaction(new Operation<List<HistoryFormBean>>() {
            @Override
            public List<HistoryFormBean> doOperation() {
                return rentRepository.getUserRentsWithDeclines(id);
            }
        });
    }

    @Override
    public List<HistoryFormBean> getUserRentsWithoutDeclines(final int id) {
        return transactionManager.doInTransaction(new Operation<List<HistoryFormBean>>() {
            @Override
            public List<HistoryFormBean> doOperation() {
                return rentRepository.getUserRentsWithoutDeclines(id);
            }
        });
    }

    @Override
    public List<HistoryFormBean> getUserRents(int id) {
        List<HistoryFormBean> historyFormBeans = getUserRentsWithDeclines(id);
        historyFormBeans.addAll(getUserRentsWithoutDeclines(id));
        return historyFormBeans;
    }


}
