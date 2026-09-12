package org.example.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.App;
import org.example.controller.IAppControlable;

public class SceneManager {
    public static void cambiarEscena(String rutaFXML, App app) throws Exception {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(rutaFXML));
        Parent nuevaVista = loader.load();

        Object controller = loader.getController();
        if (controller instanceof IAppControlable) {
            ((IAppControlable) controller).setApp(app);
        }

        app.getStagePrincipal().setScene(new Scene(nuevaVista));
    }
}