package by.serzh.beatsub.repository;

import by.serzh.beatsub.api.domain.Server;

import java.util.Collection;

public interface ServerRepository {

    Collection<Server> findAll();

    Server find(Integer id);

    Server save(Server server);

    long delete(Server server);

    long delete(Integer id);
}
