package org.example.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

    public class PrimerPantallaViewController {

        @FXML
        private Button gestionCiclas;

        @FXML
        private Button gestionCliente;

        @FXML
        private Button gestionMecanicos;

        @FXML
        private Button gestionOrden;

        @FXML
        private Button gestionRepuestos;

        @FXML
        private Button otro;

        @FXML
        void gestionarCiclas(ActionEvent event) {

        }

        @FXML
        void gestionarCliente(ActionEvent event) {

        }

        @FXML
        void gestionarMecanicos(ActionEvent event) {

        }

        @FXML
        void gestionarOrdenes(ActionEvent event) {

        }

        @FXML
        void gestionarRepuestos(ActionEvent event) {

        }

        @FXML
        void irOtrasFuncionalidades(ActionEvent event) {

        }


        @FXML
        private void irAVisitante() throws Exception {
            FXMLLoader loader = SceneManager.cambiarEscena(gestionCliente, "/org/example/parquejfx/visitante-bienvenida.fxml");
            VisitanteBienvenidaViewController ctrl =loader.getController();
            ctrl.setApp(this.app);
        }

    }

