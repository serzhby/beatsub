package by.serzh.beatsub.api.client.subsonicclient;

import by.serzh.beatsub.api.domain.Server;

import java.util.Optional;

public interface SubsonicClientFactory {

    Optional<SubsonicClient> create();
    SubsonicClient create(Server server);
}
