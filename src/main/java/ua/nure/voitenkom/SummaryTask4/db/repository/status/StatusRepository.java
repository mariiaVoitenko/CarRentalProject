package ua.nure.voitenkom.SummaryTask4.db.repository.status;

import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Status;
import ua.nure.voitenkom.SummaryTask4.db.extractor.StatusExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractSimpleEntityRepository;

import java.util.List;

public class StatusRepository extends AbstractSimpleEntityRepository<Status> implements IStatusRepository {

    public StatusRepository(IConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Status selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_STATUS_BY_ID, new StatusExtractor());
    }

    @Override
    public Status findByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_STATUS_BY_NAME, new StatusExtractor());
    }

    @Override
    public List<Status> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_STATUSES, new StatusExtractor());
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_STATUS_BY_ID);
    }

    @Override
    public void update(Status status) {
        super.update(status, StatementsContainer.SQL_UPDATE_STATUS_BY_ID);
    }

    @Override
    public void insert(Status status) {
        super.insert(status, StatementsContainer.SQL_INSERT_STATUS);
    }

}