package ua.nure.voitenkom.SummaryTask4.service.decline;

import ua.nure.voitenkom.SummaryTask4.db.repository.decline.IDeclineRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

/**
 * Created by Maria on 06.08.2015.
 */
public class DeclineService implements IDeclineService {

    private final ITransactionManager transactionManager;
    private final IDeclineRepository declineRepository;

    public DeclineService(ITransactionManager transactionManager, IDeclineRepository declineRepository) {
        this.transactionManager = transactionManager;
        this.declineRepository = declineRepository;
    }

}
