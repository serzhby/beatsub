package by.serzh.beatsub.ui;

import by.serzh.beatsub.api.client.browsing.BrowsingApi;
import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.preferences.PrefsHolder;
import by.serzh.beatsub.servers.ServersService;
import by.serzh.beatsub.servers.ServersStage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController extends StageController implements Observer {

    @FXML
    private ScrollPane contentPane;

    @FXML
    private Menu changeServerSubmenu;

    private final ServersService serversService;

    @Inject
    public MainController(ServersService serversService,
                          PrefsHolder prefsHolder) {
        super(prefsHolder);
        this.serversService = serversService;
    }

    @FXML
    public void onCloseClicked() {
        Platform.exit();
    }

    @FXML
    public void onEditServersClicked(ActionEvent event) throws IOException {
        Stage stage = new ServersStage(this);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(getStage().getScene().getWindow());
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        serversService.addObserver(this);
    }

    public void onIndexClicked() throws IOException {
        changeMainScene("index");
    }

    public void onPlaylistsClicked() throws IOException {
        changeMainScene("playlists");
    }

    private void changeMainScene(String templateName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/" + templateName + ".fxml"), getResources());
        loader.setControllerFactory(ControllerInjectorFactory.getInstance());
        Parent root = loader.load();
        contentPane.setContent(root);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ServersService) {
            fillServersMenu();
        }
    }

    private void fillServersMenu() {
        List<Server> serverList = serversService.findAll();
        Optional<Server> selectedServer = serversService.getSelectedServer();

        changeServerSubmenu.getItems().clear();

        ToggleGroup group = new ToggleGroup();
        for (Server server : serverList) {
            RadioMenuItem item = new RadioMenuItem(server.getHost());
            item.setUserData(server);
            item.setToggleGroup(group);
            if(selectedServer.isPresent() && server.getId() == selectedServer.get().getId()) {
                group.selectToggle(item);
            }
            changeServerSubmenu.getItems().add(item);
        }

        group.selectedToggleProperty().addListener((ov, oldToggle, newToggle) -> {
            Toggle selectedToggle = group.getSelectedToggle();
            if(selectedToggle != null) {
                serversService.setSelectedServer((Server) selectedToggle.getUserData());
            }
        });
    }
}
