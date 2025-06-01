package com.example.projecto_final;

public class InventarioItem {
    private int id;
    private String nombre;
    private String codigoColor;
    private int cantidad;

    public InventarioItem() {
    }

    public InventarioItem(int id, String nombre, String codigoColor, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.codigoColor = codigoColor;
        this.cantidad = cantidad;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoColor() {
        return codigoColor;
    }

    public void setCodigoColor(String codigoColor) {
        this.codigoColor = codigoColor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

