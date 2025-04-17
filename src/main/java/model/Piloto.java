package model;

import java.util.Map;

/**
 * Representa un piloto de F1.
 * Hereda de Competidor y aplica polimorfismo en estadísticas.
 */
public class Piloto extends Competidor {
    private final int id; // ID único del piloto
    private final Equipo equipo;
    private final int numero;
    private final String abreviatura;
    private final int carrerasDisputadas;
    private final Integer carrerasGanadas;
    private final Integer podios;
    private final Integer poles;
    private final Integer vueltasRapidas;
    private final Map<String, String> posicionesPorCarrera;
    private final String nacionalidad;
    private final int edad;
    private final TemporadaActual temporadaActual;

    public Piloto(int id,
                  String nombreCompleto,
                  int campeonatosGanados,
                  double puntos2024,
                  Equipo equipo,
                  int numero,
                  String abreviatura,
                  int carrerasDisputadas,
                  Integer carrerasGanadas,
                  Integer podios,
                  Integer poles,
                  Integer vueltasRapidas,
                  Map<String, String> posicionesPorCarrera,
                  String nacionalidad,
                  int edad,
                  TemporadaActual temporadaActual) {
        super(nombreCompleto, campeonatosGanados, puntos2024);
        this.id = id;
        this.equipo = equipo;
        this.numero = numero;
        this.abreviatura = abreviatura;
        this.carrerasDisputadas = carrerasDisputadas;
        this.carrerasGanadas = carrerasGanadas;
        this.podios = podios;
        this.poles = poles;
        this.vueltasRapidas = vueltasRapidas;
        this.posicionesPorCarrera = posicionesPorCarrera;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.temporadaActual = temporadaActual;
    }

    public int getId() {
        return id;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public int getNumero() {
        return numero;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public int getCarrerasDisputadas() {
        return carrerasDisputadas;
    }

    public Integer getCarrerasGanadas() {
        return carrerasGanadas;
    }

    public Integer getPodios() {
        return podios;
    }

    public Integer getPoles() {
        return poles;
    }

    public Integer getVueltasRapidas() {
        return vueltasRapidas;
    }

    public Map<String, String> getPosicionesPorCarrera() {
        return posicionesPorCarrera;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public int getEdad() {
        return edad;
    }

    public TemporadaActual getTemporadaActual() {
        return temporadaActual;
    }


    @Override
    public String toString() {
        return "Piloto{nombre='" + getNombreCompleto() + "', equipo='" + equipo.getNombreCompleto() + "', puntos=" + getPuntos2024()+ "}";
    }
}

