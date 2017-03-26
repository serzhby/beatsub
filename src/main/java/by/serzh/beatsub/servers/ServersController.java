package by.serzh.beatsub.servers;

import by.serzh.beatsub.api.domain.License;
import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.preferences.PrefsHolder;
import by.serzh.beatsub.ui.StageController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ServersController extends StageController implements Observer {

    private final ServersService serversService;

    @Inject
    public ServersController(ServersService serversService, PrefsHolder prefsHolder) {
        super(prefsHolder);
        this.serversService = serversService;
    }

    @FXML
    private TableView<Server> tableView;

    @FXML
    private Button deleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        initializeTableView();
        serversService.addObserver(this);

        disableDeleteIfNecessary();
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> disableDeleteIfNecessary()
        );
    }

    private void disableDeleteIfNecessary() {
        deleteButton.setDisable(tableView.getSelectionModel().getSelectedItem() == null);
    }

    private void initializeTableView() {
        ObservableList<TableColumn<Server, ?>> columns = tableView.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<>("host"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<>("username"));
        ((TableColumn<Server, String>) columns.get(2)).setCellValueFactory(param ->
                new SimpleStringProperty(param.getValue().getLicense().isValid()
                        ? getResources().getString("common.yes")
                        : getResources().getString("common.no"))
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
        ((TableColumn<Server, String>) columns.get(5)).setCellValueFactory(param ->
                new SimpleStringProperty(
                        Optional.ofNullable(param.getValue())
                                .map(Server::getLicense)
                                .map(License::getTrialExpires)
                                .map(Object::toString).orElse(null)
                )
        );
    }

    public void onAddClicked() throws IOException {
        Stage stage = new AddServerStage(this);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(getStage().getScene().getWindow());
        stage.show();
    }

    public void onDeleteClicked() {
        Server server = tableView.getSelectionModel().getSelectedItem();
        if(server != null) {
            serversService.delete(server);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ServersService) {
            tableView.getItems().clear();
            List<Server> servers = serversService.findAll();
            tableView.getItems().addAll(servers);
        }
    }
}
