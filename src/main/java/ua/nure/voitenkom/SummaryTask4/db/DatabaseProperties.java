package ua.nure.voitenkom.SummaryTask4.db;

public final class DatabaseProperties {

    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/carRentDB?characterEncoding=UTF-8";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String MAXIMUM_POOL_SIZE = "100";
    public static final String MINIMUM_IDLE = "5";
    public static final String LEAK_DETECTION_THRESHOLD = "15000";
    public static final String CONNECTION_TEST_QUERY = "SELECT 1";
    public static final String CONNECTION_TIMEOUT = "1000";

    private DatabaseProperties() {

    }

}
