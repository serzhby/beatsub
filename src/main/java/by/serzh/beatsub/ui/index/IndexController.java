package by.serzh.beatsub.ui.index;

import by.serzh.beatsub.api.client.browsing.BrowsingApi;
import by.serzh.beatsub.api.domain.exceptions.SubsonicException;
import by.serzh.beatsub.api.domain.index.Indexes;
import by.serzh.beatsub.servers.ServersService;
import by.serzh.beatsub.service.IndexService;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    @FXML
    private ListView listView;

    private final IndexService indexService;
    private final ServersService serversService;

    @Inject
    public IndexController(IndexService indexService, ServersService serversService) {
        this.indexService = indexService;
        this.serversService = serversService;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serversService.getSelectedServer().ifPresent(server -> {
            try {
                Indexes indexes = indexService.getIndexes(server);
                System.out.println(indexes);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SubsonicException e) {
                e.printStackTrace();
            }
        });
//        browsingApi.getIndexes(serversService.getSelectedServer());
    }
}
