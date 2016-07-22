package by.serzh.beatsub.service;

import by.serzh.beatsub.api.domain.License;
import by.serzh.beatsub.domain.Server;
import by.serzh.beatsub.repository.ServerRepository;

import javax.inject.Inject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ServersServiceImpl implements ServersService {

    private ServerRepository serverRepository;

    @Inject
    public ServersServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;

        Server server1 = new Server("86.57.236.209", 80, "admin", "password");
        License license = new License(true, "myemail@email.com", Instant.now());
        server1.setLicense(license);
        serverRepository.save(server1);
        serverRepository.save(new Server("host2", 90, "user2", "password2"));
    }

    @Override
    public List<Server> findAll() {
        return new ArrayList<>(serverRepository.findAll());
    }
}
