package org.example.model;

public class Cicla {
    private String marca;
    private String color;
    private String numeroMarco;
    private int  anio;


    public Cicla(String marca, String color, String numeroMarco, int ano) {
        this.marca = marca;
        this.color = color;
        this.numeroMarco = numeroMarco;
        this.anio = ano;
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

    public int getAno() {
        return anio;
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
