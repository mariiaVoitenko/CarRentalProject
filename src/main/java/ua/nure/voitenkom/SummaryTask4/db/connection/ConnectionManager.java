package ua.nure.voitenkom.SummaryTask4.db.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

    private ConnectionManager() {
    }

    public static void closeConnection(Connection connection) throws DatabaseConnectionException {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Couldn't close connection, exception: " + e.getMessage());
            throw new DatabaseConnectionException("Couldn't close connection", e);
        }
    }

    public static void rollback(Connection connection) throws DatabaseConnectionException {
        try {
            connection.setAutoCommit(false);
            connection.rollback();
        } catch (SQLException e) {
            logger.error("Couldn't rollback, exception: " + e.getMessage());
            throw new DatabaseConnectionException("Couldn't rollback ", e);
        }
    }
}
