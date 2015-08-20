package ua.nure.voitenkom.SummaryTask4.service.majorityclass;

import ua.nure.voitenkom.SummaryTask4.db.entity.MajorityClass;
import ua.nure.voitenkom.SummaryTask4.db.repository.majorityclass.IMajorityClassRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import java.util.List;

public class MajorityClassService implements IMajorityClassService {

    private final ITransactionManager transactionManager;
    private final IMajorityClassRepository majorityClassRepository;

    public MajorityClassService(ITransactionManager transactionManager, IMajorityClassRepository majorityClassRepository) {
        this.transactionManager = transactionManager;
        this.majorityClassRepository = majorityClassRepository;
    }

    @Override
    public List<MajorityClass> getAll() {
        return transactionManager.doInTransaction(new Operation<List<MajorityClass>>() {
            @Override
            public List<MajorityClass> doOperation() {
                return majorityClassRepository.selectAll();
            }
        });
    }

    @Override
    public MajorityClass selectById(final int id) {
        return transactionManager.doInTransaction(new Operation<MajorityClass>() {
            @Override
            public MajorityClass doOperation() {
                return majorityClassRepository.selectById(id);
            }
        });
    }

    @Override
    public MajorityClass selectByName(final String name) {
        return transactionManager.doInTransaction(new Operation<MajorityClass>() {
            @Override
            public MajorityClass doOperation() {
                return majorityClassRepository.findByName(name);
            }
        });
    }

}
