package by.serzh.beatsub.api.domain.exceptions;

public class SubsonicException extends Exception {

    public SubsonicException() {
    }

    public SubsonicException(String message) {
        super(message);
    }

    public SubsonicException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubsonicException(Throwable cause) {
        super(cause);
    }

    public SubsonicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
