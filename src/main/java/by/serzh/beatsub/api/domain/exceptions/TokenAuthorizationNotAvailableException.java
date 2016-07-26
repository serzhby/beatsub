package by.serzh.beatsub.api.domain.exceptions;

public class TokenAuthorizationNotAvailableException extends SubsonicException {

    public TokenAuthorizationNotAvailableException() {
    }

    public TokenAuthorizationNotAvailableException(String message) {
        super(message);
    }

    public TokenAuthorizationNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenAuthorizationNotAvailableException(Throwable cause) {
        super(cause);
    }

    public TokenAuthorizationNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
