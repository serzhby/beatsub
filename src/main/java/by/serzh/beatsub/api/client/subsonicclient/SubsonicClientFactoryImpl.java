package by.serzh.beatsub.api.client.subsonicclient;

import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.servers.ServersService;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

public class SubsonicClientFactoryImpl implements SubsonicClientFactory {

    private ServersService serversService;

    @Inject
    public SubsonicClientFactoryImpl(ServersService serversService) {
        this.serversService = serversService;
    }

    @Override
    public Optional<SubsonicClient> create() {
        return serversService.getSelectedServer().map(SubsonicClientImpl::new);
    }

    @Override
    public SubsonicClient create(Server server) {
        Objects.requireNonNull(server);
        return new SubsonicClientImpl(server);
    }
}
