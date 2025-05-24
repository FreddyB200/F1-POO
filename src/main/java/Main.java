import data.CargadorDatos;
import model.*;
import util.VisualizadorComparacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // Importar para formatear fechas
import java.util.Comparator; // Importar para comparador personalizado
import java.util.InputMismatchException; // Importar para manejo de errores de Scanner
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.Optional; // Importar para Optional
import java.util.stream.Collectors;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

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
        List<Piloto> pilotos = CargadorDatos.cargarPilotos(equipos);
        List<Circuito> circuitos = CargadorDatos.cargarCircuitos();
        List<Carrera> carreras = CargadorDatos.cargarCarreras(pilotos, equipos, circuitos);

        int opcion = -1;

        String menuCompleto = """
                ╔════════════════════════════════════════════════════════════╗
                ║                  GESTIÓN F1 - TEMPORADA 2024                ║
                ╠════════════════════════════════════════════════════════════╣
                ║                                                            ║
                ║  👥 LISTADOS                                              ║
                ║    1. Listar Equipos                                       ║
                ║    2. Listar Pilotos                                       ║
                ║    3. Listar Circuitos                                     ║
                ║                                                            ║
                ║  📊 INFORMACIÓN DETALLADA                                  ║
                ║  ───────────────────────────────────────────────────────────║
                ║    4. Ver Información Detallada de Equipo                   ║
                ║    5. Ver Información Detallada de Piloto                   ║
                ║    6. Ver Información Detallada de Circuito                 ║
                ║                                                            ║
                ║  🏆 ESTADÍSTICAS Y RESULTADOS                             ║
                ║    7. Ver posiciones por carrera                           ║
                ║    8. Ver puntos por carrera                               ║
                ║                                                            ║
                ║  🤝 COMPARACIÓN                                            ║
                ║  ───────────────────────────────────────────────────────────║
                ║    9. Comparar Equipos                                     ║
                ║    10. Comparar Pilotos                                    ║
                ║                                                            ║
                ║  0. Salir                                                  ║
                ║                                                            ║
                ╚════════════════════════════════════════════════════════════╝
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
                case 1 -> listarEquipos(equipos);
                case 2 -> listarPilotos(pilotos);
                case 3 -> listarCircuitos(circuitos);
                case 4 -> mostrarInfoEquipo(equipos, scanner);
                case 5 -> mostrarInfoPiloto(pilotos, scanner);
                case 6 -> mostrarInfoCircuito(circuitos, scanner);
                case 7 -> mostrarPosicionesPilotosEquipos(carreras);
                case 8 -> mostrarPuntosPorCarrera(carreras);
                case 9 -> compararEquipos(equipos);
                case 10 -> compararPilotos(pilotos);
                case 0 -> System.out.println("Saliendo...");
                case -1 -> {} // No hacer nada si la opción fue inválida por error de input
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    // Método corregido para ordenar posiciones correctamente
    private static void mostrarPosicionesPilotosEquipos(List<Carrera> carreras) {
        while (true) {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Ver todas las carreras una por una");
            System.out.println("2. Filtrar por nombre o fecha");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            String opcion = scanner.nextLine().trim();
            if (opcion.equals("3")) return;
            if (opcion.equals("1")) {
                for (Carrera carrera : carreras) {
                    mostrarCarreraConFormato(carrera);
                    System.out.println("\nPresiona Enter para ver la siguiente carrera o escribe 'salir' para volver al menú...");
                    String next = scanner.nextLine().trim();
                    if (next.equalsIgnoreCase("salir")) return;
                }
                return;
            }
            if (opcion.equals("2")) {
                while (true) {
                    System.out.println("\nFiltra por nombre o fecha (formato: dd/MM/yyyy o dd/MM/yy). Escribe 'salir' para volver al menú.");
                    String filtro = scanner.nextLine().trim();
                    if (filtro.equalsIgnoreCase("salir")) break;
                    if (!filtro.matches("\\d{2}/\\d{2}/\\d{2,4}") && !filtro.matches("[a-zA-Z].*")) {
                        System.out.println("⚠️ Formato de fecha inválido. Usa dd/MM/yyyy o dd/MM/yy, por ejemplo: 21/04/2024 o 21/04/24.");
                        continue;
                    }
                    List<Carrera> filtradas = carreras.stream().filter(c ->
                        c.getNombre().equalsIgnoreCase(filtro) ||
                        fechaCoincide(c.getFecha(), filtro)
                    ).toList();
                    if (filtradas.isEmpty()) {
                        System.out.println("❌ No se encontraron carreras con ese nombre o fecha.");
                    } else {
                        for (Carrera carrera : filtradas) {
                            mostrarCarreraConFormato(carrera);
                        }
                    }
                    System.out.println("¿Deseas filtrar otra vez? (s/n)");
                    String otra = scanner.nextLine().trim();
                    if (!otra.equalsIgnoreCase("s")) break;
                }
            }
        }
    }

    // Método auxiliar para comparar fechas en ambos formatos
    private static boolean fechaCoincide(java.time.LocalDate fecha, String filtro) {
        java.time.format.DateTimeFormatter largo = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        java.time.format.DateTimeFormatter corto = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yy");
        try {
            return fecha.format(largo).equals(filtro) || fecha.format(corto).equals(filtro);
        } catch (Exception e) {
            return false;
        }
    }

    // Método auxiliar para mostrar la carrera con el formato ya implementado
    private static void mostrarCarreraConFormato(Carrera carrera) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-54s ║\n", carrera.getNombre() + " - " + 
            (carrera.getCircuito() != null ? carrera.getCircuito().getPais() : "") + 
            " (" + carrera.getFecha().format(DATE_FORMATTER) + ")");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        
        System.out.println("║ 📍 POSICIONES DE PILOTOS                                  ║");
        System.out.println("╟────────────────────────────────────────────────────────────╢");
        
        carrera.getPosicionesPilotos().entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(e -> System.out.printf("║ %-3s │ %-30s (%s)%-15s ║\n",
                        e.getValue(),
                        e.getKey().getNombreCompleto(),
                        e.getKey().getAbreviatura(),
                        ""));

        System.out.println("╟────────────────────────────────────────────────────────────╢");
        System.out.println("║ 🏆 PUNTOS DE EQUIPOS                                      ║");
        System.out.println("╟────────────────────────────────────────────────────────────╢");
        
        carrera.getPuntosEquipos().entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(e -> System.out.printf("║ %-30s │ %3d puntos%-20s ║\n",
                        e.getKey().getNombreCompleto(),
                        e.getValue(),
                        ""));
        
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    // Método renombrado y funcionalmente correcto (muestra puntos POR carrera)
    private static void mostrarPuntosPorCarrera(List<Carrera> carreras) {
        while (true) {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Ver todas las carreras una por una");
            System.out.println("2. Filtrar por nombre o fecha");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            String opcion = scanner.nextLine().trim();
            if (opcion.equals("3")) return;
            if (opcion.equals("1")) {
                for (Carrera carrera : carreras) {
                    mostrarPuntosCarreraConFormato(carrera);
                    System.out.println("\nPresiona Enter para ver la siguiente carrera o escribe 'salir' para volver al menú...");
                    String next = scanner.nextLine().trim();
                    if (next.equalsIgnoreCase("salir")) return;
                }
                return;
            }
            if (opcion.equals("2")) {
                while (true) {
                    System.out.println("\nFiltra por nombre o fecha (formato: dd/MM/yyyy o dd/MM/yy). Escribe 'salir' para volver al menú.");
                    String filtro = scanner.nextLine().trim();
                    if (filtro.equalsIgnoreCase("salir")) break;
                    if (!filtro.matches("\\d{2}/\\d{2}/\\d{2,4}") && !filtro.matches("[a-zA-Z].*")) {
                        System.out.println("⚠️ Formato de fecha inválido. Usa dd/MM/yyyy o dd/MM/yy, por ejemplo: 21/04/2024 o 21/04/24.");
                        continue;
                    }
                    List<Carrera> filtradas = carreras.stream().filter(c ->
                        c.getNombre().equalsIgnoreCase(filtro) ||
                        fechaCoincide(c.getFecha(), filtro)
                    ).toList();
                    if (filtradas.isEmpty()) {
                        System.out.println("❌ No se encontraron carreras con ese nombre o fecha.");
                    } else {
                        for (Carrera carrera : filtradas) {
                            mostrarPuntosCarreraConFormato(carrera);
                        }
                    }
                    System.out.println("¿Deseas filtrar otra vez? (s/n)");
                    String otra = scanner.nextLine().trim();
                    if (!otra.equalsIgnoreCase("s")) break;
                }
            }
        }
    }

    // Método auxiliar para mostrar los puntos de la carrera con el formato ya implementado
    private static void mostrarPuntosCarreraConFormato(Carrera carrera) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-54s ║\n", carrera.getNombre() + " (" + carrera.getFecha().format(DATE_FORMATTER) + ")");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        
        System.out.println("║ 🏎️ PUNTOS DE PILOTOS                                      ║");
        System.out.println("╟────────────────────────────────────────────────────────────╢");
        
        carrera.getPuntosPilotos().entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(e -> System.out.printf("║ %-30s (%s) │ %3d puntos%-15s ║\n",
                        e.getKey().getNombreCompleto(),
                        e.getKey().getAbreviatura(),
                        e.getValue(),
                        ""));
        
        System.out.println("╟────────────────────────────────────────────────────────────╢");
        System.out.println("║ 🏆 PUNTOS DE EQUIPOS                                      ║");
        System.out.println("╟────────────────────────────────────────────────────────────╢");
        
        carrera.getPuntosEquipos().entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(e -> System.out.printf("║ %-30s │ %3d puntos%-20s ║\n",
                        e.getKey().getNombreCompleto(),
                        e.getValue(),
                        ""));
        
        System.out.println("╚════════════════════════════════════════════════════════════╝");
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
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.printf("║ 🏎️  %-50s ║\n", equipoEncontrado.getNombreCompleto());
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.printf("║ ID: %-52d ║\n", equipoEncontrado.getId());
            System.out.printf("║ Director: %-48s ║\n", equipoEncontrado.getDirectorGeneral());
            System.out.printf("║ País: %-50s ║\n", equipoEncontrado.getPaisOrigen());
            System.out.printf("║ Motor: %-49s ║\n", equipoEncontrado.getProveedorMotor());
            System.out.println("╟────────────────────────────────────────────────────────────╢");
            System.out.println("║ 📊 ESTADÍSTICAS 2024                                      ║");
            System.out.println("╟────────────────────────────────────────────────────────────╢");
            System.out.printf("║ Puntos: %-49.1f ║\n", equipoEncontrado.getPuntos2024());
            System.out.printf("║ Carreras Ganadas: %-42d ║\n", 
                equipoEncontrado.getCarrerasGanadas2024() != null ? equipoEncontrado.getCarrerasGanadas2024() : 0);
            System.out.printf("║ Última Victoria: %-44s ║\n", 
                equipoEncontrado.getUltimaVictoria() != null ? equipoEncontrado.getUltimaVictoria().format(DATE_FORMATTER) : "N/A");
            System.out.printf("║ Campeonatos Históricos: %-38d ║\n", equipoEncontrado.getCampeonatosGanados());
            System.out.println("╟────────────────────────────────────────────────────────────╢");
            System.out.println("║ 👥 PILOTOS 2024                                           ║");
            System.out.println("╟────────────────────────────────────────────────────────────╢");
            
            List<Piloto> pilotosDelEquipo = equipoEncontrado.getPilotos();
            if (pilotosDelEquipo != null && !pilotosDelEquipo.isEmpty()) {
                pilotosDelEquipo.forEach(p -> System.out.printf("║ • %-30s (#%d, %s)%-15s ║\n",
                        p.getNombreCompleto(), p.getNumero(), p.getAbreviatura(), ""));
            } else {
                System.out.println("║ No se encontraron pilotos vinculados                    ║");
            }
            System.out.println("╚════════════════════════════════════════════════════════════╝");
        } else {
            System.out.println("❌ Equipo no encontrado.");
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
            pilotoEncontrado = pilotos.stream()
                    .filter(p -> p.getNombreCompleto().equalsIgnoreCase(input) || p.getAbreviatura().equalsIgnoreCase(input))
                    .findFirst()
                    .orElse(null);
        }

        if (pilotoEncontrado != null) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.printf("║ 🏎️  %-50s ║\n", pilotoEncontrado.getNombreCompleto());
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.printf("║ ID: %-52d ║\n", pilotoEncontrado.getId());
            System.out.printf("║ Número: %-49d ║\n", pilotoEncontrado.getNumero());
            System.out.printf("║ Abreviatura: %-45s ║\n", pilotoEncontrado.getAbreviatura());
            System.out.printf("║ Equipo: %-48s ║\n", 
                pilotoEncontrado.getEquipo() != null ? pilotoEncontrado.getEquipo().getNombreCompleto() : "N/A");
            System.out.printf("║ Nacionalidad: %-45s ║\n", pilotoEncontrado.getNacionalidad());
            System.out.printf("║ Edad: %-51d ║\n", pilotoEncontrado.getEdad());
            System.out.println("╟────────────────────────────────────────────────────────────╢");
            System.out.println("║ 📊 ESTADÍSTICAS 2024                                      ║");
            System.out.println("╟────────────────────────────────────────────────────────────╢");
            System.out.printf("║ Puntos: %-49.1f ║\n", pilotoEncontrado.getPuntos2024());
            System.out.println("╟────────────────────────────────────────────────────────────╢");
            System.out.println("║ 🏆 ESTADÍSTICAS HISTÓRICAS                               ║");
            System.out.println("╟────────────────────────────────────────────────────────────╢");
            System.out.printf("║ Campeonatos Mundiales: %-38d ║\n", pilotoEncontrado.getCampeonatosGanados());
            System.out.printf("║ GPs Disputados: %-42d ║\n", pilotoEncontrado.getCarrerasDisputadas());
            System.out.printf("║ Victorias: %-46d ║\n", 
                pilotoEncontrado.getCarrerasGanadas() != null ? pilotoEncontrado.getCarrerasGanadas() : 0);
            System.out.printf("║ Podios: %-47d ║\n", 
                pilotoEncontrado.getPodios() != null ? pilotoEncontrado.getPodios() : 0);
            System.out.printf("║ Poles: %-48d ║\n", 
                pilotoEncontrado.getPoles() != null ? pilotoEncontrado.getPoles() : 0);
            System.out.printf("║ Vueltas Rápidas: %-41d ║\n", 
                pilotoEncontrado.getVueltasRapidas() != null ? pilotoEncontrado.getVueltasRapidas() : 0);
            System.out.println("╚════════════════════════════════════════════════════════════╝");
        } else {
            System.out.println("❌ Piloto no encontrado.");
        }
    }

    // Modificado para buscar por ID o Nombre
    private static void mostrarInfoCircuito(List<Circuito> circuitos, Scanner scanner) {
        System.out.print("Ingrese el ID o nombre del circuito: ");
        String input = scanner.nextLine().trim();
        Circuito circuitoEncontrado = null;
        try {
            int id = Integer.parseInt(input);
            circuitoEncontrado = circuitos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        } catch (NumberFormatException e) {
            circuitoEncontrado = circuitos.stream()
                    .filter(c -> c.getNombre().equalsIgnoreCase(input))
                    .findFirst()
                    .orElse(null);
        }

        if (circuitoEncontrado != null) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.printf("║ 🏁  %-50s ║\n", circuitoEncontrado.getNombre());
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.printf("║ ID: %-52d ║\n", circuitoEncontrado.getId());
            System.out.printf("║ País: %-50s ║\n", circuitoEncontrado.getPais());
            System.out.printf("║ Ciudad: %-48s ║\n", circuitoEncontrado.getCiudad());
            System.out.println("╟────────────────────────────────────────────────────────────╢");
            System.out.println("║ 📏 CARACTERÍSTICAS                                        ║");
            System.out.println("╟────────────────────────────────────────────────────────────╢");
            System.out.printf("║ Longitud: %-46.0f m ║\n", circuitoEncontrado.getLongitud());
            System.out.printf("║ Vueltas: %-48d ║\n", circuitoEncontrado.getVueltas());
            System.out.printf("║ Distancia Total: %-40.0f m ║\n", 
                circuitoEncontrado.getLongitud() * circuitoEncontrado.getVueltas());
            System.out.println("╚════════════════════════════════════════════════════════════╝");
        } else {
            System.out.println("❌ Circuito no encontrado.");
        }
    }

    // Ya mostraba ID, sin cambios necesarios aquí
    private static void listarPilotos(List<Piloto> pilotos) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ 📋 LISTADO DE PILOTOS 2024                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ ID │ Número │ Abrev. │ Nombre Completo                    ║");
        System.out.println("╟────┼────────┼────────┼────────────────────────────────────╢");
        
        pilotos.stream()
                .sorted(Comparator.comparing(Piloto::getNumero))
                .forEach(p -> System.out.printf("║ %2d │ %6d │ %-6s │ %-35s ║\n",
                        p.getId(),
                        p.getNumero(),
                        p.getAbreviatura(),
                        p.getNombreCompleto()));
        
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    // Ya mostraba ID, sin cambios necesarios aquí
    private static void listarEquipos(List<Equipo> equipos) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ 📋 LISTADO DE EQUIPOS 2024                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ ID │ Nombre Completo                    │ Puntos 2024      ║");
        System.out.println("╟────┼────────────────────────────────────┼─────────────────╢");
        
        equipos.stream()
                .sorted(Comparator.comparing(Equipo::getPuntos2024).reversed())
                .forEach(e -> System.out.printf("║ %2d │ %-35s │ %6.1f puntos%-5s ║\n",
                        e.getId(),
                        e.getNombreCompleto(),
                        e.getPuntos2024(),
                        ""));
        
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    // NUEVO METODO para listar circuitos con ID
    private static void listarCircuitos(List<Circuito> circuitos) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ 📋 LISTADO DE CIRCUITOS 2024                               ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ ID │ Circuito                    │ País                   ║");
        System.out.println("╟────┼─────────────────────────────┼────────────────────────╢");
        
        circuitos.stream()
                .sorted(Comparator.comparing(Circuito::getNombre))
                .forEach(c -> System.out.printf("║ %2d │ %-28s │ %-22s ║\n",
                        c.getId(),
                        c.getNombre(),
                        c.getPais()));
        
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    // Método para encontrar un equipo por ID o nombre
    private static Equipo encontrarEquipo(List<Equipo> equipos, String identificador) {
        try {
            int id = Integer.parseInt(identificador);
            return equipos.stream()
                          .filter(equipo -> equipo.getId() == id)
                          .findFirst()
                          .orElse(null);
        } catch (NumberFormatException e) {
            return equipos.stream()
                          .filter(equipo -> equipo.getNombreCompleto().equalsIgnoreCase(identificador))
                          .findFirst()
                          .orElse(null);
        }
    }

    private static void compararEquipos(List<Equipo> equipos) {
        if (equipos.size() < 2) {
            System.out.println("Se necesitan al menos dos equipos para comparar.");
            return;
        }

        System.out.println("\n=== COMPARACIÓN DE EQUIPOS ===");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombreCompleto());
        }

        System.out.print("\nSeleccione el primer equipo (número): ");
        int equipo1 = scanner.nextInt() - 1;
        System.out.print("Seleccione el segundo equipo (número): ");
        int equipo2 = scanner.nextInt() - 1;

        if (equipo1 < 0 || equipo1 >= equipos.size() || equipo2 < 0 || equipo2 >= equipos.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        VisualizadorComparacion.mostrarComparacionEquipos(equipos.get(equipo1), equipos.get(equipo2));
    }

    // Método para encontrar un piloto por ID, nombre o abreviatura
    private static Piloto encontrarPiloto(List<Piloto> pilotos, String identificador) {
        try {
            int id = Integer.parseInt(identificador);
            return pilotos.stream()
                         .filter(p -> p.getId() == id)
                         .findFirst()
                         .orElse(null);
        } catch (NumberFormatException e) {
            return pilotos.stream()
                         .filter(p -> p.getNombreCompleto().equalsIgnoreCase(identificador) ||
                                      p.getAbreviatura().equalsIgnoreCase(identificador))
                         .findFirst()
                         .orElse(null);
        }
    }

    private static void compararPilotos(List<Piloto> pilotos) {
        if (pilotos.size() < 2) {
            System.out.println("Se necesitan al menos dos pilotos para comparar.");
            return;
        }

        System.out.println("\n=== COMPARACIÓN DE PILOTOS ===");
        for (int i = 0; i < pilotos.size(); i++) {
            System.out.println((i + 1) + ". " + pilotos.get(i).getNombreCompleto());
        }

        System.out.print("\nSeleccione el primer piloto (número): ");
        int piloto1 = scanner.nextInt() - 1;
        System.out.print("Seleccione el segundo piloto (número): ");
        int piloto2 = scanner.nextInt() - 1;

        if (piloto1 < 0 || piloto1 >= pilotos.size() || piloto2 < 0 || piloto2 >= pilotos.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        VisualizadorComparacion.mostrarComparacionPilotos(pilotos.get(piloto1), pilotos.get(piloto2));
    }
}
