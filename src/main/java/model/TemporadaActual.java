package model;

import java.time.LocalDate;

/**
 * Modelo de la temporada actual de un piloto.
 */
/**
 * Modelo de la temporada actual de un piloto.
 * Incluye información relevante como posición en el campeonato, carreras completadas y próximas carreras.
 */
public class TemporadaActual {
    /** Posición actual en el campeonato de pilotos. */
    private final int posicionCampeonato;
    /** Número de carreras completadas en la temporada. */
    private final int carrerasCompletadas;
    /** Número de carreras pendientes en la temporada. */
    private final int carrerasPendientes;
    /** Nombre de la próxima carrera. */
    private final String proximaCarrera;
    /** Fecha de la próxima carrera. */
    private final LocalDate fechaProximaCarrera;

    /**
     * Constructor para el modelo de temporada actual de un piloto.
     * @param posicionCampeonato Posición en el campeonato de pilotos.
     * @param carrerasCompletadas Número de carreras completadas.
     * @param carrerasPendientes Número de carreras pendientes.
     * @param proximaCarrera Nombre de la próxima carrera.
     * @param fechaProximaCarrera Fecha de la próxima carrera.
     */
    public TemporadaActual(int posicionCampeonato,
                           int carrerasCompletadas,
                           int carrerasPendientes,
                           String proximaCarrera,
                           LocalDate fechaProximaCarrera) {
        this.posicionCampeonato = posicionCampeonato;
        this.carrerasCompletadas = carrerasCompletadas;
        this.carrerasPendientes = carrerasPendientes;
        this.proximaCarrera = proximaCarrera;
        this.fechaProximaCarrera = fechaProximaCarrera;
    }

    /**
     * Obtiene la posición actual en el campeonato de pilotos.
     * @return Posición en el campeonato.
     */
    public int getPosicionCampeonato() {
        return posicionCampeonato;
    }

    /**
     * Obtiene el número de carreras completadas en la temporada.
     * @return Carreras completadas.
     */
    public int getCarrerasCompletadas() {
        return carrerasCompletadas;
    }

    /**
     * Obtiene el número de carreras pendientes en la temporada.
     * @return Carreras pendientes.
     */
    public int getCarrerasPendientes() {
        return carrerasPendientes;
    }

    /**
     * Obtiene el nombre de la próxima carrera.
     * @return Nombre de la próxima carrera.
     */
    public String getProximaCarrera() {
        return proximaCarrera;
    }

    /**
     * Obtiene la fecha de la próxima carrera.
     * @return Fecha de la próxima carrera.
     */
    public LocalDate getFechaProximaCarrera() {
        return fechaProximaCarrera;
    }
}

