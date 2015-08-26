package ua.nure.voitenkom.SummaryTask4.db.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Mariia Voitenko
 */
public final class ConnectionManager {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

    private ConnectionManager() {
    }

    /**
     * Closes connection with database
     *
     * @param connection to be closed
     * @throws DatabaseConnectionException if connection is unable to be closed
     */
    public static void closeConnection(Connection connection) throws DatabaseConnectionException {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Couldn't close connection, exception: " + e.getMessage());
            throw new DatabaseConnectionException("Couldn't close connection", e);
        }
    }

    /**
     * Does rollback when some operation can't be performed
     *
     * @param connection to perform rollback
     * @throws DatabaseConnectionException if rollback can't be done
     */
    public static void rollback(Connection connection) throws DatabaseConnectionException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("Couldn't rollback, exception: " + e.getMessage());
            throw new DatabaseConnectionException("Couldn't rollback ", e);
        }
    }

}
