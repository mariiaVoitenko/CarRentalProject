package ua.nure.voitenkom.SummaryTask4.db.transaction;

import ua.nure.voitenkom.SummaryTask4.exception.DatabaseConnectionException;

/**
 * Created by Maria on 05.08.2015.
 */
public interface ITransactionManager {

    <T> T doInTransaction(Operation<T> operation) throws DatabaseConnectionException;

}
