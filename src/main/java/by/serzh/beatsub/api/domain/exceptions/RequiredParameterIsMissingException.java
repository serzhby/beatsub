package by.serzh.beatsub.api.domain.exceptions;

public class RequiredParameterIsMissingException extends SubsonicException {

    public RequiredParameterIsMissingException() {
    }

    public RequiredParameterIsMissingException(String message) {
        super(message);
    }

    public RequiredParameterIsMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredParameterIsMissingException(Throwable cause) {
        super(cause);
    }

    public RequiredParameterIsMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
