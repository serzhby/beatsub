package by.serzh.beatsub.ui.servers;

import by.serzh.beatsub.domain.Server;
import by.serzh.beatsub.service.ServersService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.inject.Inject;
import java.util.List;

public class ServersController {

    private ServersService serversService;

    @Inject
    public ServersController(ServersService serversService) {
        this.serversService = serversService;
    }

    @FXML
    private TableView<Server> tableView;

    @FXML
    public void initialize() {
        ObservableList<TableColumn<Server, ?>> columns = tableView.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<>("host"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<>("user"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory<>("password"));
        List<Server> servers = serversService.findAll();
        tableView.getItems().addAll(servers);
    }

}
