package org.example.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.controller.IAppControlable;
import org.example.model.Cicla;
import org.example.model.Cliente;

import org.example.model.TipoCicla;
import org.example.util.SceneManager;


public class GestionBicicletaViewController implements IAppControlable {

    private App app;

    @FXML private TextField txtMarca;
    @FXML private TextField txtColor;
    @FXML private TextField txtNumeroMarco;
    @FXML private ComboBox<TipoCicla> cbTipo;
    @FXML private TextField txtAnio;
    @FXML private ComboBox<Cliente> cbClienteAsociado;


    @FXML private TableView<Cicla> tblCicla;
    @FXML private TableColumn<Cicla, String> colMarca;
    @FXML private TableColumn<Cicla, String> colColor;
    @FXML private TableColumn<Cicla, String> colNumeroMarco;
    @FXML private TableColumn<Cicla, TipoCicla> colTipo;
    @FXML private TableColumn<Cicla, Integer> colAnio;
    @FXML private TableColumn<Cicla, Cliente> colClienteAsociado;

    private ObservableList<Cicla> listaCicla;

    //inicializar la tabla de Bicicletas
    public void initialize() {
        listaCicla = FXCollections.observableArrayList();
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colNumeroMarco.setCellValueFactory(new PropertyValueFactory<>("numeroMarco"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colClienteAsociado.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        cbTipo.setItems(FXCollections.observableArrayList(TipoCicla.values()));


        tblCicla.setItems(listaCicla);
    }

    @Override
    public void setApp(App app) {
        this.app = app;
        listaCicla.setAll(app.getTaller().getListCicla());
    }

    //registrar bicicleta
    @FXML
    public void onGuardarCliente() {
        String marca = txtMarca.getText();
        String color = txtColor.getText();
        String numeroMarco = txtNumeroMarco.getText();
        TipoCicla tipoCicla = cbTipo.getValue();

        Cliente clienteAsociado = cbClienteAsociado.getValue();


        if (marca.isEmpty() || color.isEmpty() || numeroMarco.isEmpty() || tipoCicla == null || txtAnio.getText().isEmpty() || clienteAsociado == null){
            mostrarAlerta("Error, Campos vacios." + "\n", " Todos deben ser rellenados ", Alert.AlertType.WARNING);
            return;
        }

        int anio;
        try {
            anio = Integer.parseInt(txtAnio.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El año debe ser un número válido", Alert.AlertType.WARNING);
            return;
        }


        boolean registrado = app.getTaller().registrarCicla(marca, color, numeroMarco, tipoCicla, anio , clienteAsociado );

        if (!registrado) {
            mostrarAlerta("Error", "Ya existe un cliente con esa cedula", Alert.AlertType.WARNING);
        }

        listaCicla.setAll(app.getTaller().getListCicla());

        mostrarAlerta("Exito." + "\n", "Cliente agregado correctamente", Alert.AlertType.INFORMATION);
        limpiarCampos();
    }

    //boton de regresar al menu
    @FXML
    void onVolverMenu() throws Exception {

        SceneManager.cambiarEscena("/org/example/taller_bicicletas/primerPantalla.fxml", app);
    }

    //metodo de limpiar campos despues de registrar una bicicleta
    private void limpiarCampos() {
        txtMarca.clear();
        txtColor.clear();
        txtNumeroMarco.clear();
        txtAnio.clear();


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




