package by.serzh.beatsub.ui;

import by.serzh.beatsub.repository.LicenseRepository;
import by.serzh.beatsub.repository.LicenseRepositoryImpl;
import by.serzh.beatsub.repository.ServerRepository;
import by.serzh.beatsub.repository.ServerRepositoryImpl;
import by.serzh.beatsub.service.ServersService;
import by.serzh.beatsub.service.ServersServiceImpl;
import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.AbstractModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    private GuiceContext context = new GuiceContext(this, () -> Collections.singletonList(new GuiceModule()));

    @Override
    public void start(Stage primaryStage) throws Exception {
        context.init();
        ControllerInjectorFactory.init(context);
        ResourceBundle bundle = ResourceBundle.getBundle("bundles/MyBundle", Locale.getDefault());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/main.fxml"), bundle);
        loader.setControllerFactory(ControllerInjectorFactory.getInstance());
        Parent root = loader.load();
        primaryStage.setTitle(bundle.getString("main.title"));
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    private class GuiceModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(ServersService.class).to(ServersServiceImpl.class).asEagerSingleton();
            bind(ServerRepository.class).to(ServerRepositoryImpl.class).asEagerSingleton();
            bind(LicenseRepository.class).to(LicenseRepositoryImpl.class).asEagerSingleton();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
