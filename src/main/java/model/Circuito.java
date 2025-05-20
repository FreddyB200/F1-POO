package model;

import java.time.LocalDate;

/**
 * Representa un circuito de la temporada de F1.
 * Incluye información sobre el nombre, país y fechas de las carreras principales y sprint.
 */
public class Circuito {
    /** ID único del circuito. */
    private final int id;
    /** Nombre del circuito. */
    private final String nombre;
    /** País donde se ubica el circuito. */
    private final String pais;
    /** Ciudad donde se ubica el circuito. */
    private final String ciudad;
    /** Longitud del circuito en kilómetros. */
    private final double longitud;
    /** Número de vueltas de la carrera. */
    private final int vueltas;
    /** Fecha de la carrera principal. */
    private final LocalDate fechaCarreraPrincipal;
    /** Fecha de la carrera sprint (puede ser null si no hay sprint). */
    private final LocalDate fechaCarreraSprint;

    /**
     * Constructor para un circuito de F1.
     * @param id ID único del circuito.
     * @param nombre Nombre del circuito.
     * @param pais País donde se ubica el circuito.
     * @param ciudad Ciudad donde se ubica el circuito.
     * @param longitud Longitud del circuito en kilómetros.
     * @param vueltas Número de vueltas de la carrera.
     * @param fechaCarreraPrincipal Fecha de la carrera principal.
     * @param fechaCarreraSprint Fecha de la carrera sprint (puede ser null).
     */
    public Circuito(int id, String nombre, String pais, String ciudad, double longitud, int vueltas,
                   LocalDate fechaCarreraPrincipal, LocalDate fechaCarreraSprint) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.longitud = longitud;
        this.vueltas = vueltas;
        this.fechaCarreraPrincipal = fechaCarreraPrincipal;
        this.fechaCarreraSprint = fechaCarreraSprint;
    }

    /**
     * Obtiene el ID único del circuito.
     * @return ID del circuito.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del circuito.
     * @return Nombre del circuito.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el país donde se ubica el circuito.
     * @return País del circuito.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Obtiene la ciudad donde se ubica el circuito.
     * @return Ciudad del circuito.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Obtiene la longitud del circuito en kilómetros.
     * @return Longitud del circuito.
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Obtiene el número de vueltas de la carrera.
     * @return Número de vueltas.
     */
    public int getVueltas() {
        return vueltas;
    }

    /**
     * Obtiene la fecha de la carrera principal.
     * @return Fecha de la carrera principal.
     */
    public LocalDate getFechaCarreraPrincipal() {
        return fechaCarreraPrincipal;
    }

    /**
     * Obtiene la fecha de la carrera sprint (puede ser null).
     * @return Fecha de la carrera sprint.
     */
    public LocalDate getFechaCarreraSprint() {
        return fechaCarreraSprint;
    }
}