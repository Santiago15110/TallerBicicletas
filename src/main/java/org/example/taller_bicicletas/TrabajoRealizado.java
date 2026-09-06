package org.example.taller_bicicletas;

public class TrabajoRealizado {
    private String descripcion;
    private double manoObra;

    public TrabajoRealizado(String descripcion, double manoObra) {
        this.descripcion = descripcion;
        this.manoObra = manoObra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getManoObra() {
        return manoObra;
    }

    public void setManoObra(double manoObra) {
        this.manoObra = manoObra;
    }
}
