package by.serzh.beatsub.ui;

import by.serzh.beatsub.api.client.SubsonicClient;
import by.serzh.beatsub.api.client.SubsonicClientImpl;
import by.serzh.beatsub.api.domain.exceptions.SubsonicException;
import by.serzh.beatsub.domain.Server;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private ResourceBundle resources;

    @FXML
    private ScrollPane contentPane;

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

        SubsonicClient client = new SubsonicClientImpl(new Server("86.57.236.209", 80, "admin", "h3avensInsidec"));
        try {
            client.getLicense();
        } catch (SubsonicException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
