package ua.nure.voitenkom.SummaryTask4.db.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.exception.ConfigurationFileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Maria on 30.07.2015.
 */
public class ConnectionConfigurator {
    private static final String DB_CONFIGURATION_PROPERTIES_FILE = "/dbConfiguration.properties";
    private static final Logger logger = LoggerFactory.getLogger(ConnectionConfigurator.class);
    private static final Properties properties = new Properties();

    static {
        try (InputStream resource = ConnectionConfigurator.class.getResourceAsStream(DB_CONFIGURATION_PROPERTIES_FILE)) {
            properties.load(resource);
        } catch (IOException e) {
            logger.error("Can't load configuration file: '{}'", DB_CONFIGURATION_PROPERTIES_FILE);
            throw new ConfigurationFileException("Can't load configuration file: '" + DB_CONFIGURATION_PROPERTIES_FILE + "'");
        }
    }

    private ConnectionConfigurator() {
    }

    public static String getConfiguration(String key) {
        return properties.getProperty(key);
    }
}
