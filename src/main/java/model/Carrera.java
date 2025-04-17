package model;

import java.time.LocalDate;
import java.util.Map;

/**
 * Representa una carrera de la temporada de F1.
 * Incluye información sobre el circuito, fecha, tipo de carrera y resultados de pilotos y equipos.
 */
public class Carrera {
    /** Nombre de la carrera (ej: "Gran Premio de Mónaco"). */
    private final String nombre;
    /** Circuito donde se disputa la carrera. */
    private final Circuito circuito;
    /** Fecha en la que se disputa la carrera. */
    private final LocalDate fecha;
    /** Tipo de carrera (ej: "Principal", "Sprint"). */
    private final String tipo;
    /**
     * Mapa que asocia cada piloto con su posición final en la carrera (ej: "1", "2", "DNF").
     */
    private final Map<Piloto, String> posicionesPilotos;
    /**
     * Mapa que asocia cada piloto con los puntos obtenidos en esta carrera.
     */
    private final Map<Piloto, Integer> puntosPilotos;
    /**
     * Mapa que asocia cada equipo con los puntos obtenidos en esta carrera.
     */
    private final Map<Equipo, Integer> puntosEquipos;

    /**
     * Constructor para una carrera de F1.
     * @param nombre Nombre de la carrera.
     * @param circuito Circuito donde se disputa.
     * @param fecha Fecha de la carrera.
     * @param tipo Tipo de carrera (ej: "Principal", "Sprint").
     * @param posicionesPilotos Mapa de posiciones finales por piloto.
     * @param puntosPilotos Mapa de puntos obtenidos por piloto.
     * @param puntosEquipos Mapa de puntos obtenidos por equipo.
     */
    public Carrera(String nombre, Circuito circuito, LocalDate fecha, String tipo,
                   Map<Piloto, String> posicionesPilotos,
                   Map<Piloto, Integer> puntosPilotos,
                   Map<Equipo, Integer> puntosEquipos) {
        this.nombre = nombre;
        this.circuito = circuito;
        this.fecha = fecha;
        this.tipo = tipo;
        this.posicionesPilotos = posicionesPilotos;
        this.puntosPilotos = puntosPilotos;
        this.puntosEquipos = puntosEquipos;
    }

    /**
     * Obtiene el nombre de la carrera.
     * @return Nombre de la carrera.
     */
    public String getNombre() { return nombre; }
    /**
     * Obtiene el circuito donde se disputa la carrera.
     * @return Circuito de la carrera.
     */
    public Circuito getCircuito() { return circuito; }
    /**
     * Obtiene la fecha de la carrera.
     * @return Fecha de la carrera.
     */
    public LocalDate getFecha() { return fecha; }
    /**
     * Obtiene el tipo de carrera (ej: "Principal", "Sprint").
     * @return Tipo de carrera.
     */
    public String getTipo() { return tipo; }
    /**
     * Obtiene el mapa de posiciones finales por piloto.
     * @return Mapa de posiciones de pilotos.
     */
    public Map<Piloto, String> getPosicionesPilotos() { return posicionesPilotos; }
    /**
     * Obtiene el mapa de puntos obtenidos por piloto.
     * @return Mapa de puntos por piloto.
     */
    public Map<Piloto, Integer> getPuntosPilotos() { return puntosPilotos; }
    /**
     * Obtiene el mapa de puntos obtenidos por equipo.
     * @return Mapa de puntos por equipo.
     */
    public Map<Equipo, Integer> getPuntosEquipos() { return puntosEquipos; }
}


