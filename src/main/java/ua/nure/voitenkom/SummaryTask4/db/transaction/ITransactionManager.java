package ua.nure.voitenkom.SummaryTask4.db.transaction;

import ua.nure.voitenkom.SummaryTask4.exception.DatabaseConnectionException;

public interface ITransactionManager {

    <T> T doInTransaction(Operation<T> operation) throws DatabaseConnectionException;

}
