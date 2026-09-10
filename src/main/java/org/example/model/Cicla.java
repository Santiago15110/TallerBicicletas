package org.example.model;

public class Cicla {
    private String marca;
    private String color;
    private String numeroMarco;
    private int  anio;
    private Cliente cliente;


    public Cicla(String marca, String color, String numeroMarco, int ano, Cliente cliente) {
        this.marca = marca;
        this.color = color;
        this.numeroMarco = numeroMarco;
        this.anio = ano;
        this.cliente = cliente;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
}
