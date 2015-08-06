package ua.nure.voitenkom.SummaryTask4.service.rent;

import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.db.repository.rent.IRentRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.user.IUserRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

import java.util.List;

/**
 * Created by Maria on 06.08.2015.
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
    public List<Rent> selectAllForUser(final int id) {
        return transactionManager.doInTransaction(new Operation<List<Rent>>() {
            @Override
            public List<Rent> doOperation() {
                return rentRepository.selectAllForUser(id);
            }
        });
    }
}
