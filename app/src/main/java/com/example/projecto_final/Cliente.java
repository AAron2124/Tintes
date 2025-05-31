package com.example.projecto_final;

public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private String fechaAplicacion;
    private String tonoTinte;
    private String sugerencias;
    private String complemento;
    private String decolorante;
    private String fechaRetoque;

    public Cliente() {
        // Constructor vacío necesario para ciertos usos (como formularios)
    }

    public Cliente(int id, String nombre, String telefono, String fechaAplicacion,
                   String tonoTinte, String sugerencias, String complemento,
                   String decolorante, String fechaRetoque) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaAplicacion = fechaAplicacion;
        this.tonoTinte = tonoTinte;
        this.sugerencias = sugerencias;
        this.complemento = complemento;
        this.decolorante = decolorante;
        this.fechaRetoque = fechaRetoque;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(String fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getTonoTinte() {
        return tonoTinte;
    }

    public void setTonoTinte(String tonoTinte) {
        this.tonoTinte = tonoTinte;
    }

    public String getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(String sugerencias) {
        this.sugerencias = sugerencias;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getDecolorante() {
        return decolorante;
    }

    public void setDecolorante(String decolorante) {
        this.decolorante = decolorante;
    }

    public String getFechaRetoque() {
        return fechaRetoque;
    }

    public void setFechaRetoque(String fechaRetoque) {
        this.fechaRetoque = fechaRetoque;
    }

    @Override
    public String toString() {
        return nombre + " - " + tonoTinte;
    }
}
