module org.example.taller_bicicletas {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.taller_bicicletas to javafx.fxml;
    exports org.example.taller_bicicletas;
}