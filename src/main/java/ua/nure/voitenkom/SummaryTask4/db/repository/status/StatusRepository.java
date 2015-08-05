package ua.nure.voitenkom.SummaryTask4.db.repository.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Status;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.StatusExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public Status findById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_STATUS_BY_ID, new StatusExtractor());
    }

    @Override
    public int findByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_STATUS_BY_NAME, new StatusExtractor());
    }

    @Override
    public List<Status> selectAll(String sql, IExtractor<Status> extractor) {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_STATUSES, extractor);
    }

    @Override
    public void deleteById(int id, String sql) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_STATUS_BY_ID);
    }

    @Override
    public void update(Status status) {
        String sql = StatementsContainer.SQL_UPDATE_STATUS_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, status.getName());
            preparedStatement.setInt(2, status.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void create(Status status) {
        String sql = StatementsContainer.SQL_INSERT_STATUS;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, status.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }
}