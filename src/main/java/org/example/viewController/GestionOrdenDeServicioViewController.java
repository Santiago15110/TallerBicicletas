package org.example.viewController;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.controller.IAppControlable;
import org.example.App;
import org.example.model.Cicla;
import org.example.model.ItemRepuesto;
import org.example.model.Mecanico;
import org.example.model.OrdenDeServicio;
import org.example.model.Repuesto;
import org.example.model.TrabajoRealizado;
import org.example.util.SceneManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

    public class GestionOrdenDeServicioViewController implements IAppControlable {

        private App app;

        // Datos generales
        @FXML private TextField txtId;
        @FXML private DatePicker dpFechaIngreso;
        @FXML private TextField txtHora;
        @FXML private TextArea txtMotivo;
        @FXML private TextArea txtDiagnostico;

        // Cicla / Mecanico
        @FXML private ComboBox<Cicla> comboCicla;
        @FXML private ComboBox<Mecanico> comboMecanico;

        // Repuestos
        @FXML private ComboBox<Repuesto> comboRepuesto;
        @FXML private TextField txtCantidadRepuesto;
        @FXML private Button btnAgregarRepuesto;
        @FXML private TableView<ItemRepuesto> tablaRepuestos;
        @FXML private TableColumn<ItemRepuesto, String> colRepuestoNombre;
        @FXML private TableColumn<ItemRepuesto, Integer> colRepuestoCantidad;
        @FXML private TableColumn<ItemRepuesto, Double> colRepuestoSubtotal;

        // Trabajos realizados
        @FXML private TextField txtDescripcionTrabajo;
        @FXML private TextField txtManoObra;
        @FXML private Button btnAgregarTrabajo;
        @FXML private TableView<TrabajoRealizado> tablaTrabajos;
        @FXML private TableColumn<TrabajoRealizado, String> colTrabajoDescripcion;
        @FXML private TableColumn<TrabajoRealizado, Double> colTrabajoManoObra;

        // Resumen y acciones
        @FXML private Label lblCostoTotal;
        @FXML private Button btnCancelar;
        @FXML private Button btnGuardarOrden;
        @FXML private Button btnRegresar;

        private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

        private ObservableList<ItemRepuesto> listaItemsRepuesto;
        private ObservableList<TrabajoRealizado> listaTrabajos;

        public void initialize() {
            listaItemsRepuesto = FXCollections.observableArrayList();
            listaTrabajos = FXCollections.observableArrayList();

            colRepuestoNombre.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getRepuesto().getNombre()));
            colRepuestoCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadUsada"));
            colRepuestoSubtotal.setCellValueFactory(cellData ->
                    new SimpleDoubleProperty(cellData.getValue().getSubtotal()).asObject());

            colTrabajoDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            colTrabajoManoObra.setCellValueFactory(new PropertyValueFactory<>("manoObra"));

            tablaRepuestos.setItems(listaItemsRepuesto);
            tablaTrabajos.setItems(listaTrabajos);

            listaItemsRepuesto.addListener((javafx.collections.ListChangeListener<ItemRepuesto>) c -> actualizarCostoTotal());
            listaTrabajos.addListener((javafx.collections.ListChangeListener<TrabajoRealizado>) c -> actualizarCostoTotal());
        }

        @Override
        public void setApp(App app) {
            this.app = app;
            comboCicla.setItems(FXCollections.observableArrayList(app.getTaller().getListCicla()));
            comboMecanico.setItems(FXCollections.observableArrayList(app.getTaller().getListMecanico()));
            comboRepuesto.setItems(FXCollections.observableArrayList(app.getTaller().getListRepuestos()));
        }

        // Agregar repuesto a la tabla (aun no descuenta stock, eso se hace al guardar la orden)
        @FXML
        private void onAgregarRepuesto(ActionEvent event) {
            Repuesto repuesto = comboRepuesto.getValue();
            String cantidadTexto = txtCantidadRepuesto.getText();

            if (repuesto == null || cantidadTexto == null || cantidadTexto.isEmpty()) {
                mostrarAlerta("Error", "Selecciona un repuesto e ingresa la cantidad.", Alert.AlertType.WARNING);
                return;
            }

            int cantidad;
            try {
                cantidad = Integer.parseInt(cantidadTexto);
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "La cantidad debe ser un numero entero.", Alert.AlertType.WARNING);
                return;
            }

            if (cantidad <= 0) {
                mostrarAlerta("Error", "La cantidad debe ser mayor a cero.", Alert.AlertType.WARNING);
                return;
            }

            if (cantidad > repuesto.getCantidadDisponible()) {
                mostrarAlerta("Error", "No hay stock suficiente de " + repuesto.getNombre(), Alert.AlertType.WARNING);
                return;
            }

            ItemRepuesto nuevoItem = new ItemRepuesto(repuesto, cantidad);
            listaItemsRepuesto.add(nuevoItem);

            comboRepuesto.setValue(null);
            txtCantidadRepuesto.clear();
        }

        // Agregar trabajo realizado a la tabla
        @FXML
        private void onAgregarTrabajo(ActionEvent event) {
            String descripcion = txtDescripcionTrabajo.getText();
            String manoObraTexto = txtManoObra.getText();

            if (descripcion == null || descripcion.isEmpty() || manoObraTexto == null || manoObraTexto.isEmpty()) {
                mostrarAlerta("Error", "Completa la descripcion y el valor de la mano de obra.", Alert.AlertType.WARNING);
                return;
            }

            double manoObra;
            try {
                manoObra = Double.parseDouble(manoObraTexto);
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "El valor de mano de obra debe ser numerico.", Alert.AlertType.WARNING);
                return;
            }

            if (manoObra < 0) {
                mostrarAlerta("Error", "El valor de mano de obra no puede ser negativo.", Alert.AlertType.WARNING);
                return;
            }

            listaTrabajos.add(new TrabajoRealizado(descripcion, manoObra));

            txtDescripcionTrabajo.clear();
            txtManoObra.clear();
        }

        @FXML
        private void onGuardarOrden(ActionEvent event) {
            String id = txtId.getText();
            LocalDate fecha = dpFechaIngreso.getValue();
            String horaTexto = txtHora.getText();
            String motivo = txtMotivo.getText();
            String diagnostico = txtDiagnostico.getText();
            Cicla cicla = comboCicla.getValue();
            Mecanico mecanico = comboMecanico.getValue();

            if (id == null || id.isEmpty() || fecha == null || horaTexto == null || horaTexto.isEmpty()
                    || motivo == null || motivo.isEmpty() || cicla == null || mecanico == null) {
                mostrarAlerta("Error", "Completa todos los datos generales, la cicla y el mecanico.", Alert.AlertType.WARNING);
                return;
            }

            LocalTime hora;
            try {
                hora = LocalTime.parse(horaTexto, FORMATO_HORA);
            } catch (DateTimeParseException e) {
                mostrarAlerta("Error", "La hora debe tener el formato HH:mm, por ejemplo 14:30.", Alert.AlertType.WARNING);
                return;
            }

            if (listaItemsRepuesto.isEmpty() && listaTrabajos.isEmpty()) {
                mostrarAlerta("Error", "Agrega al menos un repuesto o un trabajo realizado.", Alert.AlertType.WARNING);
                return;
            }

            OrdenDeServicio nuevaOrden = app.getTaller().registrarOrdenDeServicio(
                    id, fecha, hora, motivo, diagnostico, cicla, mecanico);

            for (ItemRepuesto item : listaItemsRepuesto) {
                nuevaOrden.agregarItemRepuesto(item.getRepuesto(), item.getCantidadUsada());
            }

            for (TrabajoRealizado trabajo : listaTrabajos) {
                nuevaOrden.agregarTrabajo(trabajo);
            }

            mostrarAlerta("Exito", "Orden de servicio registrada correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }

        @FXML
        private void onCancelar(ActionEvent event) {
            limpiarCampos();
        }

        @FXML
        private void onVolverMenu(ActionEvent event) throws Exception {
            SceneManager.cambiarEscena("/org/example/taller_bicicletas/primerPantalla.fxml", app);
        }

        private void actualizarCostoTotal() {
            double totalRepuestos = 0;
            for (ItemRepuesto item : listaItemsRepuesto) {
                totalRepuestos += item.getSubtotal();
            }

            double totalManoObra = 0;
            for (TrabajoRealizado trabajo : listaTrabajos) {
                totalManoObra += trabajo.getManoObra();
            }

            lblCostoTotal.setText(String.format("$%.0f", totalRepuestos + totalManoObra));
        }

        private void limpiarCampos() {
            txtId.clear();
            dpFechaIngreso.setValue(null);
            txtHora.clear();
            txtMotivo.clear();
            txtDiagnostico.clear();
            comboCicla.setValue(null);
            comboMecanico.setValue(null);
            comboRepuesto.setValue(null);
            txtCantidadRepuesto.clear();
            txtDescripcionTrabajo.clear();
            txtManoObra.clear();
            listaItemsRepuesto.clear();
            listaTrabajos.clear();
            lblCostoTotal.setText("$0");
        }

        private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
            Alert alert = new Alert(tipo);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        }
    }

