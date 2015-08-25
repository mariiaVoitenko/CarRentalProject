package ua.nure.voitenkom.SummaryTask4.db.repository.rent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.db.extractor.ApplicationExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.HistoryExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.RentExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;
import ua.nure.voitenkom.SummaryTask4.formbean.ApplicationFormBean;
import ua.nure.voitenkom.SummaryTask4.formbean.HistoryFormBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RentRepository extends AbstractRepository<Rent> implements IRentRepository {

    private static final Logger logger = LoggerFactory.getLogger(RentRepository.class);

    public RentRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public void update(Rent rent) {
        String sql = StatementsContainer.SQL_UPDATE_RENT_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setBoolean(1, rent.isDriven());
            preparedStatement.setInt(2, rent.getCarId());
            preparedStatement.setInt(3, rent.getUserId());
            preparedStatement.setInt(4, rent.getCheckId());
            preparedStatement.setTimestamp(5, rent.getStartDate());
            preparedStatement.setTimestamp(6, rent.getEndDate());
            preparedStatement.setInt(7, rent.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public List<Rent> selectRentsForDates(Timestamp start, Timestamp end) {
        String sql = StatementsContainer.SQL_SELECT_RENT_BY_DATE;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, start);
            preparedStatement.setTimestamp(2, end);
            preparedStatement.setTimestamp(3, start);
            preparedStatement.setTimestamp(4, end);
            return executeQuery(preparedStatement, new RentExtractor());
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void updateDecline(Rent rent) {
        String sql = StatementsContainer.SQL_UPDATE_RENT_DECLINE_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, rent.getDeclineId());
            preparedStatement.setInt(2, rent.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void updateReturnedState(int rentId) {
        updateState(rentId, StatementsContainer.SQL_UPDATE_RETURNED_STATE_BY_ID);
    }

    @Override
    public void updateFinishedState(int rentId) {
        updateState(rentId, StatementsContainer.SQL_UPDATE_FINISHED_STATE_BY_ID);
    }

    @Override
    public List<ApplicationFormBean> getApplications() {
        String sql = StatementsContainer.SQL_SELECT_APPLICATIONS;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            return extractApplications(preparedStatement);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public List<ApplicationFormBean> getReturned() {
        String sql = StatementsContainer.SQL_SELECT_RETURNED_CARS;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            return extractApplications(preparedStatement);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public List<HistoryFormBean> getUserRentsWithDeclines(int id) {
        return getUserHistory(StatementsContainer.SQL_SELECT_USER_RENTS_WITH_DECLINES, id);
    }

    @Override
    public List<HistoryFormBean> getUserRentsWithoutDeclines(int id) {
        return getUserHistory(StatementsContainer.SQL_SELECT_USER_RENTS_WITHOUT_DECLINES, id);
    }

    @Override
    public void updateApprovedState(int rentId) {
        updateState(rentId, StatementsContainer.SQL_UPDATE_APPROVED_STATE_BY_ID);
    }

    @Override
    public void insert(Rent rent) {
        String sql = StatementsContainer.SQL_INSERT_RENT;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setBoolean(1, rent.isDriven());
            preparedStatement.setInt(2, rent.getCarId());
            preparedStatement.setInt(3, rent.getUserId());
            preparedStatement.setInt(4, rent.getCheckId());
            preparedStatement.setTimestamp(5, rent.getStartDate());
            preparedStatement.setTimestamp(6, rent.getEndDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public Rent selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_RENT_BY_ID, new RentExtractor());
    }

    @Override
    public List<Rent> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_RENTS, new RentExtractor());
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_RENT_BY_ID);
    }

    private void updateState(int rentId, String sql) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, rentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    private List<ApplicationFormBean> extractApplications(PreparedStatement preparedStatement) throws SQLException {
        ApplicationExtractor extractor = new ApplicationExtractor();
        List<ApplicationFormBean> applicationFormBeans = new ArrayList<>();
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                applicationFormBeans.add(extractor.extract(resultSet));
            }
        }
        return applicationFormBeans;
    }

    private List<HistoryFormBean> extractHistory(PreparedStatement preparedStatement) throws SQLException {
        HistoryExtractor extractor = new HistoryExtractor();
        List<HistoryFormBean> historyFormBeans = new ArrayList<>();
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                historyFormBeans.add(extractor.extract(resultSet));
            }
        }
        return historyFormBeans;
    }

    private List<HistoryFormBean> getUserHistory(String sql, int id){
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return extractHistory(preparedStatement);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

}