package org.example.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.example.App;
import org.example.controller.IAppControlable;
import org.example.model.Repuesto;
import org.example.util.SceneManager;

public class GestionRepuestoViewController implements IAppControlable {

    private App app;
    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtCantidadDisponible;
    @FXML private TextField txtCantidadMin;

    @FXML private TableView<Repuesto> tblRepuesto;
    @FXML private TableColumn<Repuesto,String> colId;
    @FXML private TableColumn<Repuesto, String> colNombre;
    @FXML private TableColumn<Repuesto, String> colPrecio;
    @FXML private TableColumn<Repuesto, String> colCantidadDisponible;
    @FXML private TableColumn<Repuesto, String> colCantidadMin;

    private ObservableList<Repuesto> listaRepuesto;

    //inicializar la tabla
    public void initialize(){
       listaRepuesto = FXCollections.observableArrayList();
       colId.setCellValueFactory(new PropertyValueFactory<>("id"));
       colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
       colCantidadDisponible.setCellValueFactory(new PropertyValueFactory<>("cantidadDisponible"));
       colCantidadMin.setCellValueFactory(new PropertyValueFactory<>("cantidadMin"));

       tblRepuesto.setItems(listaRepuesto);
    }

    @Override
    public void setApp(App app){
        this.app = app;
        listaRepuesto.setAll(app.getTaller().getListRepuestos());
    }

    //guardar el repuesto
    @FXML
    public void onGuardarRepuesto(){
        String id= txtId.getText().trim();
        String nombre=txtNombre.getText().trim();
        String precio=txtPrecio.getText().trim();
        String cantidadDisponible=txtCantidadDisponible.getText().trim();
        String cantidadMin=txtCantidadMin.getText().trim();

        if(id.isEmpty() || nombre.isEmpty() || precio.isEmpty() ||
                cantidadDisponible.isEmpty() || cantidadMin.isEmpty()){
            mostrarAlerta("Error, Campos Vacios"+"\n","Todos los espacios deben ser rellenados", Alert.AlertType.ERROR);
            return;
        }

        try{
            double precioRepuesto= Double.parseDouble(precio);
            int cantidadDis= Integer.parseInt(cantidadDisponible);
            int cantidadMinima= Integer.parseInt(cantidadMin);

            boolean registrado= app.getTaller().registrarRepuesto(id,nombre,
                    precioRepuesto,cantidadDis,cantidadMinima);

            if (!registrado) {
                mostrarAlerta("Error","Ya existe un repuesto con el ID ingresado", Alert.AlertType.ERROR);
                return;
            }

            listaRepuesto.setAll(app.getTaller().getListRepuestos());
            mostrarAlerta("Exito","Repuesto agregado correctamente", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }catch (NumberFormatException e){
            mostrarAlerta("Error", "Los datos de precio, cantidad disponible y cantidad minima, deben ser numeros", Alert.AlertType.ERROR);
        }

    }

    //Regresar al menu
    @FXML
    public void onVolverMenu() throws Exception {
        SceneManager.cambiarEscena("/org/example/taller_bicicletas/primerPantalla.fxml", app);
    }

    private void limpiarCampos(){
        txtId.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidadDisponible.clear();
        txtCantidadMin.clear();
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
