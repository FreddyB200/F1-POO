package model;


import java.time.LocalDate;

/**
 * Representa un equipo de F1.
 * Hereda de Competidor.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un equipo de F1.
 * Hereda de Competidor e incluye información específica del equipo y sus pilotos.
 */
public class Equipo extends Competidor {
    /** ID único del equipo. */
    private final int id;
    /** Nombre del director general del equipo. */
    private final String directorGeneral;
    /** País de origen del equipo. */
    private final String paisOrigen;
    /** Proveedor de motor del equipo. */
    private final String proveedorMotor;
    /** Número de carreras ganadas en 2024. */
    private final Integer carrerasGanadas2024;
    /** Fecha de la última victoria del equipo. */
    private final LocalDate ultimaVictoria;
    /** Lista de pilotos asociados a este equipo. */
    private final List<Piloto> pilotos = new ArrayList<>();

    /**
     * Constructor para un equipo de F1.
     * @param id ID único del equipo.
     * @param nombreEquipo Nombre completo del equipo.
     * @param campeonatosGanados Número de campeonatos ganados.
     * @param puntos2024 Puntos obtenidos en la temporada 2024.
     * @param directorGeneral Nombre del director general.
     * @param paisOrigen País de origen del equipo.
     * @param proveedorMotor Proveedor de motor del equipo.
     * @param carrerasGanadas2024 Número de carreras ganadas en 2024.
     * @param ultimaVictoria Fecha de la última victoria.
     */
    public Equipo(int id,
                  String nombreEquipo,
                  int campeonatosGanados,
                  double puntos2024,
                  String directorGeneral,
                  String paisOrigen,
                  String proveedorMotor,
                  Integer carrerasGanadas2024,
                  LocalDate ultimaVictoria) {
        super(nombreEquipo, campeonatosGanados, puntos2024);
        this.id = id;
        this.directorGeneral = directorGeneral;
        this.paisOrigen = paisOrigen;
        this.proveedorMotor = proveedorMotor;
        this.carrerasGanadas2024 = carrerasGanadas2024;
        this.ultimaVictoria = ultimaVictoria;
    }

    /**
     * Obtiene el ID único del equipo.
     * @return ID del equipo.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del director general del equipo.
     * @return Director general.
     */
    public String getDirectorGeneral() {
        return directorGeneral;
    }
    // NUEVO: Métodos para gestionar pilotos
    /**
     * Agrega un piloto a la lista de pilotos del equipo si no está ya presente.
     * @param piloto Piloto a agregar.
     */
    public void agregarPiloto(Piloto piloto) {
        if (!pilotos.contains(piloto)) {
            pilotos.add(piloto);
        }
    }
    /**
     * Obtiene la lista de pilotos asociados a este equipo.
     * @return Lista de pilotos.
     */
    public List<Piloto> getPilotos() {
        return pilotos;
    }

    /**
     * Obtiene el país de origen del equipo.
     * @return País de origen.
     */
    public String getPaisOrigen() {
        return paisOrigen;
    }

    /**
     * Obtiene el proveedor de motor del equipo.
     * @return Proveedor de motor.
     */
    public String getProveedorMotor() {
        return proveedorMotor;
    }

    /**
     * Obtiene el número de carreras ganadas en 2024.
     * @return Carreras ganadas en 2024.
     */
    public Integer getCarrerasGanadas2024() {
        return carrerasGanadas2024;
    }

    /**
     * Obtiene la fecha de la última victoria del equipo.
     * @return Fecha de la última victoria.
     */
    public LocalDate getUltimaVictoria() {
        return ultimaVictoria;
    }
}

