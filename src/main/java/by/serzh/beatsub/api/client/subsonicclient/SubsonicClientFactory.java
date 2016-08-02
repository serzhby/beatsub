package by.serzh.beatsub.api.client.subsonicclient;

import by.serzh.beatsub.api.domain.Server;

public interface SubsonicClientFactory {

    SubsonicClient create();
    SubsonicClient create(Server server);
}
