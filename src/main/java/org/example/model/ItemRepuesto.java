package org.example.model;

public class ItemRepuesto {

    private Repuesto repuesto;
    private int cantidadUsada;

    public ItemRepuesto(Repuesto respuesto, int cantidad) {
        this.repuesto=respuesto;
        this.cantidadUsada = cantidad;
    }



    public double getSubtotal(){
        return cantidadUsada * repuesto.getPrecio();
    }


    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    public int getCantiadad() {
        return cantidadUsada;
    }

    public void setCantiadad(int cantiadad) {
        this.cantidadUsada = cantiadad;
    }
}
