package model;


import java.time.LocalDate;

/**
 * Representa un equipo de F1.
 * Hereda de Competidor.
 */
import java.util.ArrayList;
import java.util.List;

public class Equipo extends Competidor {
    private final int id; // ID único del equipo
    private final String directorGeneral;
    private final String paisOrigen;
    private final String proveedorMotor;
    private final Integer carrerasGanadas2024;
    private final LocalDate ultimaVictoria;
    private final List<Piloto> pilotos = new ArrayList<>(); // NUEVO

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

    public int getId() {
        return id;
    }

    public String getDirectorGeneral() {
        return directorGeneral;
    }
    // NUEVO: Métodos para gestionar pilotos
    public void agregarPiloto(Piloto piloto) {
        if (!pilotos.contains(piloto)) {
            pilotos.add(piloto);
        }
    }
    public List<Piloto> getPilotos() {
        return pilotos;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public String getProveedorMotor() {
        return proveedorMotor;
    }

    public Integer getCarrerasGanadas2024() {
        return carrerasGanadas2024;
    }

    public LocalDate getUltimaVictoria() {
        return ultimaVictoria;
    }
}

