package model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CarreraTest {
    @Test
    void testCarreraConstructorAndGetters() {
        Circuito circuito = new Circuito(1, "Silverstone", "Reino Unido", LocalDate.of(2024, 7, 7), null);
        Map<Piloto, String> posicionesPilotos = new HashMap<>();
        Map<Piloto, Integer> puntosPilotos = new HashMap<>();
        Map<Equipo, Integer> puntosEquipos = new HashMap<>();
        Carrera carrera = new Carrera("GP Silverstone", circuito, LocalDate.of(2024, 7, 7), "Principal", posicionesPilotos, puntosPilotos, puntosEquipos);
        assertEquals("GP Silverstone", carrera.getNombre());
        assertEquals(circuito, carrera.getCircuito());
        assertEquals(LocalDate.of(2024, 7, 7), carrera.getFecha());
        assertEquals("Principal", carrera.getTipo());
        assertEquals(posicionesPilotos, carrera.getPosicionesPilotos());
        assertEquals(puntosPilotos, carrera.getPuntosPilotos());
        assertEquals(puntosEquipos, carrera.getPuntosEquipos());
    }
}
