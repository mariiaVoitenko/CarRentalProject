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
import java.util.List;

/**
 * Created by Maria on 05.08.2015.
 */
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
            preparedStatement.setDate(6, rent.getStartDate());
            preparedStatement.setDate(7, rent.getEndDate());
            preparedStatement.setInt(8, rent.getId());
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
            return executeQuery(preparedStatement, new RentExtractor());
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
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
            preparedStatement.setDate(6, rent.getStartDate());
            preparedStatement.setDate(7, rent.getEndDate());
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