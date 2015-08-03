package ua.nure.voitenkom.SummaryTask4.db.holder;

import java.sql.Connection;

/**
 * Created by Maria on 30.07.2015.
 */
public class ConnectionHolder implements IConnectionHolder {

    private final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    @Override
    public Connection get() {
        return connectionHolder.get();
    }

    @Override
    public void set(Connection connection) {
        connectionHolder.set(connection);
    }

    @Override
    public void remove() {
        connectionHolder.remove();
    }
}
