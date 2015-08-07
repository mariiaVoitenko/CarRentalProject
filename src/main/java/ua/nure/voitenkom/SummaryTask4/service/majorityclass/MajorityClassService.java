package ua.nure.voitenkom.SummaryTask4.service.majorityclass;

import ua.nure.voitenkom.SummaryTask4.db.repository.majorityclass.IMajorityClassRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.TransactionManager;

public class MajorityClassService implements IMajorityClassService {

    private final ITransactionManager transactionManager;
    private final IMajorityClassRepository majorityClassRepository;

    public MajorityClassService(ITransactionManager transactionManager, IMajorityClassRepository majorityClassRepository) {
        this.transactionManager = transactionManager;
        this.majorityClassRepository = majorityClassRepository;
    }

}
