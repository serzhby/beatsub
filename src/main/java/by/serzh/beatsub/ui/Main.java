package by.serzh.beatsub.ui;

import by.serzh.beatsub.api.client.browsing.BrowsingApi;
import by.serzh.beatsub.api.client.browsing.BrowsingApiImpl;
import by.serzh.beatsub.api.client.license.LicenseApi;
import by.serzh.beatsub.api.client.license.LicenseApiImpl;
import by.serzh.beatsub.api.client.subsonicclient.SubsonicClientFactory;
import by.serzh.beatsub.api.client.subsonicclient.SubsonicClientFactoryImpl;
import by.serzh.beatsub.preferences.PrefsHolder;
import by.serzh.beatsub.preferences.PrefsHolderImpl;
import by.serzh.beatsub.repository.LicenseRepository;
import by.serzh.beatsub.repository.LicenseRepositoryImpl;
import by.serzh.beatsub.servers.ServerRepository;
import by.serzh.beatsub.servers.ServerRepositoryImpl;
import by.serzh.beatsub.service.IndexService;
import by.serzh.beatsub.service.IndexServiceImpl;
import by.serzh.beatsub.servers.ServersService;
import by.serzh.beatsub.servers.ServersServiceImpl;
import by.serzh.beatsub.ui.prefs.PrefsStorage;
import by.serzh.beatsub.ui.prefs.PrefsStorageImpl;
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
        StageController controller = loader.getController();
        controller.setStage(primaryStage);
    }

    private class GuiceModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(ServersService.class).to(ServersServiceImpl.class).asEagerSingleton();
            bind(ServerRepository.class).to(ServerRepositoryImpl.class).asEagerSingleton();
            bind(LicenseRepository.class).to(LicenseRepositoryImpl.class).asEagerSingleton();
            bind(SubsonicClientFactory.class).to(SubsonicClientFactoryImpl.class).asEagerSingleton();
            bind(LicenseApi.class).to(LicenseApiImpl.class).asEagerSingleton();
            bind(PrefsStorage.class).to(PrefsStorageImpl.class).asEagerSingleton();
            bind(BrowsingApi.class).to(BrowsingApiImpl.class).asEagerSingleton();
            bind(IndexService.class).to(IndexServiceImpl.class).asEagerSingleton();
            bind(PrefsHolder.class).to(PrefsHolderImpl.class).asEagerSingleton();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
