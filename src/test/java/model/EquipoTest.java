package model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class EquipoTest {
    @Test
    void testEquipoConstructorAndGetters() {
        Equipo equipo = new Equipo(1, "Test Team", 2, 123.5, "Director", "España", "Ferrari", 3, LocalDate.of(2024, 5, 1));
        assertEquals(1, equipo.getId());
        assertEquals("Test Team", equipo.getNombreCompleto());
        assertEquals(2, equipo.getCampeonatosGanados());
        assertEquals(123.5, equipo.getPuntos2024());
        assertEquals("Director", equipo.getDirectorGeneral());
        assertEquals("España", equipo.getPaisOrigen());
        assertEquals("Ferrari", equipo.getProveedorMotor());
        assertEquals(3, equipo.getCarrerasGanadas2024());
        assertEquals(LocalDate.of(2024, 5, 1), equipo.getUltimaVictoria());
    }

    @Test
    void testAgregarPiloto() {
        Equipo equipo = new Equipo(2, "Test Team 2", 0, 0.0, "Director", "Italia", "Mercedes", 0, null);
        Piloto piloto = new Piloto(10, "Piloto Test", 0, 0.0, equipo, 99, "TST", 0, 0, 0, 0, 0, java.util.Map.of(), "Italiana", 25, null);
        equipo.agregarPiloto(piloto);
        assertTrue(equipo.getPilotos().contains(piloto));
    }
}
