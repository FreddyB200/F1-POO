import data.CargadorDatos;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // Importar para formatear fechas
import java.util.Comparator; // Importar para comparador personalizado
import java.util.InputMismatchException; // Importar para manejo de errores de Scanner
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.Optional; // Importar para Optional

public class Main {

    // Formateador para fechas (opcional, pero mejora la legibilidad)
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        mostrarBanner();
    }

    private static void mostrarBanner(){
            String asciiArt = """
    ███████╗ ██████╗ ██████╗ ███╗   ███╗██╗   ██╗██╗     █████╗       ████╗
    ██╔════╝██╔═══██╗██╔══██╗████╗ ████║██║   ██║██║     ██╔══██╗       ██║
    █████╗  ██║   ██║██████╔╝██╔████╔██║██║   ██║██║     ███████║       ██║
    ██╔══╝  ██║   ██║██╔══██╗██║╚██╔╝██║██║   ██║██║     ██╔══██║       ██║
    ██║     ╚██████╔╝██║  ██║██║ ╚═╝ ██║╚██████╔╝███████╗██║  ██║       ██║
    ╚═╝      ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝     ██████
                                                                                                      
                     _______________|8888|_______________
                    |_____________ ,~~~~~~. _____________|
  _________         |_____________: mmmmmm :_____________|         _________
 / _______ \\   ,----|~~~~~~~~~~~,'\\ _...._ /`.~~~~~~~~~~~|----,   / _______ \\
| /       \\ |  |    |       |____|,d~    ~b.|____|       |    |  | /       \\ |
||         |-------------------\\-d.-~~~~~~-.b-/-------------------|         ||
||         | |8888 ....... _,===~/......... \\~===._         8888| |         ||
||         |=========_,===~~======._.=~~=._.======~~===._=========|         ||
||         | |888===~~ ...... //,, .`~~~~'. .,\\\\        ~~===888| |         ||
||        |===================,P'.::::::::.. `?,===================|        ||
||        |_________________,P'_::----------.._`?,_________________|        ||
`|        |-------------------~~~~~~~~~~~~~~~~~~-------------------|        |'
  \\_______/                                                        \\_______/
        """;
                System.out.println(asciiArt);

                mostrarMenu();
        }


    private static void mostrarMenu() {
        // Carga única de datos al inicio
        List<Equipo> equipos = CargadorDatos.cargarEquipos();
        List<Piloto> pilotos = CargadorDatos.cargarPilotos(); // CargadorDatos ya debe vincular pilotos a equipos
        List<Circuito> circuitos = CargadorDatos.cargarCircuitos();
        List<Carrera> carreras = CargadorDatos.cargarCarreras(pilotos, equipos, circuitos);

        Scanner scanner = new Scanner(System.in);
        int opcion = -1; // Inicializar con valor inválido

        String menuCompleto = """
                ==== MENÚ DE GESTIÓN F1 - TEMPORADA 2024 ====
                        == SELECCIONA UNA OPCION ==
                         
1. Ver posiciones de pilotos y escuderías en cada carrera
2. Ver puntos POR CADA carrera (NO acumulados)
3. Información general de un equipo
4. Información general de un piloto
5. Información general de un circuito
6. Listar todos los pilotos (con ID)
7. Listar todos los equipos (con ID)
8. Listar todos los circuitos (con ID)
0. Salir
        """;
        do {
            System.out.println(menuCompleto);

            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
                opcion = -1; // Asigna valor inválido para que no ejecute nada incorrecto
            } finally {
                scanner.nextLine(); // Siempre limpiar buffer, incluso si hay error
            }


            switch (opcion) {
                case 1 -> mostrarPosicionesPilotosEquipos(carreras);
                case 2 -> mostrarPuntosPorCarrera(carreras); // Método renombrado
                case 3 -> mostrarInfoEquipo(equipos, scanner); // Ya no necesita 'pilotos' si CargadorDatos vincula
                case 4 -> mostrarInfoPiloto(pilotos, scanner);
                case 5 -> mostrarInfoCircuito(circuitos, scanner); // Modificado para buscar por ID
                case 6 -> listarPilotos(pilotos);
                case 7 -> listarEquipos(equipos);
                case 8 -> listarCircuitos(circuitos); // Nuevo método llamado
                case 0 -> System.out.println("Saliendo...");
                case -1 -> {} // No hacer nada si la opción fue inválida por error de input
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    // Método corregido para ordenar posiciones correctamente
    private static void mostrarPosicionesPilotosEquipos(List<Carrera> carreras) {
        System.out.println("\n=== POSICIONES DE PILOTOS Y ESCUDERÍAS POR CARRERA ===");

        // Comparador para ordenar posiciones (números primero, luego DNF, DSQ, etc.)
        Comparator<Map.Entry<Piloto, String>> comparadorPosicion = (e1, e2) -> {
            String pos1 = e1.getValue();
            String pos2 = e2.getValue();
            boolean esNum1 = pos1.matches("\\d+");
            boolean esNum2 = pos2.matches("\\d+");

            if (esNum1 && esNum2) {
                return Integer.compare(Integer.parseInt(pos1), Integer.parseInt(pos2)); // Ambos números
            } else if (esNum1) {
                return -1; // Número antes que texto
            } else if (esNum2) {
                return 1; // Texto después que número
            } else {
                // Ambos texto (DNF, DSQ, etc.), ordena alfabéticamente
                // Podrías añadir lógica extra si quieres un orden específico para DNF/DSQ
                return pos1.compareTo(pos2);
            }
        };

        for (Carrera carrera : carreras) {
            System.out.println("\n> " + carrera.getNombre() + " - " + (carrera.getCircuito() != null ? carrera.getCircuito().getPais() : "") + " (" + carrera.getFecha().format(DATE_FORMATTER) + ")");
            System.out.println("  --- Pilotos ---");
            carrera.getPosicionesPilotos().entrySet().stream()
                    .sorted(comparadorPosicion) // Usar el comparador personalizado
                    .forEach(e -> System.out.printf("    Pos %-3s : %s (%s)\n", // Formato mejorado
                            e.getValue(),
                            e.getKey().getNombreCompleto(),
                            e.getKey().getAbreviatura()));

            System.out.println("  --- Puntos Equipos (en esta carrera) ---");
            // Ordenar equipos por puntos descendente para esta carrera
            carrera.getPuntosEquipos().entrySet().stream()
                    .filter(entry -> entry.getValue() > 0) // Opcional: mostrar solo equipos que puntuaron
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                    .forEach(e -> System.out.printf("    %s : %d puntos\n", e.getKey().getNombreCompleto(), e.getValue()));
        }
    }

    // Método renombrado y funcionalmente correcto (muestra puntos POR carrera)
    private static void mostrarPuntosPorCarrera(List<Carrera> carreras) {
        System.out.println("\n=== PUNTOS OBTENIDOS POR CARRERA ===");
        for (Carrera carrera : carreras) {
            System.out.println("\n> " + carrera.getNombre() + " (" + carrera.getFecha().format(DATE_FORMATTER) + ")");
            System.out.println("  --- Pilotos (Puntos en esta carrera) ---");
            carrera.getPuntosPilotos().entrySet().stream()
                    .filter(entry -> entry.getValue() > 0) // Mostrar solo pilotos que puntuaron
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // Ordenar por puntos desc
                    .forEach(e -> System.out.printf("    %s (%s) : %d puntos\n",
                            e.getKey().getNombreCompleto(),
                            e.getKey().getAbreviatura(),
                            e.getValue()));

            System.out.println("  --- Equipos (Puntos en esta carrera) ---");
            carrera.getPuntosEquipos().entrySet().stream()
                    .filter(entry -> entry.getValue() > 0) // Mostrar solo equipos que puntuaron
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // Ordenar por puntos desc
                    .forEach(e -> System.out.printf("    %s : %d puntos\n",
                            e.getKey().getNombreCompleto(),
                            e.getValue()));
        }
    }

    // Método corregido para mostrar pilotos del equipo y más info
    // Asume que CargadorDatos llama a equipo.agregarPiloto()
    private static void mostrarInfoEquipo(List<Equipo> equipos, Scanner scanner) {
        System.out.print("Ingrese el ID o nombre del equipo: ");
        String input = scanner.nextLine().trim();
        Equipo equipoEncontrado = null;
        try {
            int id = Integer.parseInt(input);
            equipoEncontrado = equipos.stream().filter(eq -> eq.getId() == id).findFirst().orElse(null);
        } catch (NumberFormatException e) {
            equipoEncontrado = equipos.stream().filter(eq -> eq.getNombreCompleto().equalsIgnoreCase(input)).findFirst().orElse(null);
        }

        if (equipoEncontrado != null) {
            System.out.println("\n--- Información del Equipo ---");
            System.out.println("ID: " + equipoEncontrado.getId());
            System.out.println("Nombre: " + equipoEncontrado.getNombreCompleto());
            System.out.println("Director: " + equipoEncontrado.getDirectorGeneral());
            System.out.println("País de origen: " + equipoEncontrado.getPaisOrigen());
            System.out.println("Proveedor de motor: " + equipoEncontrado.getProveedorMotor());
            System.out.println("Puntos 2024: " + equipoEncontrado.getPuntos2024());
            System.out.println("Campeonatos Históricos: " + equipoEncontrado.getCampeonatosGanados());
            // Mostrar Carreras Ganadas 2024 (manejando null)
            Integer ganadas2024 = equipoEncontrado.getCarrerasGanadas2024();
            System.out.println("Carreras Ganadas 2024: " + (ganadas2024 != null ? ganadas2024 : "0"));
            // Mostrar Última Victoria (manejando null)
            LocalDate ultimaVictoria = equipoEncontrado.getUltimaVictoria();
            System.out.println("Última Victoria: " + (ultimaVictoria != null ? ultimaVictoria.format(DATE_FORMATTER) : "N/A"));

            System.out.println("Pilotos 2024:");
            List<Piloto> pilotosDelEquipo = equipoEncontrado.getPilotos(); // Usa el método del modelo Equipo
            if (pilotosDelEquipo != null && !pilotosDelEquipo.isEmpty()) {
                pilotosDelEquipo.forEach(p -> System.out.printf("  - %s (#%d, %s)\n",
                        p.getNombreCompleto(), p.getNumero(), p.getAbreviatura()));
            } else {
                // Esto no debería ocurrir si CargadorDatos hizo bien la vinculación
                System.out.println("  (No se encontraron pilotos vinculados directamente en el objeto Equipo)");
            }
        } else {
            System.out.println("Equipo no encontrado.");
        }
    }

    // Corregido para usar getters correctos de Piloto
    private static void mostrarInfoPiloto(List<Piloto> pilotos, Scanner scanner) {
        System.out.print("Ingrese el ID, nombre o abreviatura del piloto: ");
        String input = scanner.nextLine().trim();
        Piloto pilotoEncontrado = null;
        try {
            int id = Integer.parseInt(input);
            pilotoEncontrado = pilotos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        } catch (NumberFormatException e) {
            // Buscar por nombre completo O abreviatura (case-insensitive)
            pilotoEncontrado = pilotos.stream()
                    .filter(p -> p.getNombreCompleto().equalsIgnoreCase(input) || p.getAbreviatura().equalsIgnoreCase(input))
                    .findFirst()
                    .orElse(null);
        }

        if (pilotoEncontrado != null) {
            System.out.println("\n--- Información del Piloto ---");
            System.out.println("ID: " + pilotoEncontrado.getId());
            System.out.println("Nombre completo: " + pilotoEncontrado.getNombreCompleto());
            System.out.println("Abreviatura: " + pilotoEncontrado.getAbreviatura());
            System.out.println("Número: " + pilotoEncontrado.getNumero());
            // Manejar posible equipo null (aunque no debería pasar con los datos cargados)
            System.out.println("Equipo 2024: " + (pilotoEncontrado.getEquipo() != null ? pilotoEncontrado.getEquipo().getNombreCompleto() : "N/A"));
            System.out.println("Nacionalidad: " + pilotoEncontrado.getNacionalidad());
            System.out.println("Edad: " + pilotoEncontrado.getEdad());
            System.out.println("Puntos 2024: " + pilotoEncontrado.getPuntos2024());
            System.out.println("Campeonatos Mundiales: " + pilotoEncontrado.getCampeonatosGanados());
            System.out.println("--- Estadísticas de Carrera (Históricas) ---");
            System.out.println("Participaciones en GPs: " + pilotoEncontrado.getCarrerasDisputadas());
            // Manejar null para Integer (aunque en CargadorDatos les dimos valor)
            System.out.println("Victorias: " + (pilotoEncontrado.getCarrerasGanadas() != null ? pilotoEncontrado.getCarrerasGanadas() : "0"));
            System.out.println("Podios: " + (pilotoEncontrado.getPodios() != null ? pilotoEncontrado.getPodios() : "0"));
            System.out.println("Poles: " + (pilotoEncontrado.getPoles() != null ? pilotoEncontrado.getPoles() : "0"));
            System.out.println("Vueltas rápidas: " + (pilotoEncontrado.getVueltasRapidas() != null ? pilotoEncontrado.getVueltasRapidas() : "0"));
            // Info de TemporadaActual (podría ser útil)
            TemporadaActual ta = pilotoEncontrado.getTemporadaActual();
            if (ta != null) {
                System.out.println("--- Info Final Temporada 2024 ---");
                System.out.println("Posición Final Campeonato: " + ta.getPosicionCampeonato());
                //System.out.println("Carreras Completadas (en 2024 por este piloto): " + ta.getCarrerasCompletadas());
            }

        } else {
            System.out.println("Piloto no encontrado.");
        }
    }

    // Modificado para buscar por ID o Nombre
    private static void mostrarInfoCircuito(List<Circuito> circuitos, Scanner scanner) {
        System.out.print("Ingrese el ID o nombre del circuito: ");
        String input = scanner.nextLine().trim();
        Optional<Circuito> circuitoOpt = Optional.empty(); // Usar Optional para manejar el resultado

        try {
            int id = Integer.parseInt(input);
            circuitoOpt = circuitos.stream().filter(c -> c.getId() == id).findFirst();
        } catch (NumberFormatException e) {
            // Si no es número, buscar por nombre (case-insensitive)
            circuitoOpt = circuitos.stream()
                    .filter(c -> c.getNombre().equalsIgnoreCase(input))
                    .findFirst();
        }

        // Usar ifPresentOrElse para manejar si se encontró o no
        circuitoOpt.ifPresentOrElse(
                c -> { // Código a ejecutar si se encuentra el circuito
                    System.out.println("\n--- Información del Circuito ---");
                    System.out.println("ID: " + c.getId());
                    System.out.println("Nombre: " + c.getNombre());
                    System.out.println("País: " + c.getPais());
                    System.out.println("Fecha carrera principal 2024: " + c.getFechaCarreraPrincipal().format(DATE_FORMATTER));
                    LocalDate sprintDate = c.getFechaCarreraSprint();
                    System.out.println("Fecha carrera sprint 2024: " + (sprintDate != null ? sprintDate.format(DATE_FORMATTER) : "N/A"));
                },
                () -> { // Código a ejecutar si NO se encuentra
                    System.out.println("Circuito no encontrado.");
                }
        );
    }

    // Ya mostraba ID, sin cambios necesarios aquí
    private static void listarPilotos(List<Piloto> pilotos) {
        System.out.println("\n=== LISTA DE PILOTOS 2024 ===");
        pilotos.stream()
                .sorted(Comparator.comparing(Piloto::getId)) // Ordenar por ID
                .forEach(p -> System.out.printf("ID: %-2d | Nombre: %-25s | Abrev: %-3s | Equipo: %s\n",
                        p.getId(),
                        p.getNombreCompleto(),
                        p.getAbreviatura(),
                        (p.getEquipo() != null ? p.getEquipo().getNombreCompleto() : "N/A")));
    }

    // Ya mostraba ID, sin cambios necesarios aquí
    private static void listarEquipos(List<Equipo> equipos) {
        System.out.println("\n=== LISTA DE EQUIPOS 2024 ===");
        equipos.stream()
                .sorted(Comparator.comparing(Equipo::getId)) // Ordenar por ID
                .forEach(eq -> System.out.printf("ID: %-2d | Nombre: %-30s | Director: %-20s | País: %s\n",
                        eq.getId(),
                        eq.getNombreCompleto(),
                        eq.getDirectorGeneral(),
                        eq.getPaisOrigen()));
    }

    // NUEVO METODO para listar circuitos con ID
    private static void listarCircuitos(List<Circuito> circuitos) {
        System.out.println("\n=== LISTA DE CIRCUITOS 2024 ===");
        circuitos.stream()
                .sorted(Comparator.comparing(Circuito::getId)) // Ordenar por ID (que coincide con orden de carrera)
                .forEach(c -> System.out.printf("ID: %-2d | Nombre: %-35s | País: %-20s | Fecha GP: %s\n",
                        c.getId(),
                        c.getNombre(),
                        c.getPais(),
                        c.getFechaCarreraPrincipal().format(DATE_FORMATTER)));
    }
}
