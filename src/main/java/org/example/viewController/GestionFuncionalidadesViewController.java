package org.example.viewController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.controller.IAppControlable;
import org.example.util.SceneManager;
import org.example.model.OrdenDeServicio;
import org.example.model.Repuesto;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class GestionFuncionalidadesViewController implements IAppControlable {

    private App app;


    @FXML private Button btnVolver;
    @FXML private Button btnActualizar;


    //Parte de la orden de fecha
    @FXML private TableView<OrdenDeServicio> tblOrdenFecha;
    @FXML private TableColumn<OrdenDeServicio, String>colFecId;
    @FXML private TableColumn<OrdenDeServicio, LocalTime> colFecHora;
    @FXML private TableColumn<OrdenDeServicio, String> colFecCliente;
    @FXML private TableColumn<OrdenDeServicio, String> colFecMecanico;
    @FXML private TableColumn<OrdenDeServicio, String > colFecMotivo;
    @FXML private DatePicker fechaIngresada;
    @FXML private Button btnBuscarOrden;







    //Parte del historial
    @FXML private TableView<OrdenDeServicio> tblHistorial;
    @FXML private TableColumn<OrdenDeServicio, LocalTime> colHistFecha;
    @FXML private TableColumn<OrdenDeServicio, String> colHistId;
    @FXML private TableColumn<OrdenDeServicio, String> colHistMarco;
    @FXML private TableColumn<OrdenDeServicio, String> colHistMecanico;
    @FXML private Button btnBuscar;
    @FXML private TextField txtNumeroMarco;


    //parte del stock
    @FXML private TableView<Repuesto> tblStockBajo;
    @FXML private TableColumn<Repuesto, Integer> colStockCantidad;
    @FXML private TableColumn<Repuesto, String> colStockId;
    @FXML private TableColumn<Repuesto, String> colStockNombre;
    @FXML private TableColumn<Repuesto, Integer> colStockPrecio;





    @Override
    public void setApp(App app){
        this.app=app;
        cargarStockBajo();
        configurarColumnas();
    }

    private void configurarColumnas() {
        // Orden por fecha
        colFecId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFecHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colFecMotivo.setCellValueFactory(new PropertyValueFactory<>("motivoDelServicio"));
        colFecCliente.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getCicla().getCliente().getNombre()));
        colFecMecanico.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getMecanico().getNombre()));

        //  Historial
        colHistId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colHistFecha.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colHistMarco.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getCicla().getMarca()));
        colHistMecanico.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getMecanico().getNombre()));

        //Stock bajo
        colStockId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStockNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colStockCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadDisponible"));
        colStockPrecio.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        (int) cellData.getValue().getPrecio()).asObject());
    }

    // la accion que tiene cada seccion

    @FXML
    private void buscarOrdenByFecha() {
        LocalDate fecha = fechaIngresada.getValue();

        if (fecha == null) {
            mostrarAlerta("Error", "Debes seleccionar una fecha", Alert.AlertType.WARNING);
            return;
        }

        List<OrdenDeServicio> resultado = app.getTaller().listOrdenesByFecha(fecha);
        tblOrdenFecha.setItems(FXCollections.observableArrayList(resultado));
    }

    @FXML
    private void buscarHistorial() {
        String serial = txtNumeroMarco.getText();

        if (serial.isEmpty()) {
            mostrarAlerta("Error", "Debes ingresar el número de marco/serial", Alert.AlertType.WARNING);
            return;
        }

        List<OrdenDeServicio> historial = app.getTaller().listHistorialByCicla(serial);
        tblHistorial.setItems(FXCollections.observableArrayList(historial));
    }

    private void cargarStockBajo() {
        List<Repuesto> repuestosBajos = app.getTaller().listarRepuestosConStockBajo();
        tblStockBajo.setItems(FXCollections.observableArrayList(repuestosBajos));
    }

    @FXML
     void onVolver() throws Exception {
        SceneManager.cambiarEscena("/org/example/taller_bicicletas/primerPantalla.fxml", app);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void actualizarStock(){
        cargarStockBajo();
    }



}