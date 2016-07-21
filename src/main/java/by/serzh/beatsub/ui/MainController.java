package by.serzh.beatsub.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private ResourceBundle resources;

    @FXML
    public void onCloseClicked() {
        Platform.exit();
    }

    @FXML
    public void onEditServersClicked() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/servers.fxml"), resources);
        loader.setControllerFactory(ControllerInjectorFactory.getInstance());
        Parent root = loader.load();
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle(resources.getString("servers.servers"));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
    }
}
