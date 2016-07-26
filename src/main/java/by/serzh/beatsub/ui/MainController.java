package by.serzh.beatsub.ui;

import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.service.ServersService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MainController implements Initializable, Observer {
    private ResourceBundle resources;

    @FXML
    private ScrollPane contentPane;

    @FXML
    private Menu changeServerSubmenu;

    private ServersService serversService;

    @Inject
    public MainController(ServersService serversService) {
        this.serversService = serversService;
    }

    @FXML
    public void onCloseClicked() {
        Platform.exit();
    }

    @FXML
    public void onEditServersClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/servers.fxml"), resources);
        loader.setControllerFactory(ControllerInjectorFactory.getInstance());
        Parent root = loader.load();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(contentPane.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.setTitle(resources.getString("servers.servers"));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        serversService.addObserver(this);
//        contentPane.getScene().getWindow().setOnCloseRequest((event) -> serversService.deleteObserver(this));
    }

    public void onIndexClicked() throws IOException {
        changeMainScene("index");
    }

    public void onPlaylistsClicked() throws IOException {
        changeMainScene("playlists");
    }

    private void changeMainScene(String templateName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/" + templateName + ".fxml"), resources);
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
        Server selectedServer = serversService.getSelectedServer();

        changeServerSubmenu.getItems().clear();

        ToggleGroup group = new ToggleGroup();
        for (Server server : serverList) {
            RadioMenuItem item = new RadioMenuItem(server.getHost());
            item.setUserData(server);
            item.setToggleGroup(group);
            if(server.equals(selectedServer)) {
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
