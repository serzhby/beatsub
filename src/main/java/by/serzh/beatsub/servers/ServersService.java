package by.serzh.beatsub.servers;

import by.serzh.beatsub.api.domain.Server;

import java.util.List;
import java.util.Observer;
import java.util.Optional;

public interface ServersService {
    List<Server> findAll();
    Optional<Server> getSelectedServer();

    Server addServer(Server server);

    void setSelectedServer(Server server);

    void addObserver(Observer observer);
    void deleteObserver(Observer observer);

    void delete(Server server);
}
