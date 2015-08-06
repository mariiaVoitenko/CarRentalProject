package ua.nure.voitenkom.SummaryTask4.service.status;

import ua.nure.voitenkom.SummaryTask4.db.repository.status.IStatusRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

/**
 * Created by Maria on 06.08.2015.
 */
public class StatusService implements IStatusService {

    private final ITransactionManager transactionManager;
    private final IStatusRepository statusRepository;

    public StatusService(ITransactionManager transactionManager, IStatusRepository statusRepository) {
        this.transactionManager = transactionManager;
        this.statusRepository = statusRepository;
    }

}
