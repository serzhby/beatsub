package by.serzh.beatsub.repository;

import by.serzh.beatsub.api.domain.Server;

import java.util.Collection;

public interface ServerRepository {

    Collection<Server> findAll();
    Server save(Server server);

}
