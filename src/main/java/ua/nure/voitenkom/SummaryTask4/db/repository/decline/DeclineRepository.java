package ua.nure.voitenkom.SummaryTask4.db.repository.decline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.extractor.DeclineExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maria on 05.08.2015.
 */
public class DeclineRepository extends AbstractRepository<Decline> implements IDeclineRepository {

    private static final Logger logger = LoggerFactory.getLogger(DeclineRepository.class);

    public DeclineRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Decline findById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_DECLINE_BY_ID, new DeclineExtractor());
    }

    @Override
    public int findByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_DECLINE_BY_NAME, new DeclineExtractor());
    }

    @Override
    public List<Decline> selectAll(String sql, IExtractor<Decline> extractor) {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_DECLINES, extractor);
    }

    @Override
    public void deleteById(int id, String sql) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_DECLINE_BY_ID);
    }

    @Override
    public void update(Decline decline) {
        String sql = StatementsContainer.SQL_UPDATE_DECLINE_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, decline.getName());
            preparedStatement.setInt(2, decline.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void create(Decline decline) {
        String sql = StatementsContainer.SQL_INSERT_DECLINE;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, decline.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }
}
