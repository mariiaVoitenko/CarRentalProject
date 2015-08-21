package ua.nure.voitenkom.SummaryTask4.db.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Entity;
import ua.nure.voitenkom.SummaryTask4.db.extractor.IExtractor;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<T extends Entity> implements IAbstractRepository<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractRepository.class);
    private final ConnectionHolder connectionHolder;

    public AbstractRepository(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    protected Connection getConnection() {
        return connectionHolder.get();
    }

    protected List<T> executeQuery(PreparedStatement preparedStatement, IExtractor<T> extractor) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T record = extractor.extract(resultSet);
                result.add(record);
            }
            return result;
        }
    }

    public T selectById(int id, String sql, IExtractor<T> extractor) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            List<T> records = executeQuery(preparedStatement, extractor);
            return records.isEmpty() ? null : records.get(0);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    public T selectByName(String name, String sql, IExtractor<T> extractor) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            List<T> records = executeQuery(preparedStatement, extractor);
            return records.isEmpty() ? null : records.get(0);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    public List<T> selectAll(String sql, IExtractor<T> extractor) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            return executeQuery(preparedStatement, extractor);
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

    public void deleteById(int id, String sql) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Fail while executing sql ['{}']; Message: ", sql, e);
            throw new DatabaseException("Fail while executing sql ['" + sql + "']");
        }
    }

}
