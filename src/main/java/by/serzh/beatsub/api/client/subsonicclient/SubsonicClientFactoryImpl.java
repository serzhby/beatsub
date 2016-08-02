package by.serzh.beatsub.api.client.subsonicclient;

import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.service.ServersService;

import javax.inject.Inject;
import java.util.Objects;

public class SubsonicClientFactoryImpl implements SubsonicClientFactory {

    private ServersService serversService;

    @Inject
    public SubsonicClientFactoryImpl(ServersService serversService) {
        this.serversService = serversService;
    }

    @Override
    public SubsonicClient create() {
        return serversService.getSelectedServer() == null ? null : new SubsonicClientImpl(serversService.getSelectedServer());
    }

    @Override
    public SubsonicClient create(Server server) {
        Objects.requireNonNull(server);
        return new SubsonicClientImpl(server);
    }
}
