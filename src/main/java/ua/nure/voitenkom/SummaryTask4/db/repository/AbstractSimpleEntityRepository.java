package ua.nure.voitenkom.SummaryTask4.db.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractSimpleEntityRepository<T extends SimpleEntity> extends AbstractRepository<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractSimpleEntityRepository.class);

    public AbstractSimpleEntityRepository(IConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    protected void insert(T entity, String sql) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    protected void update(T entity, String sql) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

}
