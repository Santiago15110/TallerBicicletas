package org.example.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class OrdenDeServicio {
    private String id;
    private LocalDate fechaIngreso;
    private LocalTime hora;
    private String motivoDelServicio;
    private String diagnostico;
    private ArrayList<ItemRepuesto> listItems;
    private ArrayList<TrabajoRealizado> listTrabajos;

    public OrdenDeServicio(String id, LocalDate fechaIngreso, LocalTime hora, String motivoDelServicio, String diagnostico) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.hora = hora;
        this.motivoDelServicio = motivoDelServicio;
        this.diagnostico = diagnostico;
    }


    public void agregarItemRepuesto(Repuesto repuesto, int cantidadUsada){
        repuesto.descontarStock(cantidadUsada);
        ItemRepuesto newItemRepuesto = new ItemRepuesto(repuesto, cantidadUsada);
        this.listItems.add(newItemRepuesto);
    }

    public void agregarTrabajo(TrabajoRealizado trabajo) {

     this.listTrabajos.add(trabajo);
    }

    public double calcularCostoTotal(){

        double totalRepuestos = 0;

        for(ItemRepuesto i: listItems){
            totalRepuestos += i.getSubtotal();
        }

        double totalManoObra =0;

        for(TrabajoRealizado t: listTrabajos){

            totalManoObra += t.getManoObra();
        }

       return  totalManoObra + totalRepuestos;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivoDelServicio() {
        return motivoDelServicio;
    }

    public void setMotivoDelServicio(String motivoDelServicio) {
        this.motivoDelServicio = motivoDelServicio;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}
