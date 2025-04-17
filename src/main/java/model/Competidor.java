package model;

/**
 * Clase base para participantes en el campeonato (equipos y pilotos).
 * Aplica encapsulación en atributos comunes.
 */
public abstract class Competidor {
    private final String nombreCompleto;
    private final int campeonatosGanados;
    private final double puntos2024;

    public Competidor(String nombreCompleto, int campeonatosGanados, double puntos2024) {
        this.nombreCompleto = nombreCompleto;
        this.campeonatosGanados = campeonatosGanados;
        this.puntos2024 = puntos2024;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getCampeonatosGanados() {
        return campeonatosGanados;
    }

    public double getPuntos2024() {
        return puntos2024;
    }
}
