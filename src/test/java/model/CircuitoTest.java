package model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CircuitoTest {
    @Test
    void testCircuitoConstructorAndGetters() {
        Circuito circuito = new Circuito(1, "Monza", "Italia", LocalDate.of(2024, 9, 1), LocalDate.of(2024, 8, 31));
        assertEquals(1, circuito.getId());
        assertEquals("Monza", circuito.getNombre());
        assertEquals("Italia", circuito.getPais());
        assertEquals(LocalDate.of(2024, 9, 1), circuito.getFechaCarreraPrincipal());
        assertEquals(LocalDate.of(2024, 8, 31), circuito.getFechaCarreraSprint());
    }
}
