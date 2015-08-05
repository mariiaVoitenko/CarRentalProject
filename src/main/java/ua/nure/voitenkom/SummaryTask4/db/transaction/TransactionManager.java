package ua.nure.voitenkom.SummaryTask4.db.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.connection.ConnectionFactory;
import ua.nure.voitenkom.SummaryTask4.db.connection.ConnectionManager;
import ua.nure.voitenkom.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.Connection;

/**
 * Created by Maria on 05.08.2015.
 */
public class TransactionManager implements ITransactionManager {

    private static final Logger logger = LoggerFactory.getLogger(TransactionManager.class);
    private static ThreadLocal<Integer> currentTransaction = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    private final ConnectionFactory connectionFactory;
    private final ConnectionHolder connectionHolder;

    public TransactionManager(ConnectionFactory connectionFactory, ConnectionHolder holder) {
        this.connectionFactory = connectionFactory;
        connectionHolder = holder;
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
        Integer currTransaction = currentTransaction.get();
        Connection connection;
        if (isExternalTransaction(currTransaction)) {
            connection = connectionFactory.getConnection();
            connectionHolder.set(connection);
        } else {
            connection = connectionHolder.get();
        }
        currentTransaction.set(++currTransaction);
        return connection;
    }

    private boolean isExternalTransaction(Integer currTransaction) {
        return currTransaction == 0;
    }

    private void closeConnection() {
        Integer currTransaction = currentTransaction.get();
        currentTransaction.set(--currTransaction);
        if (isExternalTransaction(currTransaction)) {
            Connection connection = connectionHolder.get();
            ConnectionManager.closeConnection(connection);
            connectionHolder.remove();
        }
    }

}
