package model;

import java.util.Map;

/**
 * Representa un piloto de F1.
 * Hereda de Competidor y aplica polimorfismo en estadísticas.
 */
/**
 * Representa un piloto de F1.
 * Hereda de Competidor y aplica polimorfismo en estadísticas.
 */
public class Piloto extends Competidor {
    /** ID único del piloto. */
    private final int id;
    /** Equipo al que pertenece el piloto. */
    private final Equipo equipo;
    /** Número de carrera del piloto. */
    private final int numero;
    /** Abreviatura del nombre del piloto (ej: "VER"). */
    private final String abreviatura;
    /** Número de carreras disputadas en su carrera. */
    private final int carrerasDisputadas;
    /** Número total de carreras ganadas. */
    private final Integer carrerasGanadas;
    /** Número total de podios alcanzados. */
    private final Integer podios;
    /** Número total de poles position obtenidas. */
    private final Integer poles;
    /** Número total de vueltas rápidas logradas. */
    private final Integer vueltasRapidas;
    /** Mapa de posiciones por carrera (clave: nombre GP, valor: posición o estado). */
    private final Map<String, String> posicionesPorCarrera;
    /** Nacionalidad del piloto. */
    private final String nacionalidad;
    /** Edad del piloto. */
    private final int edad;
    /** Información de la temporada actual para el piloto. */
    private final TemporadaActual temporadaActual;

    /**
     * Constructor para un piloto de F1.
     * @param id ID único del piloto.
     * @param nombreCompleto Nombre completo del piloto.
     * @param campeonatosGanados Número de campeonatos ganados.
     * @param puntos2024 Puntos obtenidos en la temporada 2024.
     * @param equipo Equipo al que pertenece.
     * @param numero Número de carrera del piloto.
     * @param abreviatura Abreviatura del piloto.
     * @param carrerasDisputadas Total de carreras disputadas.
     * @param carrerasGanadas Total de carreras ganadas.
     * @param podios Total de podios alcanzados.
     * @param poles Total de poles position.
     * @param vueltasRapidas Total de vueltas rápidas.
     * @param posicionesPorCarrera Mapa de posiciones por carrera.
     * @param nacionalidad Nacionalidad del piloto.
     * @param edad Edad del piloto.
     * @param temporadaActual Información de la temporada actual.
     */
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

    /**
     * Obtiene el ID único del piloto.
     * @return ID del piloto.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el equipo al que pertenece el piloto.
     * @return Equipo del piloto.
     */
    public Equipo getEquipo() {
        return equipo;
    }

    /**
     * Obtiene el número de carrera del piloto.
     * @return Número del piloto.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtiene la abreviatura del piloto.
     * @return Abreviatura.
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * Obtiene el número total de carreras disputadas.
     * @return Carreras disputadas.
     */
    public int getCarrerasDisputadas() {
        return carrerasDisputadas;
    }

    /**
     * Obtiene el número total de carreras ganadas.
     * @return Carreras ganadas.
     */
    public Integer getCarrerasGanadas() {
        return carrerasGanadas;
    }

    /**
     * Obtiene el número total de podios alcanzados.
     * @return Podios alcanzados.
     */
    public Integer getPodios() {
        return podios;
    }

    /**
     * Obtiene el número total de poles position.
     * @return Poles position.
     */
    public Integer getPoles() {
        return poles;
    }

    /**
     * Obtiene el número total de vueltas rápidas logradas.
     * @return Vueltas rápidas.
     */
    public Integer getVueltasRapidas() {
        return vueltasRapidas;
    }

    /**
     * Obtiene el mapa de posiciones por carrera.
     * @return Mapa de posiciones por carrera.
     */
    public Map<String, String> getPosicionesPorCarrera() {
        return posicionesPorCarrera;
    }

    /**
     * Obtiene la nacionalidad del piloto.
     * @return Nacionalidad.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Obtiene la edad del piloto.
     * @return Edad.
     */
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

