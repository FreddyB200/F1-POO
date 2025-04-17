package model;

import java.time.LocalDate;

public class Circuito {
    private final int id; // ID único del circuito
    private final String nombre;
    private final String pais;
    private final LocalDate fechaCarreraPrincipal;
    private final LocalDate fechaCarreraSprint; // puede ser null si no hay sprint

    public Circuito(int id, String nombre, String pais, LocalDate fechaCarreraPrincipal, LocalDate fechaCarreraSprint) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fechaCarreraPrincipal = fechaCarreraPrincipal;
        this.fechaCarreraSprint = fechaCarreraSprint;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public LocalDate getFechaCarreraPrincipal() {
        return fechaCarreraPrincipal;
    }

    public LocalDate getFechaCarreraSprint() {
        return fechaCarreraSprint;
    }
}