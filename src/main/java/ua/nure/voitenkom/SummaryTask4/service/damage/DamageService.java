package ua.nure.voitenkom.SummaryTask4.service.damage;

import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.repository.damage.IDamageRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;

import java.util.List;

public class DamageService implements IDamageService {

    private final ITransactionManager transactionManager;
    private final IDamageRepository damageRepository;

    public DamageService(ITransactionManager transactionManager, IDamageRepository damageRepository) {
        this.transactionManager = transactionManager;
        this.damageRepository = damageRepository;
    }

    @Override
    public Damage findByName(final String name) {
        return transactionManager.doInTransaction(new Operation<Damage>() {
            @Override
            public Damage doOperation() {
                return damageRepository.findByName(name);
            }
        });
    }

    @Override
    public Damage findById(final int id) {
        return transactionManager.doInTransaction(new Operation<Damage>() {
            @Override
            public Damage doOperation() {
                return damageRepository.selectById(id);
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
    public List<Damage> getAll() {
        return transactionManager.doInTransaction(new Operation<List<Damage>>() {
            @Override
            public List<Damage> doOperation() {
                return damageRepository.selectAll();
            }
        });
    }

    @Override
    public int selectSumById(final int id) {
        return transactionManager.doInTransaction(new Operation<Integer>() {
            @Override
            public Integer doOperation() {
                return damageRepository.selectSumById(id);
            }
        });
    }
}
