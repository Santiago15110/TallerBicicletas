package org.example;

import javafx.application.Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.viewController.PrimerPantallaViewController;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        App app = new App();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/taller_bicicletas/primerPantalla.fxml"));
        Parent root = loader.load();

        PrimerPantallaViewController controller = loader.getController();
        controller.setApp(app);

        stage.setScene(new Scene(root));
        stage.setTitle("Taller de Bicicletas");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}