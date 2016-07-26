package by.serzh.beatsub.api.domain.exceptions;

public class LicenseInvalidException extends SubsonicException {

    public LicenseInvalidException() {
    }

    public LicenseInvalidException(String message) {
        super(message);
    }

    public LicenseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public LicenseInvalidException(Throwable cause) {
        super(cause);
    }

    public LicenseInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
