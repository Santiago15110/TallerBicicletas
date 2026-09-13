package org.example.model;

public class Cicla {
    private String marca;
    private String color;
    private String numeroMarco;
    private TipoCicla tipo;
    private int anio;
    private Cliente clienteAsociado;


    public Cicla(String marca, String color, String numeroMarco, TipoCicla tipo, int anio, Cliente cliente) {
        this.marca = marca;
        this.color = color;
        this.numeroMarco = numeroMarco;
        this.tipo = tipo;
        this.anio = anio;
        this.clienteAsociado = cliente;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Cliente getCliente() {
        return clienteAsociado;
    }

    public void setCliente(Cliente cliente) {
        this.clienteAsociado = cliente;
    }

    public String getMarca() {
        return marca;
    }

    public String getColor() {
        return color;
    }

    public String getNumeroMarco() {
        return numeroMarco;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNumeroMarco(String numeroMarco) {
        this.numeroMarco = numeroMarco;
    }

    public void setAno(int ano) {
        this.anio = ano;
    }

    @Override
    public String toString() {
        return "Cicla{" +
                "clienteAsociado=" + clienteAsociado +
                ", anio=" + anio +
                ", tipo=" + tipo +
                ", numeroMarco='" + numeroMarco + '\'' +
                ", color='" + color + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }

    public TipoCicla getTipo() {
        return tipo;
    }

    public void setTipo(TipoCicla tipo) {
        this.tipo = tipo;
    }
}

