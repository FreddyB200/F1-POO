package model;

/**
 * Clase abstracta base para los participantes del campeonato de F1 (equipos y pilotos).
 * Proporciona atributos y métodos comunes, como nombre, campeonatos ganados y puntos obtenidos en 2024.
 * Aplica encapsulación en atributos comunes.
 */
public abstract class Competidor {
    /** Nombre completo del participante (equipo o piloto). */
    private final String nombreCompleto;
    /** Número de campeonatos ganados por el participante. */
    private final int campeonatosGanados;
    /** Puntos obtenidos en la temporada 2024. */
    private final double puntos2024;

    /**
     * Constructor base para un competidor.
     * @param nombreCompleto Nombre completo del participante.
     * @param campeonatosGanados Número de campeonatos ganados.
     * @param puntos2024 Puntos obtenidos en la temporada 2024.
     */
    public Competidor(String nombreCompleto, int campeonatosGanados, double puntos2024) {
        this.nombreCompleto = nombreCompleto;
        this.campeonatosGanados = campeonatosGanados;
        this.puntos2024 = puntos2024;
    }

    /**
     * Obtiene el nombre completo del competidor.
     * @return Nombre completo.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Obtiene el número de campeonatos ganados.
     * @return Campeonatos ganados.
     */
    public int getCampeonatosGanados() {
        return campeonatosGanados;
    }

    /**
     * Obtiene los puntos obtenidos en la temporada 2024.
     * @return Puntos de la temporada 2024.
     */
    public double getPuntos2024() {
        return puntos2024;
    }
}
