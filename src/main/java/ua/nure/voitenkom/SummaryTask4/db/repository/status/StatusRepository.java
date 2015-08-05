package ua.nure.voitenkom.SummaryTask4.db.repository.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import ua.nure.voitenkom.SummaryTask4.db.entity.Status;
import ua.nure.voitenkom.SummaryTask4.db.extractor.StatusExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import java.util.List;

/**
 * Created by Maria on 05.08.2015.
 */
public class StatusRepository extends AbstractRepository<Status> implements IStatusRepository {

    private static final Logger logger = LoggerFactory.getLogger(StatusRepository.class);
    public StatusRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Status selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_STATUS_BY_ID, new StatusExtractor());
    }

    @Override
    public int findByName(String name) {
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
    public void update(SimpleEntity status) {
        super.update(status, StatementsContainer.SQL_UPDATE_STATUS_BY_ID);
    }

    @Override
    public void insert(SimpleEntity status) {
        super.insert(status, StatementsContainer.SQL_INSERT_STATUS);
    }
}