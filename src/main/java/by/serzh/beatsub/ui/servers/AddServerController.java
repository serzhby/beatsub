package by.serzh.beatsub.ui.servers;

import by.serzh.beatsub.api.domain.Server;
import by.serzh.beatsub.service.ServersService;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.util.TextUtils;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class AddServerController implements Initializable {

    @FXML
    public TextField hostTextField;

    @FXML
    public TextField userTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public Label errorLabel;

    private ServersService serversService;
    private ResourceBundle resources;

    @Inject
    public AddServerController(ServersService serversService) {
        this.serversService = serversService;
    }

    @FXML
    public void onAddServerClicked(ActionEvent actionEvent) {
        String host = hostTextField.getText().replaceAll("http://", "");
        String username = userTextField.getText();
        String password = passwordTextField.getText();
        if(validate(host, username, password)) {
            String[] hostParts = host.split(":");
            host = hostParts[0];
            Integer port = hostParts.length == 2 ? Integer.valueOf(hostParts[1]) : null;
            Server server = new Server(host, username, password);
            if(port != null) {
                server.setPort(port);
            }
            serversService.addServer(server);
            closeWindow();
        }
    }

    private boolean validate(String host, String username, String password) {
        if(!UrlValidator.getInstance().isValid("http://" + host)) {
            setError("add_server.error_host");
            return false;
        } else if(TextUtils.isBlank(username)) {
            setError("add_server.error_username");
            return false;
        } else if(TextUtils.isBlank(password)) {
            setError("add_server.error_password");
            return false;
        } else {
            return true;
        }
    }

    private void clearError() {
        setError(null);
    }

    private void setError(String textCode) {
        String text = textCode == null ? "" : resources.getString(textCode);
        errorLabel.setText(text);
    }

    private void closeWindow() {
        ((Stage) hostTextField.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        ChangeListener<String> clearErrorListener = (observable, oldValue, newValue) -> clearError();
        hostTextField.textProperty().addListener(clearErrorListener);
        userTextField.textProperty().addListener(clearErrorListener);
        passwordTextField.textProperty().addListener(clearErrorListener);
    }
}
