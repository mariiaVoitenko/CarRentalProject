package ua.nure.voitenkom.SummaryTask4.db.repository.rent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import ua.nure.voitenkom.SummaryTask4.db.extractor.BrandExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.extractor.RentExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.db.repository.brand.IBrandRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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
            preparedStatement.setInt(4, rent.getDeclineId());
            preparedStatement.setInt(5, rent.getCheckId());
            preparedStatement.setTimestamp(6, rent.getStartDate());
            preparedStatement.setTimestamp(7, rent.getEndDate());
            preparedStatement.setBoolean(8, rent.isReturned());
            preparedStatement.setInt(9, rent.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public List<Rent> selectAllForUser(int id) {
        String sql = StatementsContainer.SQL_SELECT_ALL_RENTS_FOR_USER;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return executeQuery(preparedStatement, new RentExtractor());
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
    public List<Rent> selectUnapproved() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_UNAPPROVED_RENTS, new RentExtractor());
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
        String sql = StatementsContainer.SQL_UPDATE_RETURNED_STATE_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, rentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public List<Rent> selectReturnedCars() {
        return super.selectAll(StatementsContainer.SQL_SELECT_RETURNED_CARS, new RentExtractor());
    }

    @Override
    public void insert(Rent rent) {
        String sql = StatementsContainer.SQL_INSERT_RENT;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setBoolean(1, rent.isDriven());
            preparedStatement.setInt(2, rent.getCarId());
            preparedStatement.setInt(3, rent.getUserId());
            preparedStatement.setInt(4, rent.getDeclineId());
            preparedStatement.setInt(5, rent.getCheckId());
            preparedStatement.setTimestamp(6, rent.getStartDate());
            preparedStatement.setTimestamp(7, rent.getEndDate());
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
    public void insert(SimpleEntity entity) {

    }

    @Override
    public void update(SimpleEntity entity) {

    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_RENT_BY_ID);
    }
}