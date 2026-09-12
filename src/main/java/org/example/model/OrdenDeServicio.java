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
    private Cicla cicla;
    private Mecanico mecanico;
    private ArrayList<ItemRepuesto> listItems;
    private ArrayList<TrabajoRealizado> listTrabajos;

    @Override
    public String toString() {
        return "OrdenDeServicio{" +
                "id='" + id + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", hora=" + hora +
                ", motivoDelServicio='" + motivoDelServicio + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", cicla=" + cicla +
                ", mecanico=" + mecanico +
                ", listItems=" + listItems +
                ", listTrabajos=" + listTrabajos +
                '}';
    }

    public OrdenDeServicio(String id, LocalDate fechaIngreso, LocalTime hora, String motivoDelServicio,
                           String diagnostico, Cicla cicla, Mecanico mecanico) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.hora = hora;
        this.motivoDelServicio = motivoDelServicio;
        this.diagnostico = diagnostico;
        this.cicla = cicla;
        this.mecanico = mecanico;
        this.listItems=new ArrayList<>();
        this.listTrabajos=new ArrayList<>();
    }


    //metodo para agregar un Item


    public void agregarItemRepuesto(Repuesto repuesto, int cantidadUsada){
        repuesto.descontarStock(cantidadUsada);
        ItemRepuesto newItemRepuesto = new ItemRepuesto(repuesto, cantidadUsada);
        this.listItems.add(newItemRepuesto);
    }

    //metodo agregar trabajo

    public void agregarTrabajo(TrabajoRealizado trabajo) {

     this.listTrabajos.add(trabajo);
    }


    //Metodo para calcular el costo total de la orden de servicio

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


    //Metodos gett y set

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

    public Cicla getCicla() {
        return cicla;
    }

    public void setCicla(Cicla cicla) {
        this.cicla = cicla;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }
}
