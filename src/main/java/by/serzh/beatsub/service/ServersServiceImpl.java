package by.serzh.beatsub.service;

import by.serzh.beatsub.domain.Server;
import by.serzh.beatsub.repository.ServerRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ServersServiceImpl implements ServersService {

    private ServerRepository serverRepository;

    @Inject
    public ServersServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;

        serverRepository.save(new Server("host", 90, "user", "password"));
        serverRepository.save(new Server("host2", 90, "user2", "password2"));
    }

    @Override
    public List<Server> findAll() {
        return new ArrayList<>(serverRepository.findAll());
    }
}
