package by.serzh.beatsub.service;

import by.serzh.beatsub.api.domain.Server;

import java.util.List;
import java.util.Observer;

public interface ServersService {
    List<Server> findAll();
    Server getSelectedServer();
    void setSelectedServer(Server server);

    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
}
