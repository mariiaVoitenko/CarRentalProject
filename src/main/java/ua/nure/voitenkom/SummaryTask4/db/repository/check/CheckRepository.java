package ua.nure.voitenkom.SummaryTask4.db.repository.check;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import ua.nure.voitenkom.SummaryTask4.db.extractor.CheckExtractor;
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
public class CheckRepository extends AbstractRepository<Check> implements ICheckRepository {

    private static final Logger logger = LoggerFactory.getLogger(CheckRepository.class);

    public CheckRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Check selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_CHECK_BY_ID, new CheckExtractor());
    }

    @Override
    public List<Check> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_CHECKS, new CheckExtractor());
    }

    @Override
    public List<Check> selectUnpayed() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_UNPAYED_CHECKS, new CheckExtractor());
    }

    @Override
    public void insert(SimpleEntity entity) {

    }

    @Override
    public void update(SimpleEntity entity) {

    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_CHECK_BY_ID);
    }

    @Override
    public void update(Check check) {
        String sql = StatementsContainer.SQL_UPDATE_CHECK_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, check.getSum());
            preparedStatement.setBoolean(2, check.isPayed());
            preparedStatement.setInt(3, check.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void setPayed(Check check) {
        String sql = StatementsContainer.SQL_UPDATE_CHECK_PAY_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, check.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void updateSum(Check check, int sum) {
        String sql = StatementsContainer.SQL_UPDATE_CHECK_SUM_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, check.getSum());
            preparedStatement.setInt(2, check.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void insert(Check check) {
        String sql = StatementsContainer.SQL_INSERT_CHECK;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, check.getSum());
            preparedStatement.setBoolean(2, check.isPayed());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }
}
