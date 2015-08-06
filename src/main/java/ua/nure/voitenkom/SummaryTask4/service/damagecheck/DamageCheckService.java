package ua.nure.voitenkom.SummaryTask4.service.damagecheck;

import ua.nure.voitenkom.SummaryTask4.db.repository.damagecheck.IDamageCheckRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

/**
 * Created by Maria on 06.08.2015.
 */
public class DamageCheckService implements IDamageCheckService {

    private final ITransactionManager transactionManager;
    private final IDamageCheckRepository damageCheckRepository;

    public DamageCheckService(ITransactionManager transactionManager, IDamageCheckRepository damageCheckRepository) {
        this.transactionManager = transactionManager;
        this.damageCheckRepository = damageCheckRepository;
    }

}
