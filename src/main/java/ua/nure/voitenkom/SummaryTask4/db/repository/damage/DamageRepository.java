package ua.nure.voitenkom.SummaryTask4.db.repository.damage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.FieldsContainer;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.extractor.DamageExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DamageRepository extends AbstractRepository<Damage> implements IDamageRepository {

    private static final Logger logger = LoggerFactory.getLogger(DamageRepository.class);

    public DamageRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Damage findByName(String name) {
        return super.selectByName(name, StatementsContainer.SQL_SELECT_DAMAGE_BY_NAME, new DamageExtractor());
    }

    @Override
    public List<Damage> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_DAMAGES, new DamageExtractor());
    }

    @Override
    public Damage selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_DAMAGE_BY_ID, new DamageExtractor());
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_DAMAGE_BY_ID);
    }

    @Override
    public void update(Damage damage) {
        String sql = StatementsContainer.SQL_UPDATE_DAMAGE_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, damage.getName());
            preparedStatement.setInt(2, damage.getSum());
            preparedStatement.setInt(3, damage.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void updateSum(Damage damage) {
        String sql = StatementsContainer.SQL_UPDATE_DAMAGE_SUM_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, damage.getSum());
            preparedStatement.setInt(2, damage.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public int selectSumById(int id) {
        String sql = StatementsContainer.SQL_SELECT_DAMAGE_SUM_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int sum = 0;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    sum = resultSet.getInt(FieldsContainer.FIELD_SUM);
                }
            }
            return sum;
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void insert(Damage damage) {
        String sql = StatementsContainer.SQL_INSERT_DAMAGE;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, damage.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

}
