package ua.nure.voitenkom.SummaryTask4.db.repository;

import ua.nure.voitenkom.SummaryTask4.db.transaction.Operation;
import ua.nure.voitenkom.SummaryTask4.db.util.ConnectionManager;
import ua.nure.voitenkom.SummaryTask4.exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Maria on 24.08.2015.
 */
public class Test {

//    public <T> T doInTransaction(UnitOfWork<T> operation) throws DatabaseException {
//        Connection connection = getConnection();
//        try {
//            T result = operation.doOperation(connection);
//            connection.commit();
//            return result;
//        } catch (Exception e) {
//            ConnectionManager.rollback(connection);
//            throw new DatabaseException("Couldn't commit", e);
//        } finally {
//            closeConnection();
//        }
//    }
//
//
//    private interface UnitOfWork<T> {
//        T doOperation(Connection connection) throws SQLException;
//    }
//
//    private class MyRepo{
//        public void findById(int id, Connection connection) throws SQLException {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ?");
//            preparedStatement.setInt(1, 2);
//            preparedStatement.execute();
//        }
//
//        public void insert(int id, Connection connection) throws SQLException {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ?");
//            preparedStatement.setInt(1,2);
//            preparedStatement.execute();
//        }
//    }
//
//
//
//
//    private class Service {
//        Test test = new Test();
//        MyRepo repo = new MyRepo();
//        public void doSmth(){
//            test.doInTransaction(new UnitOfWork<Void>() {
//                @Override
//                public Void doOperation(Connection connection) throws SQLException {
//                    repo.findById(10, connection);
//                    repo.insert(50, connection);
//
//                    return null;
//                }
//            });
//        }
//    }


}
