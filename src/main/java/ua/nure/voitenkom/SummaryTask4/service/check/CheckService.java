package ua.nure.voitenkom.SummaryTask4.service.check;

import ua.nure.voitenkom.SummaryTask4.db.entity.Car;
import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.repository.check.ICheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;

public class CheckService implements ICheckService {

    private final ITransactionManager transactionManager;
    private final ICheckRepository checkRepository;

    public CheckService(ITransactionManager transactionManager, ICheckRepository checkRepository) {
        this.transactionManager = transactionManager;
        this.checkRepository = checkRepository;
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
    public void updateSum(final Check check, final int sum) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                checkRepository.updateSum(check, sum);
                return null;
            }
        });
    }

    @Override
    public int getSum(Car car, long days) {
        return car.getPrice() * (int) days;
    }

    @Override
    public int getSumWithDriver(Car car, long days) {
        return getSum(car, days) + 200 * (int) days;
    }

    @Override
    public int selectLastInsertedId() {
        return transactionManager.doInTransaction(new Operation<Integer>() {
            @Override
            public Integer doOperation() {
                return checkRepository.selectLastInsertedId();
            }
        });
    }

    @Override
    public Check selectById(final int id) {
        return transactionManager.doInTransaction(new Operation<Check>() {
            @Override
            public Check doOperation() {
                return checkRepository.selectById(id);
            }
        });
    }
}
