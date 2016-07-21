package by.serzh.beatsub.ui.servers;

import by.serzh.beatsub.service.ServersService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class AddServerController {

    @FXML
    public TextField hostTextField;

    @FXML
    public TextField userTextField;

    @FXML
    public PasswordField passwordTextField;

    private ServersService serversService;

    @Inject
    public AddServerController(ServersService serversService) {
        this.serversService = serversService;
    }

    @FXML
    public void addServer() {
        System.out.println(serversService);
    }

}
