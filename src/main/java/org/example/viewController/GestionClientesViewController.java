package org.example.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Cliente;

public class GestionClientesViewController {
    @FXML private TextField txtNombre;
    @FXML private TextField txtCedula;
    @FXML private TextField txtTelefono;

    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colCedula;
    @FXML private TableColumn<Cliente, String> colTelefono;

    private ObservableList<Cliente> listaClientes;

    //inicializar la tabla de clientes
    public void initialize() {
        listaClientes = FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        tblClientes.setItems(listaClientes);
    }

    //registrar clientes
    @FXML
    public void onGuardarCliente() {
        String nombre= txtNombre.getText();
        String cedula = txtCedula.getText();
        String telefono = txtTelefono.getText();

        if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty()){
            mostrarAlerta("Error, Campos vacios."+"\n"," Todos deben ser rellenados ", Alert.AlertType.WARNING);
            return;
        }

        Cliente cliente= new Cliente(nombre,cedula,telefono,"");
        listaClientes.add(cliente);

        mostrarAlerta("Exito."+"\n","Cliente agregado correctamente",Alert.AlertType.INFORMATION);
        limpiarCampos();
    }

    //boton de regresar al menu
    @FXML
    public void onVolverMenu(){

    }

    //metodo de limpiar campos despues de registrar un cliente
    private void limpiarCampos() {
        txtNombre.clear();
        txtCedula.clear();
        txtTelefono.clear();
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
