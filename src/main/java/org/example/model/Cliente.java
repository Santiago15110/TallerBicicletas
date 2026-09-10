package org.example.model;

import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String cedula;
    private String telefono;
    private String direccion;
    private ArrayList<Cicla> listCicla;

    public Cliente(String nombre, String cedula, String telefono, String direccion) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
        this.listCicla = new ArrayList<>();
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Cicla> getListCicla() {
        return listCicla;
    }
    public void setListCicla(ArrayList<Cicla> listCicla) {
        this.listCicla = listCicla;
    }

    //metodo de agregar cicla
    public void agregarCicla(Cicla cicla){
        listCicla.add(cicla);
    }

}
