package ua.nure.voitenkom.SummaryTask4.service.status;

import ua.nure.voitenkom.SummaryTask4.db.entity.Status;
import ua.nure.voitenkom.SummaryTask4.db.repository.status.IStatusRepository;
import ua.nure.voitenkom.SummaryTask4.db.transaction.ITransactionManager;
import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;

import java.util.List;

/**
 * @author Mariia Voitenko
 */
public class StatusService implements IStatusService {

    private final ITransactionManager transactionManager;
    private final IStatusRepository statusRepository;

    public StatusService(ITransactionManager transactionManager, IStatusRepository statusRepository) {
        this.transactionManager = transactionManager;
        this.statusRepository = statusRepository;
    }

    @Override
    public List<Status> getAll() {
        return transactionManager.doInTransaction(new Operation<List<Status>>() {
            @Override
            public List<Status> doOperation() {
                return statusRepository.selectAll();
            }
        });
    }

    @Override
    public Status selectById(final int id) {
        return transactionManager.doInTransaction(new Operation<Status>() {
            @Override
            public Status doOperation() {
                return statusRepository.selectById(id);
            }
        });
    }

    @Override
    public Status selectByName(final String name) {
        return transactionManager.doInTransaction(new Operation<Status>() {
            @Override
            public Status doOperation() {
                return statusRepository.findByName(name);
            }
        });
    }
}
