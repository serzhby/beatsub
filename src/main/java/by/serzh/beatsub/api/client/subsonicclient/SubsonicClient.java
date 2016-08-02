package by.serzh.beatsub.api.client.subsonicclient;

import by.serzh.beatsub.api.domain.exceptions.SubsonicException;

import java.io.IOException;
import java.net.URI;

public interface SubsonicClient {
    URI createURI(String methodPath, Object paramsObject);

    <T> T get(URI uri, Class<T> cl) throws IOException, SubsonicException;
}
