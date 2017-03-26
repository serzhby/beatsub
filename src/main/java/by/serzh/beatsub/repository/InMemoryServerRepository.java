package by.serzh.beatsub.repository;

import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.servers.ServerRepository;

import java.util.*;

public class InMemoryServerRepository implements ServerRepository {

    private List<Server> servers = new ArrayList<>();

    @Override
    public Collection<Server> findAll() {
        return Collections.unmodifiableCollection(servers);
    }

    @Override
    public Server find(Integer id) {
        return null;
    }

    @Override
    public Server save(Server server) {
        Objects.requireNonNull(server);
        servers.add(server);
        return server;
    }

    @Override
    public long delete(Server server) {
        return 0;
    }

    @Override
    public long delete(Integer id) {
        return 0;
    }
}
