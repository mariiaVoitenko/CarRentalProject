package ua.nure.voitenkom.SummaryTask4.service.decline;

import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.repository.decline.IDeclineRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;

import java.util.List;

/**
 * @author Mariia Voitenko
 */
public class DeclineService implements IDeclineService {

    private final ITransactionManager transactionManager;
    private final IDeclineRepository declineRepository;

    public DeclineService(ITransactionManager transactionManager, IDeclineRepository declineRepository) {
        this.transactionManager = transactionManager;
        this.declineRepository = declineRepository;
    }

    @Override
    public Decline selectById(final int id) {
        return transactionManager.doInTransaction(new Operation<Decline>() {
            @Override
            public Decline doOperation() {
                return declineRepository.selectById(id);
            }
        });
    }

    @Override
    public List<Decline> getAll() {
        return transactionManager.doInTransaction(new Operation<List<Decline>>() {
            @Override
            public List<Decline> doOperation() {
                return declineRepository.selectAll();
            }
        });
    }

    @Override
    public Decline selectByName(final String name) {
        return transactionManager.doInTransaction(new Operation<Decline>() {
            @Override
            public Decline doOperation() {
                return declineRepository.findByName(name);
            }
        });
    }

}
