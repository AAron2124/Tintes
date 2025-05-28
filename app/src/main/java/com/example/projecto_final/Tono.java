package com.example.projecto_final;

public class Tono {
    private int id;
    private String nombre;
    private String codigoColor;

    public Tono(int id, String nombre, String codigoColor) {
        this.id = id;
        this.nombre = nombre;
        this.codigoColor = codigoColor;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCodigoColor() { return codigoColor; }
}
