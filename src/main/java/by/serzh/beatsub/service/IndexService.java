package by.serzh.beatsub.service;

import by.serzh.beatsub.api.domain.exceptions.SubsonicException;
import by.serzh.beatsub.api.domain.index.Indexes;

import java.io.IOException;

public interface IndexService {
    Indexes getIndexes() throws IOException, SubsonicException;
}
