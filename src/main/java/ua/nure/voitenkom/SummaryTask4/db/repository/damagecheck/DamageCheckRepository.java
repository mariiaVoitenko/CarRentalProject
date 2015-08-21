package ua.nure.voitenkom.SummaryTask4.db.repository.damagecheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;
import ua.nure.voitenkom.SummaryTask4.db.entity.DamageCheck;
import ua.nure.voitenkom.SummaryTask4.db.extractor.DamageCheckExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.repository.AbstractRepository;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DamageCheckRepository extends AbstractRepository<DamageCheck> implements IDamageCheckRepository {

    private static final Logger logger = LoggerFactory.getLogger(DamageCheckRepository.class);

    public DamageCheckRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public void update(DamageCheck damageCheck) {
        String sql = StatementsContainer.SQL_UPDATE_DAMAGECHECK_BY_ID;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, damageCheck.getDamageId());
            preparedStatement.setInt(2, damageCheck.getCheckId());
            preparedStatement.setInt(3, damageCheck.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public void insert(DamageCheck damageCheck) {
        String sql = StatementsContainer.SQL_INSERT_DAMAGECHECK;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, damageCheck.getDamageId());
            preparedStatement.setInt(2, damageCheck.getCheckId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    @Override
    public DamageCheck selectById(int id) {
        return super.selectById(id, StatementsContainer.SQL_SELECT_DAMAGECHECK_BY_ID, new DamageCheckExtractor());
    }

    @Override
    public List<DamageCheck> selectAll() {
        return super.selectAll(StatementsContainer.SQL_SELECT_ALL_DAMAGECHECKS, new DamageCheckExtractor());
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id, StatementsContainer.SQL_DELETE_DAMAGECHECK_BY_ID);
    }

}
