package org.example.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Mecanico;
import org.example.util.SceneManager;
import org.example.util.SceneManager;
import org.example.controller.IAppControlable;
import org.example.App;

public class GestionMecanicoViewController implements IAppControlable{

    private App app;

    @FXML private TextField txtNombre;
    @FXML private TextField txtCodigo;

    @FXML private TableView<Mecanico> tblMecanico;
    @FXML private TableColumn<Mecanico, String> colNombre;
    @FXML private TableColumn<Mecanico, String> colCodigo;

    private ObservableList<Mecanico> listaMecanico;

    //inicializar la tabla de Mecanicos
    public void initialize() {
        listaMecanico = FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        tblMecanico.setItems(listaMecanico);
    }

    //registrar Mecanico
    public void onGuardarMecanico() {
        String nombre = txtNombre.getText();
        String codigo = txtCodigo.getText();

        if(nombre.isEmpty() || codigo.isEmpty()) {
            mostrarAlerta("Error, Campos vacios."+"\n"," Todos deben ser rellenados ", Alert.AlertType.WARNING);
            return;
        }

        Mecanico mecanico = new Mecanico(nombre, codigo);
        listaMecanico.add(mecanico);

        mostrarAlerta("Exito."+"\n","Cliente agregado correctamente",Alert.AlertType.INFORMATION);
        limpiarCampos();
    }

    @Override
    public void setApp(App app){
        this.app = app;
        this.listaMecanico.setAll(app.getTaller().getListMecanico());
    }

    //boton de regresar
    @FXML
    public void onVolverMenu() throws Exception{

        SceneManager.cambiarEscena("/org/example/taller_bicicletas/primerPantalla.fxml", app);
    }

    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtCodigo.clear();
    }

    //metodo de alertas y mensajes
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
