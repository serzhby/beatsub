package by.serzh.beatsub.service;

import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.repository.ServerRepository;
import by.serzh.beatsub.ui.prefs.PrefsStorage;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ServersServiceImpl extends Observable implements ServersService {

    private final PrefsStorage prefsStorage;
    private final ServerRepository serverRepository;

    @Inject
    public ServersServiceImpl(ServerRepository serverRepository, PrefsStorage prefsStorage) {
        this.serverRepository = serverRepository;
        this.prefsStorage = prefsStorage;
    }

    @Override
    public List<Server> findAll() {
        return new ArrayList<>(serverRepository.findAll());
    }

    @Override
    public Server getSelectedServer() {
        Integer id = prefsStorage.getSelectedServerId();
        return id != null ? serverRepository.find(id) : null;
    }

    @Override
    public Server addServer(Server server) {
        server = serverRepository.save(server);
        if(prefsStorage.getSelectedServerId() == null) {
            setSelectedServer(server);
        }
        setChanged();
        notifyObservers();
        return server;
    }

    @Override
    public void setSelectedServer(Server server) {
        prefsStorage.setSelectedServerId(server.getId());
        setChanged();
        notifyObservers();
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        o.update(this, null);
    }

    @Override
    public void delete(Server server) {
        serverRepository.delete(server);
        setChanged();
        notifyObservers();
    }
}
