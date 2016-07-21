package by.serzh.beatsub.repository;

import by.serzh.beatsub.domain.Server;

import java.util.*;

public class InMemoryServerRepository implements ServerRepository {

    private List<Server> servers = new ArrayList<>();

    @Override
    public Collection<Server> findAll() {
        return Collections.unmodifiableCollection(servers);
    }

    @Override
    public Server save(Server server) {
        Objects.requireNonNull(server);
        servers.add(server);
        return server;
    }
}
