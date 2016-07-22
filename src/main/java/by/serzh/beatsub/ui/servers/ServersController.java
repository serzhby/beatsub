package by.serzh.beatsub.ui.servers;

import by.serzh.beatsub.api.domain.License;
import by.serzh.beatsub.domain.Server;
import by.serzh.beatsub.service.ServersService;
import by.serzh.beatsub.ui.ControllerInjectorFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ServersController implements Initializable {

    private ResourceBundle resources;

    private ServersService serversService;

    @Inject
    public ServersController(ServersService serversService) {
        this.serversService = serversService;
    }

    @FXML
    private TableView<Server> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;

        ObservableList<TableColumn<Server, ?>> columns = tableView.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<>("host"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<>("user"));
        ((TableColumn<Server, String>) columns.get(2)).setCellValueFactory(param ->
            new SimpleStringProperty(param.getValue().getLicense().isValid()
                    ? resources.getString("common.yes")
                    : resources.getString("common.no"))
        );
        ((TableColumn<Server, String>) columns.get(3)).setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getLicense().getEmail())
        );
        ((TableColumn<Server, String>) columns.get(4)).setCellValueFactory(param ->
                new SimpleStringProperty(
                        Optional.ofNullable(param.getValue())
                                .map(Server::getLicense)
                                .map(License::getLicenseExpires)
                                .map(Object::toString).orElse(null)
                )
        );
        List<Server> servers = serversService.findAll();
        tableView.getItems().addAll(servers);
    }

    public void onAddClicked() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/add_server.fxml"), resources);
        loader.setControllerFactory(ControllerInjectorFactory.getInstance());
        Parent root = loader.load();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.setTitle(resources.getString("servers.add_server"));
        stage.show();
    }
}
