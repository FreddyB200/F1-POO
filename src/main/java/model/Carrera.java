package model;

import java.time.LocalDate;
import java.util.Map;

public class Carrera {
    private final String nombre;
    private final Circuito circuito;
    private final LocalDate fecha;
    private final String tipo; // Ej: "Principal", "Sprint", etc.
    // Mapa de piloto a posición (ej: "1", "2", "DNF", etc.)
    private final Map<Piloto, String> posicionesPilotos;
    // Mapa de piloto a puntos obtenidos en esta carrera
    private final Map<Piloto, Integer> puntosPilotos;
    // Mapa de equipo a puntos obtenidos en esta carrera
    private final Map<Equipo, Integer> puntosEquipos;

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

    public String getNombre() { return nombre; }
    public Circuito getCircuito() { return circuito; }
    public LocalDate getFecha() { return fecha; }
    public String getTipo() { return tipo; }
    public Map<Piloto, String> getPosicionesPilotos() { return posicionesPilotos; }
    public Map<Piloto, Integer> getPuntosPilotos() { return puntosPilotos; }
    public Map<Equipo, Integer> getPuntosEquipos() { return puntosEquipos; }
}

