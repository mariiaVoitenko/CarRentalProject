package ua.nure.voitenkom.SummaryTask4.exception;

/**
 * Created by Maria on 30.07.2015.
 */
public class DatabaseConnectionException extends RuntimeException{

    public DatabaseConnectionException() {
    }

    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseConnectionException(Throwable cause) {
        super(cause);
    }

    public DatabaseConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}