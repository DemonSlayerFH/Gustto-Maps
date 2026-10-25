package com.example.gustto;

/** Modelo de los datos; no usa Firebase ni SQLite. */
public class Restaurante {
    private final int id;
    private final String nombre, categoria, descripcion, direccion, horario;
    private final double latitud, longitud;

    public Restaurante(int id, String nombre, String categoria, String descripcion,
                       String direccion, String horario, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public String getDescripcion() { return descripcion; }
    public String getDireccion() { return direccion; }
    public String getHorario() { return horario; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }
}
