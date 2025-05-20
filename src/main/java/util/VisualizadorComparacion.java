package util;

import model.Equipo;
import model.Piloto;
import java.util.List;

public class VisualizadorComparacion {
    private static final int BARRA_LONGITUD = 30;
    private static final String BARRA_LLENA = "█";
    private static final String BARRA_VACIA = "░";
    private static final String FLECHA_ARRIBA = "↑";
    private static final String FLECHA_ABAJO = "↓";
    private static final String FLECHA_IGUAL = "→";

    public static void mostrarComparacionEquipos(Equipo equipo1, Equipo equipo2) {
        System.out.println("\n=== COMPARACIÓN VISUAL DE EQUIPOS ===");
        System.out.println(equipo1.getNombreCompleto() + " vs " + equipo2.getNombreCompleto());
        System.out.println("=====================================");

        // Comparar puntos
        double puntos1 = equipo1.getPuntos2024();
        double puntos2 = equipo2.getPuntos2024();
        mostrarBarraComparativa("Puntos", puntos1, puntos2);

        // Comparar victorias
        int victorias1 = equipo1.getCarrerasGanadas2024() != null ? equipo1.getCarrerasGanadas2024() : 0;
        int victorias2 = equipo2.getCarrerasGanadas2024() != null ? equipo2.getCarrerasGanadas2024() : 0;
        mostrarBarraComparativa("Victorias", victorias1, victorias2);

        // Comparar pilotos
        System.out.println("\n=== PILOTOS ===");
        for (Piloto piloto1 : equipo1.getPilotos()) {
            for (Piloto piloto2 : equipo2.getPilotos()) {
                System.out.println("\n" + piloto1.getNombreCompleto() + " vs " + piloto2.getNombreCompleto());
                mostrarBarraComparativa("Puntos", piloto1.getPuntos2024(), piloto2.getPuntos2024());
                mostrarBarraComparativa("Victorias", piloto1.getCarrerasGanadas() != null ? piloto1.getCarrerasGanadas() : 0, 
                                      piloto2.getCarrerasGanadas() != null ? piloto2.getCarrerasGanadas() : 0);
                mostrarBarraComparativa("Podios", piloto1.getPodios() != null ? piloto1.getPodios() : 0, 
                                      piloto2.getPodios() != null ? piloto2.getPodios() : 0);
                mostrarBarraComparativa("Vueltas Rápidas", piloto1.getVueltasRapidas() != null ? piloto1.getVueltasRapidas() : 0, 
                                      piloto2.getVueltasRapidas() != null ? piloto2.getVueltasRapidas() : 0);
            }
        }
    }

    public static void mostrarComparacionPilotos(Piloto piloto1, Piloto piloto2) {
        System.out.println("\n=== COMPARACIÓN VISUAL DE PILOTOS ===");
        System.out.println(piloto1.getNombreCompleto() + " vs " + piloto2.getNombreCompleto());
        System.out.println("=====================================");

        // Comparar puntos
        double puntos1 = piloto1.getPuntos2024();
        double puntos2 = piloto2.getPuntos2024();
        mostrarBarraComparativa("Puntos", puntos1, puntos2);

        // Comparar victorias
        int victorias1 = piloto1.getCarrerasGanadas() != null ? piloto1.getCarrerasGanadas() : 0;
        int victorias2 = piloto2.getCarrerasGanadas() != null ? piloto2.getCarrerasGanadas() : 0;
        mostrarBarraComparativa("Victorias", victorias1, victorias2);

        // Comparar podios
        int podios1 = piloto1.getPodios() != null ? piloto1.getPodios() : 0;
        int podios2 = piloto2.getPodios() != null ? piloto2.getPodios() : 0;
        mostrarBarraComparativa("Podios", podios1, podios2);

        // Comparar vueltas rápidas
        int vueltasRapidas1 = piloto1.getVueltasRapidas() != null ? piloto1.getVueltasRapidas() : 0;
        int vueltasRapidas2 = piloto2.getVueltasRapidas() != null ? piloto2.getVueltasRapidas() : 0;
        mostrarBarraComparativa("Vueltas Rápidas", vueltasRapidas1, vueltasRapidas2);
    }

    private static void mostrarBarraComparativa(String etiqueta, double valor1, double valor2) {
        double max = Math.max(valor1, valor2);
        if (max == 0) max = 1; // Evitar división por cero

        int barra1 = (int) ((valor1 / max) * BARRA_LONGITUD);
        int barra2 = (int) ((valor2 / max) * BARRA_LONGITUD);

        String flecha1 = obtenerFlecha(valor1, valor2);
        String flecha2 = obtenerFlecha(valor2, valor1);

        System.out.printf("%-20s: %6.1f %s %s%n", etiqueta, valor1, flecha1, BARRA_LLENA.repeat(barra1) + BARRA_VACIA.repeat(BARRA_LONGITUD - barra1));
        System.out.printf("%-20s: %6.1f %s %s%n", "", valor2, flecha2, BARRA_LLENA.repeat(barra2) + BARRA_VACIA.repeat(BARRA_LONGITUD - barra2));
    }

    private static String obtenerFlecha(double valor1, double valor2) {
        if (valor1 > valor2) return FLECHA_ARRIBA;
        if (valor1 < valor2) return FLECHA_ABAJO;
        return FLECHA_IGUAL;
    }
} 