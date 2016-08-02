package by.serzh.beatsub.api.client.browsing;

import by.serzh.beatsub.api.client.SubsonicPath;
import by.serzh.beatsub.api.client.subsonicclient.SubsonicClient;
import by.serzh.beatsub.api.client.subsonicclient.SubsonicClientFactory;
import by.serzh.beatsub.api.domain.index.Indexes;
import by.serzh.beatsub.api.domain.musicfolder.MusicFolders;
import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.api.domain.exceptions.SubsonicException;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class BrowsingApiImpl implements BrowsingApi {

    private SubsonicClientFactory clientFactory;

    @Inject
    public BrowsingApiImpl(SubsonicClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public MusicFolders getMusicFolders(Server server) throws IOException, SubsonicException {
        Objects.requireNonNull(server);
        SubsonicClient subsonicClient = clientFactory.create(server);
        URI uri = subsonicClient.createURI(SubsonicPath.MUSIC_FOLDERS, null);
        MusicFolders result = subsonicClient.query(uri, MusicFolders.class);
        return result;
    }

    @Override
    public Indexes getIndexes(Server server) throws IOException, SubsonicException {
        Objects.requireNonNull(server);
        SubsonicClient subsonicClient = clientFactory.create(server);
        URI uri = subsonicClient.createURI(SubsonicPath.INDEXES, null);
        Indexes result = subsonicClient.query(uri, Indexes.class);
        return result;
    }

}
