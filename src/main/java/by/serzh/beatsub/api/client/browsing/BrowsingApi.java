package by.serzh.beatsub.api.client.browsing;

import by.serzh.beatsub.api.domain.index.Indexes;
import by.serzh.beatsub.api.domain.musicfolder.MusicFolders;
import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.api.domain.exceptions.SubsonicException;

import java.io.IOException;

public interface BrowsingApi {
    MusicFolders getMusicFolders(Server server) throws IOException, SubsonicException;

    Indexes getIndexes(Server server) throws IOException, SubsonicException;
}
