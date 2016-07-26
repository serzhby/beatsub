package by.serzh.beatsub.api.domain.exceptions;

public class IncompatibleServerException extends SubsonicException {

    public IncompatibleServerException() {
    }

    public IncompatibleServerException(String message) {
        super(message);
    }

    public IncompatibleServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleServerException(Throwable cause) {
        super(cause);
    }

    public IncompatibleServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
