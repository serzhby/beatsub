package by.serzh.beatsub.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public abstract class BaseStage extends Stage {

    protected Parent load(FXMLLoader loader) {
        try {
            return loader.load();
        } catch (IOException ex) {
            throw new RuntimeException("Cannot load fxml", ex);
        }
    }

    protected BaseStage(StageController stageController, String templatePath, String titleKey) {
        this(stageController.getResources(), templatePath, titleKey);
    }

    protected BaseStage(ResourceBundle resources, String templatePath, String titleKey) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(templatePath), resources);
        loader.setControllerFactory(ControllerInjectorFactory.getInstance());
        Parent root = load(loader);
        setScene(new Scene(root));
        setTitle(resources.getString(titleKey));

        StageController controller = loader.getController();
        controller.setStage(this);
    }

}
