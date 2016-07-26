package by.serzh.beatsub.utils;

import by.serzh.beatsub.api.domain.SubsonicError;
import by.serzh.beatsub.api.domain.exceptions.*;

public class ExceptionUtils {

    private ExceptionUtils() { }

    public static void handle(SubsonicError error) throws SubsonicException {
        if(error.getCode() == 0) {
            throw new GenericSubsonicException(error.getMessage());
        } else if(error.getCode() == 10) {
            throw new RequiredParameterIsMissingException(error.getMessage());
        } else if(error.getCode() == 20) {
            throw new IncompatibleClientException(error.getMessage());
        } else if(error.getCode() == 30) {
            throw new IncompatibleServerException(error.getMessage());
        } else if(error.getCode() == 40) {
            throw new BadCredentialsException(error.getMessage());
        } else if(error.getCode() == 41) {
            throw new TokenAuthorizationNotAvailableException(error.getMessage());
        } else if(error.getCode() == 50) {
            throw new OperationForbiddenException(error.getMessage());
        } else if(error.getCode() == 60) {
            throw new LicenseInvalidException(error.getMessage());
        } else if(error.getCode() == 70) {
            throw new NotFoundException(error.getMessage());
        } else {
            throw new SubsonicException(error.getMessage());
        }
    }

}
