package org.example.model;

import java.util.ArrayList;

public class Taller {

    private String nombre;
    private String nit;
    private String direccion;
    private ArrayList<Cliente> listClientes;


    public Taller(String nombre, String nit, String direeccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
    }


    //Crud Cliente

    //registro cliente

    public boolean registrarCliente(String nombre, String cedula, String telefono, String direccion){

        Cliente newCliente = new Cliente(nombre, cedula, telefono, direccion);

        for(Cliente c: listClientes){

            if(c.getCedula().equals(cedula)){
                return false;
            }
        }

        if(listClientes == null){

        return false;
        }

        listClientes.add(newCliente);
        return true;

    }


    public boolean eliminarCliente(String cedula){

        Cliente encontrado = buscarClienteByCedula(cedula);



    }


    public Cliente buscarClienteByCedula(String cedula){

        for(Cliente c: listClientes){
            if(c.getCedula().equals(cedula)){
                return c;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }







































































































































































    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
