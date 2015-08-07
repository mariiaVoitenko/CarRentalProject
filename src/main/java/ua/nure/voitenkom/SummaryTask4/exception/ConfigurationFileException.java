package ua.nure.voitenkom.SummaryTask4.exception;

public class ConfigurationFileException extends RuntimeException{

    public ConfigurationFileException() {
    }

    public ConfigurationFileException(String message) {
        super(message);
    }

    public ConfigurationFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationFileException(Throwable cause) {
        super(cause);
    }

    public ConfigurationFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
