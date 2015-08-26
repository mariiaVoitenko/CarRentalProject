package ua.nure.voitenkom.SummaryTask4.db.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.connection.IConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.holder.IConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.db.util.ConnectionManager;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.Connection;

public class TransactionManager implements ITransactionManager {

    private static final Logger logger = LoggerFactory.getLogger(TransactionManager.class);
    private final IConnectionFactory connectionFactory;
    private final IConnectionHolder connectionHolder;

    public TransactionManager(IConnectionFactory connectionFactory, IConnectionHolder connectionHolder) {
        this.connectionFactory = connectionFactory;
        this.connectionHolder = connectionHolder;
    }

    @Override
    public <T> T doInTransaction(Operation<T> operation) throws DatabaseException {
        Connection connection = getConnection();
        try {
            T result = operation.doOperation();
            connection.commit();
            return result;
        } catch (Exception e) {
            logger.warn("Couldn't commit", e);
            ConnectionManager.rollback(connection);
            throw new DatabaseException("Couldn't commit", e);
        } finally {
            closeConnection();
        }
    }

    private Connection getConnection() {
        Connection connection = connectionFactory.getConnection();
        connectionHolder.set(connection);
        return connection;
    }

    private void closeConnection() {
        ConnectionManager.closeConnection(connectionHolder.get());
        connectionHolder.remove();
    }

}
