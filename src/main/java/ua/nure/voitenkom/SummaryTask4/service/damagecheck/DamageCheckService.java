package ua.nure.voitenkom.SummaryTask4.service.damagecheck;

import ua.nure.voitenkom.SummaryTask4.db.entity.DamageCheck;
import ua.nure.voitenkom.SummaryTask4.db.repository.damagecheck.IDamageCheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;

/**
 * @author Mariia Voitenko
 */
public class DamageCheckService implements IDamageCheckService {

    private final ITransactionManager transactionManager;
    private final IDamageCheckRepository damageCheckRepository;

    public DamageCheckService(ITransactionManager transactionManager, IDamageCheckRepository damageCheckRepository) {
        this.transactionManager = transactionManager;
        this.damageCheckRepository = damageCheckRepository;
    }

    @Override
    public void insert(final DamageCheck damageCheck) {
        transactionManager.doInTransaction(new Operation<Void>() {
            @Override
            public Void doOperation() {
                damageCheckRepository.insert(damageCheck);
                return null;
            }
        });
    }

}
