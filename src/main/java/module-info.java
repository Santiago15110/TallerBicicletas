module org.example.taller_bicicletas {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.viewController to javafx.fxml;
    exports org.example.controller;
    exports org.example.model;
    opens org.example.model to javafx.fxml, org.junit.platform.commons;
}