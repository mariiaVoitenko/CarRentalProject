package ua.nure.voitenkom.SummaryTask4.db.repository.check;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.extractor.CheckExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CheckRepository extends AbstractRepository<Check> implements ICheckRepository {

    private static final Logger logger = LoggerFactory.getLogger(CheckRepository.class);

    public CheckRepository(IConnectionHolder connectionHolder) {
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
    public void updateSum(Check check, int sum) {
        String sql = StatementsContainer.SQL_UPDATE_CHECK_SUM_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, sum);
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

    @Override
    public int selectLastInsertedId() {
        String sql = StatementsContainer.SELECT_LAST_INSERTED_ID;
        int id = 0;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt(FieldsContainer.FIELD_LAST_INSERTED_ID);
                }
                return id;
            }
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

}
