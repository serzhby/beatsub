package by.serzh.beatsub.ui;

import by.serzh.beatsub.preferences.PrefsHolder;
import com.google.inject.Inject;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class StageController implements Initializable {

    private ResourceBundle resources;
    private Stage stage;

    protected final PrefsHolder prefsHolder;

    @Inject
    public StageController(PrefsHolder prefsHolder) {
        this.prefsHolder = prefsHolder;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
    }

    private void bindResize() {
        getStage().widthProperty().addListener((obs, oldVal, newVal) -> {
            Dimens newDimens = new Dimens(newVal.doubleValue(), getStage().getHeight());
            prefsHolder.saveDimens(getClass().getSimpleName(), newDimens);
        });
        getStage().heightProperty().addListener((obs, oldVal, newVal) -> {
            Dimens newDimens = new Dimens(getStage().getWidth(), newVal.doubleValue());
            prefsHolder.saveDimens(getClass().getSimpleName(), newDimens);
        });
    }

    public Stage getStage() {
        return stage;
    }

    public ResourceBundle getResources() {
        return resources;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        restoreStageDimens();
        bindResize();
    }

    protected void restoreStageDimens() {
        Dimens dimens = prefsHolder.readDimens(getClass().getSimpleName());
        getStage().setWidth(dimens.getWidth());
        getStage().setHeight(dimens.getHeight());
    }

}
