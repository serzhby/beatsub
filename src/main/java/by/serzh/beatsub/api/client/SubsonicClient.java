package by.serzh.beatsub.api.client;

import by.serzh.beatsub.api.domain.License;
import by.serzh.beatsub.api.domain.exceptions.SubsonicException;

import java.io.IOException;

public interface SubsonicClient {
    License getLicense() throws SubsonicException, IOException;
}
