package ua.nure.voitenkom.SummaryTask4.db.holder;

import java.sql.Connection;

public interface IConnectionHolder {

    Connection get();

    void set(Connection connection);

    void remove();

}
