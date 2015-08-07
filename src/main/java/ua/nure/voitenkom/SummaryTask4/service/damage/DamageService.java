package ua.nure.voitenkom.SummaryTask4.service.damage;

import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.repository.damage.IDamageRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

public class DamageService implements IDamageService {

    private final ITransactionManager transactionManager;
    private final IDamageRepository damageRepository;

    public DamageService(ITransactionManager transactionManager, IDamageRepository damageRepository) {
        this.transactionManager = transactionManager;
        this.damageRepository = damageRepository;
    }

    @Override
    public int findByName(final String name) {
        return transactionManager.doInTransaction(new Operation<Integer>() {
            @Override
            public Integer doOperation() {
                return damageRepository.findByName(name);
            }
        });
    }

    @Override
    public void insert(final Damage damage) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                damageRepository.insert(damage);
                return null;
            }
        });
    }

    @Override
    public void update(final Damage damage) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                damageRepository.update(damage);
                return null;
            }
        });
    }

    @Override
    public void updateSum(final Damage damage) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                damageRepository.updateSum(damage);
                return null;
            }
        });
    }
}
