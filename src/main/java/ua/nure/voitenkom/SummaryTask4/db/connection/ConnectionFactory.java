package ua.nure.voitenkom.SummaryTask4.db.connection;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.DatabaseProperties;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory implements IConnectionFactory{

    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);
    private HikariDataSource dataSource = new HikariDataSource();

    public ConnectionFactory() {
        dataSource.setDriverClassName(DatabaseProperties.DRIVER);
        dataSource.setJdbcUrl(DatabaseProperties.URL);
        dataSource.setUsername(DatabaseProperties.USER_NAME);
        dataSource.setPassword(DatabaseProperties.PASSWORD);
        dataSource.setMaximumPoolSize(Integer.parseInt(DatabaseProperties.MAXIMUM_POOL_SIZE));
        dataSource.setMinimumIdle(Integer.parseInt(DatabaseProperties.MINIMUM_IDLE));
        dataSource.setLeakDetectionThreshold(Integer.parseInt(DatabaseProperties.LEAK_DETECTION_THRESHOLD));
        dataSource.setConnectionTestQuery(DatabaseProperties.CONNECTION_TEST_QUERY);
        dataSource.setConnectionTimeout(Integer.parseInt(DatabaseProperties.CONNECTION_TIMEOUT));
        dataSource.setAutoCommit(false);
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Connection failed", e);
            throw new IllegalStateException("Connection failed", e);
        }
    }
}
