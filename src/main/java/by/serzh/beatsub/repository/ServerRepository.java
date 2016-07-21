package by.serzh.beatsub.repository;

import by.serzh.beatsub.domain.Server;

import java.util.Collection;

public interface ServerRepository {

    Collection<Server> findAll();
    Server save(Server server);

}
