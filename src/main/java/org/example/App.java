package org.example;

import javafx.stage.Stage;
import org.example.model.Taller;

public class App {

        private Taller taller;
        private Stage stagePrincipal;

        public App(){
            this.taller = new Taller("Catra", "123456", "calle 12");
        }


        public Taller getTaller(){
            return taller;
        }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }
}
