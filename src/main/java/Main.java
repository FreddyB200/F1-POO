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
import java.util.stream.Collectors;

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
        List<Piloto> pilotos = CargadorDatos.cargarPilotos();
        List<Circuito> circuitos = CargadorDatos.cargarCircuitos();
        List<Carrera> carreras = CargadorDatos.cargarCarreras(pilotos, equipos, circuitos);

        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        String menuCompleto = """
                ╔════════════════════════════════════════════════════════════╗
                ║                  GESTIÓN F1 - TEMPORADA 2024                ║
                ╠════════════════════════════════════════════════════════════╣
                ║                                                            ║
                ║  📊 ESTADÍSTICAS Y RESULTADOS                             ║
                ║    1. Ver posiciones por carrera                           ║
                ║    2. Ver puntos por carrera                               ║
                ║                                                            ║
                ║  👥 INFORMACIÓN DE PARTICIPANTES                          ║
                ║    3. Información de equipo                                ║
                ║    4. Información de piloto                                ║
                ║    5. Información de circuito                              ║
                ║                                                            ║
                ║  📋 LISTADOS                                              ║
                ║    6. Listar pilotos                                       ║
                ║    7. Listar equipos                                       ║
                ║    8. Listar circuitos                                     ║
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
                case 1 -> mostrarPosicionesPilotosEquipos(carreras);
                case 2 -> mostrarPuntosPorCarrera(carreras); // Método renombrado
                case 3 -> mostrarInfoEquipo(equipos, scanner);
                case 4 -> mostrarInfoPiloto(pilotos, scanner);
                case 5 -> mostrarInfoCircuito(circuitos, scanner);
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
        Scanner scanner = new Scanner(System.in);
        
        // Comparador para ordenar posiciones (números primero, luego DNF, DSQ, etc.)
        Comparator<Map.Entry<Piloto, String>> comparadorPosicion = (e1, e2) -> {
            String pos1 = e1.getValue();
            String pos2 = e2.getValue();
            boolean esNum1 = pos1.matches("\\d+");
            boolean esNum2 = pos2.matches("\\d+");

            if (esNum1 && esNum2) {
                return Integer.compare(Integer.parseInt(pos1), Integer.parseInt(pos2));
            } else if (esNum1) {
                return -1;
            } else if (esNum2) {
                return 1;
            } else {
                return pos1.compareTo(pos2);
            }
        };
        
        // Nuevo: Opción de filtrar o salir
        System.out.println("Ingrese el nombre o la fecha (DD/MM/YYYY) de la carrera para buscar, o escriba 'salir' para regresar al menú:");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("salir")) {
            return; // Salir del método
        }

        List<Carrera> carrerasAMostrar = carreras;
        if (!input.isEmpty()) {
            // Filtrar carreras
            carrerasAMostrar = carreras.stream()
                    .filter(carrera -> 
                        carrera.getNombre().equalsIgnoreCase(input) || 
                        carrera.getFecha().format(DATE_FORMATTER).equals(input)
                    )
                    .collect(Collectors.toList());

            if (carrerasAMostrar.isEmpty()) {
                System.out.println("❌ No se encontraron carreras con el nombre o fecha especificados.");
                return; // Regresar al menú si no se encuentra nada
            }
        }

        for (Carrera carrera : carrerasAMostrar) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-54s ║\n", carrera.getNombre() + " - " + 
                (carrera.getCircuito() != null ? carrera.getCircuito().getPais() : "") + 
                " (" + carrera.getFecha().format(DATE_FORMATTER) + ")");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            
            System.out.println("║ 📍 POSICIONES DE PILOTOS                                  ║");
            System.out.println("╟────────────────────────────────────────────────────────────╢");
            
            carrera.getPosicionesPilotos().entrySet().stream()
                    .sorted(comparadorPosicion)
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
            
            System.out.println("\nPresiona Enter para ver la siguiente carrera, o escribe 'salir' para volver al menú...");
            String nextInput = scanner.nextLine().trim();
            if (nextInput.equalsIgnoreCase("salir")) {
                break; // Salir del bucle interior
            }
        }
    }

    // Método renombrado y funcionalmente correcto (muestra puntos POR carrera)
    private static void mostrarPuntosPorCarrera(List<Carrera> carreras) {
        Scanner scanner = new Scanner(System.in);
        
        for (Carrera carrera : carreras) {
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
            
            System.out.println("\nPresiona Enter para ver la siguiente carrera...");
            scanner.nextLine();
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
            System.out.printf("║ Longitud: %-46.3f km ║\n", circuitoEncontrado.getLongitud());
            System.out.printf("║ Vueltas: %-48d ║\n", circuitoEncontrado.getVueltas());
            System.out.printf("║ Distancia Total: %-40.3f km ║\n", 
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
}
