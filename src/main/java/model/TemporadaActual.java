package model;

import java.time.LocalDate;

/**
 * Modelo de la temporada actual de un piloto.
 */
public class TemporadaActual {
    private final int posicionCampeonato;
    private final int carrerasCompletadas;
    private final int carrerasPendientes;
    private final String proximaCarrera;
    private final LocalDate fechaProximaCarrera;

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

    public int getPosicionCampeonato() {
        return posicionCampeonato;
    }

    public int getCarrerasCompletadas() {
        return carrerasCompletadas;
    }

    public int getCarrerasPendientes() {
        return carrerasPendientes;
    }

    public String getProximaCarrera() {
        return proximaCarrera;
    }

    public LocalDate getFechaProximaCarrera() {
        return fechaProximaCarrera;
    }
}

