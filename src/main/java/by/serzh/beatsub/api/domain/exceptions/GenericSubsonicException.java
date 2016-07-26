package by.serzh.beatsub.api.domain.exceptions;

public class GenericSubsonicException extends SubsonicException {

    public GenericSubsonicException() {
    }

    public GenericSubsonicException(String message) {
        super(message);
    }

    public GenericSubsonicException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericSubsonicException(Throwable cause) {
        super(cause);
    }

    public GenericSubsonicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
