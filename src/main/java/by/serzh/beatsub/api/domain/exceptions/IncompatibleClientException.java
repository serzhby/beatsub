package by.serzh.beatsub.api.domain.exceptions;

public class IncompatibleClientException extends SubsonicException {

    public IncompatibleClientException() {
    }

    public IncompatibleClientException(String message) {
        super(message);
    }

    public IncompatibleClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleClientException(Throwable cause) {
        super(cause);
    }

    public IncompatibleClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
