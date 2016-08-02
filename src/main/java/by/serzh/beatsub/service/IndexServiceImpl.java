package by.serzh.beatsub.service;

import by.serzh.beatsub.api.client.browsing.BrowsingApi;
import by.serzh.beatsub.api.domain.exceptions.SubsonicException;
import by.serzh.beatsub.api.domain.index.Indexes;

import javax.inject.Inject;
import java.io.IOException;

public class IndexServiceImpl implements IndexService {

    private BrowsingApi browsingApi;
    private ServersService serversService;

    @Inject
    public IndexServiceImpl(BrowsingApi browsingApi, ServersService serversService) {
        this.browsingApi = browsingApi;
        this.serversService = serversService;
    }

    @Override
    public Indexes getIndexes() throws IOException, SubsonicException {
        return browsingApi.getIndexes(serversService.getSelectedServer());
    }
}
