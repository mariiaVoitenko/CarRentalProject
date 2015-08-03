package ua.nure.voitenkom.SummaryTask4.db.holder;

import java.sql.Connection;

/**
 * Created by Maria on 30.07.2015.
 */
public interface IConnectionHolder {

    Connection get();

    void set(Connection connection);

    void remove();

}
