package org.example.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Taller {

    private String nombre;
    private String nit;
    private String direccion;
    private ArrayList<Cliente> listClientes;
    private ArrayList<Cicla> listCicla;
    private ArrayList<Mecanico> listMecanico;
    private ArrayList<OrdenDeServicio> listOrdenDeServicio;
    private ArrayList<Repuesto> listRepuestos;

    public Taller(String nombre, String nit, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.listClientes = new ArrayList<>();
        this.listCicla = new ArrayList<>();
        this.listMecanico = new ArrayList<>();
        this.listOrdenDeServicio = new ArrayList<>();
        this.listRepuestos = new ArrayList<>();
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
        if (encontrado == null) {

            return false;
        }
        listClientes.remove(encontrado);
        return true;
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

    //registro cicla


    public boolean registrarCicla(String marca, String color, String numeroMarco, TipoCicla tipo, int anio, Cliente clienteAsociado){

        if(listCicla == null){
            return false;
        }

// se le asigna un cliente asociado a la cicla

        for(Cicla i: listCicla){

            if(i.getNumeroMarco().equals(numeroMarco)){
                return false;
            }
        }

        Cicla newCicla = new Cicla(marca, color, numeroMarco,tipo, anio, clienteAsociado);
        listCicla.add(newCicla);
        return true;
    }

    public Cicla buscarCiclaByNumeroMarco(String numeroMarco){

        for( Cicla i: listCicla){
            if(i.getNumeroMarco().equals(numeroMarco)){
                return i;
            }
        }
        return null;
    }

    // registro mecanico

    public boolean registrarMecanico(String codigo, String nombre, TipoEspecialidad tipoEspecialidad){

        Mecanico newMecanico = new Mecanico(codigo, nombre, tipoEspecialidad);

        for(Mecanico m: listMecanico){

            if(m.getCodigo().equals(codigo)){
                return false;
            }
        }

        if(listMecanico == null){

            return false;
        }

        listMecanico.add(newMecanico);
        return true;
    }

        public Mecanico buscarMecanicoByCodigo( String codigo){

            for( Mecanico mecanico: listMecanico){
                if(mecanico.getCodigo().equals(codigo)){
                    return mecanico;
                }
            }
            return null;
        }

        //crer orden de servicio

        public OrdenDeServicio registrarOrdenDeServicio(String id, LocalDate fechaDeIngreso, LocalTime hora,
                                                        String motivoDelServicio, String diagnostico,
                                                        Cicla cicla, Mecanico mecanico) {
            OrdenDeServicio nuevaOrden = new OrdenDeServicio(id, fechaDeIngreso, hora,
                    motivoDelServicio, diagnostico,
                    cicla, mecanico);
            this.listOrdenDeServicio.add(nuevaOrden);
            return nuevaOrden;
        }

        //Historial de servicios por bicicleta
            public ArrayList<OrdenDeServicio> listHistorialByCicla(String numeroMarco) {
                ArrayList<OrdenDeServicio> historial = new ArrayList<>();
                for (OrdenDeServicio orden : listOrdenDeServicio) {
                    if (orden.getCicla().getNumeroMarco().equalsIgnoreCase(numeroMarco)) {
                        historial.add(orden);
                    }
                }
                return historial;
            }

            //buscar ordenes del dia por fecha

            public ArrayList<OrdenDeServicio> listOrdenesByFecha(LocalDate fecha) {
                ArrayList<OrdenDeServicio> resultado = new ArrayList<>();
                for (OrdenDeServicio orden : listOrdenDeServicio) {
                    if (orden.getFechaIngreso().equals(fecha)) {
                        resultado.add(orden);
                    }
                }
                return resultado;
            }




    public List<Repuesto> listarRepuestosConStockBajo() {
        List<Repuesto> resultado = new ArrayList<>();
        for (Repuesto r : listRepuestos) {
            if (r.estaStockBajo()) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public ArrayList<Cliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(ArrayList<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public ArrayList<Cicla> getListCicla() {
        return listCicla;
    }

    public void setListCicla(ArrayList<Cicla> listCicla) {
        this.listCicla = listCicla;
    }

    public ArrayList<Mecanico> getListMecanico() {
        return listMecanico;
    }

    public void setListMecanico(ArrayList<Mecanico> listMecanico) {
        this.listMecanico = listMecanico;
    }

    public ArrayList<OrdenDeServicio> getListOrdenDeServicio() {
        return listOrdenDeServicio;
    }

    public void setListOrdenDeServicio(ArrayList<OrdenDeServicio> listOrdenDeServicio) {
        this.listOrdenDeServicio = listOrdenDeServicio;
    }

    public ArrayList<Repuesto> getListRepuestos() {
        return listRepuestos;
    }

    public void setListRepuestos(ArrayList<Repuesto> listRepuestos) {
        this.listRepuestos = listRepuestos;
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

    @Override
    public String toString() {
        return "Taller{" +
                "nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", direccion='" + direccion + '\'' +
                ", listClientes=" + listClientes +
                ", listCicla=" + listCicla +
                ", listMecanico=" + listMecanico +
                ", listOrdenDeServicio=" + listOrdenDeServicio +
                '}';
    }
}

