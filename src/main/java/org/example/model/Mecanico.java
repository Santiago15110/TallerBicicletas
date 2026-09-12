package org.example.model;

public class Mecanico {

    private String codigo;
    private String nombre;
    private TipoEspecialidad tipoEspecialidad;

    public Mecanico(String codigo, String nombre, TipoEspecialidad tipoEspecialidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipoEspecialidad= tipoEspecialidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
