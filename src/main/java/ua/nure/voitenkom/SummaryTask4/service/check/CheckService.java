package ua.nure.voitenkom.SummaryTask4.service.check;

import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.repository.check.ICheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

import java.util.List;

/**
 * Created by Maria on 06.08.2015.
 */
public class CheckService implements ICheckService {

    private final ITransactionManager transactionManager;
    private final ICheckRepository checkRepository;

    public CheckService(ITransactionManager transactionManager, ICheckRepository checkRepository) {
        this.transactionManager = transactionManager;
        this.checkRepository = checkRepository;
    }

    @Override
    public List<Check> selectUnpayed() {
        return transactionManager.doInTransaction(new Operation<List<Check>>() {
            @Override
            public List<Check> doOperation() {
                return checkRepository.selectUnpayed();
            }
        });
    }

    @Override
    public void insert(final Check check) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                checkRepository.insert(check);
                return null;
            }
        });
    }

    @Override
    public void update(final Check check) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                checkRepository.update(check);
                return null;
            }
        });
    }

    @Override
    public void setPayed(final Check check) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                checkRepository.setPayed(check);
                return null;
            }
        });
    }

    @Override
    public void updateSum(final Check check, final int sum) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                checkRepository.updateSum(check, sum);
                return null;
            }
        });
    }
}
