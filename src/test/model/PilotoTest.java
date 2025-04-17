package model;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PilotoTest {
    @Test
    void testPilotoConstructorAndGetters() {
        Equipo equipo = new Equipo(1, "Red Bull Racing", 5, 200.0, "Christian Horner", "Reino Unido", "Honda", 10, java.time.LocalDate.of(2024, 4, 1));
        Piloto piloto = new Piloto(33, "Max Verstappen", 3, 150.0, equipo, 1, "VER", 100, 50, 80, 30, 20, Map.of("Australia", "1", "Bahrein", "2"), "Holandesa", 27, null);
        assertEquals(33, piloto.getId());
        assertEquals(equipo, piloto.getEquipo());
        assertEquals(1, piloto.getNumero());
        assertEquals("VER", piloto.getAbreviatura());
        assertEquals(100, piloto.getCarrerasDisputadas());
        assertEquals(50, piloto.getCarrerasGanadas());
        assertEquals(80, piloto.getPodios());
        assertEquals(30, piloto.getPoles());
        assertEquals(20, piloto.getVueltasRapidas());
        assertEquals("Holandesa", piloto.getNacionalidad());
        assertEquals(27, piloto.getEdad());
        assertEquals("1", piloto.getPosicionesPorCarrera().get("Australia"));
    }
}
