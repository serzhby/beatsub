package by.serzh.beatsub.service;

import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.repository.ServerRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ServersServiceImpl extends Observable implements ServersService {

    private ServerRepository serverRepository;

    private Server selectedServer;

    @Inject
    public ServersServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;

//        Server server1 = new Server("86.57.236.209", 80, "admin", "password");
//        License license = new License(true, "myemail@email.com", Instant.now());
//        server1.setLicense(license);
//        serverRepository.save(server1);
////        serverRepository.save(new Server("host2", 90, "user2", "password2"));
//        selectedServer = server1;
//        serverRepository.delete(2);
    }

    @Override
    public List<Server> findAll() {
        return new ArrayList<>(serverRepository.findAll());
    }

    @Override
    public Server getSelectedServer() {
        return selectedServer;
    }

    @Override
    public Server addServer(Server server) {
        server = serverRepository.save(server);
        notifyObservers();
        return server;
    }

    @Override
    public void setSelectedServer(Server server) {
        this.selectedServer = server;
        notifyObservers();
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        o.update(this, null);
    }
}
