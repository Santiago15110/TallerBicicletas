package org.example.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.App;
import org.example.controller.IAppControlable;
import org.example.util.SceneManager;

public class PrimerPantallaViewController implements IAppControlable {

    private App app;

    @FXML private Button gestionCiclas;
    @FXML private Button gestionCliente;
    @FXML private Button gestionMecanicos;
    @FXML private Button gestionOrden;
    @FXML private Button gestionRepuestos;
    @FXML private Button otro;

    @Override
    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    void gestionarCliente(ActionEvent event) throws Exception {
        SceneManager.cambiarEscena("/org/example/taller_bicicletas/clientes.fxml", app);
    }

    @FXML
    void gestionarCiclas(ActionEvent event) throws Exception {
        SceneManager.cambiarEscena("/org/example/taller_bicicletas/bicicletas.fxml", app);
    }

    @FXML
    void gestionarMecanicos(ActionEvent event) throws Exception {
        SceneManager.cambiarEscena( "/org/example/taller_bicicletas/mecanicos.fxml", app);
    }

    @FXML
    void gestionarOrdenes(ActionEvent event) throws Exception {
        SceneManager.cambiarEscena( "/org/example/taller_bicicletas/ordenes.fxml", app);
    }

    @FXML
    void gestionarRepuestos(ActionEvent event) throws Exception {
        SceneManager.cambiarEscena("/org/example/taller_bicicletas/repuesto.fxml", app);
    }

    @FXML
    void irOtrasFuncionalidades(ActionEvent event) throws Exception {
        SceneManager.cambiarEscena("/org/example/taller_bicicletas/funcionalidades.fxml", app);
    }


}