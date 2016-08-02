package by.serzh.beatsub.api.client.license;

import by.serzh.beatsub.api.client.SubsonicPath;
import by.serzh.beatsub.api.client.subsonicclient.SubsonicClient;
import by.serzh.beatsub.api.client.subsonicclient.SubsonicClientFactory;
import by.serzh.beatsub.api.domain.License;
import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.api.domain.exceptions.SubsonicException;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class LicenseApiImpl implements LicenseApi {

    private SubsonicClientFactory clientFactory;

    @Inject
    public LicenseApiImpl(SubsonicClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public License getLicense(Server server) throws SubsonicException, IOException {
        Objects.requireNonNull(server);
        SubsonicClient subsonicClient = clientFactory.create(server);
        URI uri = subsonicClient.createURI(SubsonicPath.LICENSE, null);
        License license = subsonicClient.get(uri, License.class);
        return license;
    }
}
