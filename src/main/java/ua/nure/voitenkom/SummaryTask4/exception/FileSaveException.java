package ua.nure.voitenkom.SummaryTask4.exception;

public class FileSaveException extends RuntimeException {

    public FileSaveException() {
    }

    public FileSaveException(String message) {
        super(message);
    }

    public FileSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSaveException(Throwable cause) {
        super(cause);
    }

    public FileSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
