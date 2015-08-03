package ua.nure.voitenkom.SummaryTask4.db.connection;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static ua.nure.voitenkom.SummaryTask4.db.connection.ConnectionConfigurator.getConfiguration;

/**
 * Created by Maria on 30.07.2015.
 */
public class ConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);
    private HikariDataSource dataSource;

    public ConnectionFactory() {
        dataSource.setDriverClassName(getConfiguration("driver"));
        dataSource.setJdbcUrl(getConfiguration("url"));
        dataSource.setUsername(getConfiguration("userName"));
        dataSource.setPassword(getConfiguration("password"));
        dataSource.setMaximumPoolSize(Integer.parseInt(getConfiguration("maximumPoolSize")));
        dataSource.setMinimumIdle(Integer.parseInt(getConfiguration("minimumIdle")));
        dataSource.setLeakDetectionThreshold(Integer.parseInt(getConfiguration("leakDetectionThreshold")));
        dataSource.setConnectionTestQuery(getConfiguration("connectionTestQuery"));
        dataSource.setConnectionTimeout(Integer.parseInt(getConfiguration("connectionTimeout")));
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
