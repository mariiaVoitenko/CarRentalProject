package ua.nure.voitenkom.SummaryTask4.db.holder;

import java.sql.Connection;

/**
 * @author MariiaVoitenko
 */
public class ConnectionHolder implements IConnectionHolder {

    private final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    /**
     * Gets the connection with database that is thread safe to avoid situations with shared connection within two or
     * more different services providing to every thread it's own value of one field
     *
     * @return connection with database
     */
    @Override
    public Connection get() {
        return connectionHolder.get();
    }

    /**
     * Sets the connection with database
     *
     * @param connection with database
     */
    @Override
    public void set(Connection connection) {
        connectionHolder.set(connection);
    }

    /**
     * Removes the connection with database
     *
     * @return connection with database
     */
    @Override
    public void remove() {
        connectionHolder.remove();
    }

}
