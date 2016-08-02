package by.serzh.beatsub.api.client.license;

import by.serzh.beatsub.api.domain.License;
import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.api.domain.exceptions.SubsonicException;

import java.io.IOException;

public interface LicenseApi {
    License getLicense(Server server) throws SubsonicException, IOException;
}
