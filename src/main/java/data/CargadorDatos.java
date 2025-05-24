package data;

import model.Circuito;
import model.Equipo;
import model.Carrera;
import model.Piloto;
import model.TemporadaActual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.time.LocalDate.parse;

public class CargadorDatos {

    // Equipos 2024: Compatibles con nuevo constructor Equipo(id, nombre, campeonatos, puntos2024, ...)
    // puntos2024 = Puntos finales del campeonato de constructores 2024
    public static List<Equipo> cargarEquipos() {
        return List.of(
                new Equipo(1, "McLaren F1 Team", 9, 666.0, "Andrea Stella", "Reino Unido", "Mercedes", 4, parse("2024-09-22")),
                new Equipo(2, "Scuderia Ferrari", 16, 652.0, "Frédéric Vasseur", "Italia", "Ferrari", 3, parse("2024-09-01")),
                new Equipo(3, "Oracle Red Bull Racing", 6, 589.0, "Christian Horner", "Austria", "Honda RBPT", 1, parse("2024-06-23")), // Ganó 1 carrera con Verstappen
                new Equipo(4, "Mercedes-AMG PETRONAS F1 Team", 8, 468.0, "Toto Wolff", "Alemania", "Mercedes", 2, parse("2024-07-28")), // 1 Russell, 1 Hamilton
                new Equipo(5, "Aston Martin Aramco F1 Team", 0, 94.0, "Mike Krack", "Reino Unido", "Mercedes", 0, null),
                new Equipo(6, "BWT Alpine F1 Team", 2, 65.0, "Bruno Famin", "Francia", "Renault", 0, parse("2021-08-01")),
                new Equipo(7, "MoneyGram Haas F1 Team", 0, 58.0, "Ayao Komatsu", "Estados Unidos", "Ferrari", 0, null),
                new Equipo(8, "Visa Cash App RB F1 Team", 0, 46.0, "Laurent Mekies", "Italia", "Honda RBPT", 0, parse("2020-09-06")),
                new Equipo(9, "Williams Racing", 9, 17.0, "James Vowles", "Reino Unido", "Mercedes", 0, parse("2012-05-13")),
                new Equipo(10, "Stake F1 Team Kick Sauber", 0, 4.0, "Alessandro Alunni Bravi", "Suiza", "Ferrari", 0, parse("2008-06-08"))
        );
    }

    // Circuitos 2024: Actualizado con nuevos campos
    public static List<Circuito> cargarCircuitos() {
        return List.of(
                new Circuito(1, "Bahrain International Circuit", "Bahrein", "Sakhir", 5.412, 57, parse("2024-03-02"), null),
                new Circuito(2, "Jeddah Corniche Circuit", "Arabia Saudita", "Yeda", 6.174, 50, parse("2024-03-09"), null),
                new Circuito(3, "Albert Park Circuit", "Australia", "Melbourne", 5.278, 58, parse("2024-03-24"), null),
                new Circuito(4, "Suzuka International Racing Course", "Japón", "Suzuka", 5.807, 53, parse("2024-04-07"), null),
                new Circuito(5, "Shanghai International Circuit", "China", "Shanghái", 5.451, 56, parse("2024-04-21"), parse("2024-04-20")),
                new Circuito(6, "Miami International Autodrome", "Estados Unidos", "Miami", 5.412, 57, parse("2024-05-05"), parse("2024-05-04")),
                new Circuito(7, "Autodromo Internazionale Enzo e Dino Ferrari", "Italia", "Imola", 4.909, 63, parse("2024-05-19"), null), // Emilia Romagna
                new Circuito(8, "Circuit de Monaco", "Mónaco", "Monte Carlo", 3.337, 78, parse("2024-05-26"), null),
                new Circuito(9, "Circuit Gilles Villeneuve", "Canadá", "Montreal", 4.361, 70, parse("2024-06-09"), null),
                new Circuito(10, "Circuit de Barcelona-Catalunya", "España", "Montmeló", 4.657, 66, parse("2024-06-23"), null),
                new Circuito(11, "Red Bull Ring", "Austria", "Spielberg", 4.318, 71, parse("2024-06-30"), parse("2024-06-29")),
                new Circuito(12, "Silverstone Circuit", "Reino Unido", "Silverstone", 5.891, 52, parse("2024-07-07"), null),
                new Circuito(13, "Hungaroring", "Hungría", "Budapest", 4.381, 70, parse("2024-07-21"), null),
                new Circuito(14, "Circuit de Spa-Francorchamps", "Bélgica", "Stavelot", 7.004, 44, parse("2024-07-28"), null),
                new Circuito(15, "Circuit Zandvoort", "Países Bajos", "Zandvoort", 4.259, 72, parse("2024-08-25"), null),
                new Circuito(16, "Autodromo Nazionale Monza", "Italia", "Monza", 5.793, 53, parse("2024-09-01"), null), // Italia
                new Circuito(17, "Baku City Circuit", "Azerbaiyán", "Bakú", 6.003, 51, parse("2024-09-15"), null),
                new Circuito(18, "Marina Bay Street Circuit", "Singapur", "Singapur", 5.063, 62, parse("2024-09-22"), null),
                new Circuito(19, "Circuit of the Americas", "Estados Unidos", "Austin", 5.513, 56, parse("2024-10-20"), parse("2024-10-19")), // USA
                new Circuito(20, "Autódromo Hermanos Rodríguez", "México", "Ciudad de México", 4.304, 71, parse("2024-10-27"), null),
                new Circuito(21, "Autódromo José Carlos Pace", "Brasil", "São Paulo", 4.309, 71, parse("2024-11-03"), parse("2024-11-02")), // São Paulo
                new Circuito(22, "Las Vegas Strip Circuit", "Estados Unidos", "Las Vegas", 6.201, 50, parse("2024-11-23"), null), // Las Vegas
                new Circuito(23, "Lusail International Circuit", "Qatar", "Lusail", 5.419, 57, parse("2024-12-01"), parse("2024-11-30")),
                new Circuito(24, "Yas Marina Circuit", "Emiratos Árabes Unidos", "Abu Dhabi", 5.281, 58, parse("2024-12-08"), null) // Abu Dhabi
        );
    }

    // Pilotos 2024: Compatibles con nuevo constructor Piloto(id, nombre, campeonatos, puntos2024, equipo, ...)
    // puntos2024 = Puntos finales del campeonato de pilotos 2024
    // Otros stats (victorias, podios, etc.) = Totales de carrera hasta fin 2024
    public static List<Piloto> cargarPilotos(List<Equipo> equipos) {
        Map<String, Equipo> equiposPorNombre = equipos.stream()
                .collect(Collectors.toMap(Equipo::getNombreCompleto, Function.identity()));

        // Creación de la lista de pilotos
        List<Piloto> pilotosList = List.of(
                // Pilotos ordenados por posición final 2024 para referencia
                // ID Pilotos: 1-Verstappen, 7-Norris, 3-Leclerc, 8-Piastri, 4-Sainz, 6-Russell, 5-Hamilton, 2-Perez,
                // 9-Alonso, 12-Gasly, 19-Hulkenberg, 16-Tsunoda, 10-Stroll, 11-Ocon, 20-Magnussen, 13-Albon,
                // 15-Ricciardo, 21-Bearman, 22-Colapinto, 17-Bottas, 14-Sargeant, 18-Zhou, 23-Doohan
                new Piloto(1, "Max Verstappen", 4, 437.0, equiposPorNombre.get("Oracle Red Bull Racing"), 1, "VER", 209, 61, 112, 32, 32, Map.of(), "Holandés", 27, new TemporadaActual(1, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))), // Campeón 2024
                new Piloto(7, "Lando Norris", 0, 374.0, equiposPorNombre.get("McLaren F1 Team"), 4, "NOR", 128, 4, 21, 1, 10, Map.of(), "Británico", 25, new TemporadaActual(2, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(3, "Charles Leclerc", 0, 356.0, equiposPorNombre.get("Scuderia Ferrari"), 16, "LEC", 149, 8, 35, 23, 9, Map.of(), "Monegasco", 27, new TemporadaActual(3, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(8, "Oscar Piastri", 0, 292.0, equiposPorNombre.get("McLaren F1 Team"), 81, "PIA", 46, 2, 4, 0, 3, Map.of(), "Australiano", 24, new TemporadaActual(4, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(4, "Carlos Sainz", 0, 290.0, equiposPorNombre.get("Scuderia Ferrari"), 55, "SAI", 205, 3, 22, 5, 3, Map.of(), "Español", 30, new TemporadaActual(5, 23, 0, "Abu Dhabi GP", parse("2024-12-08"))), // Corrió 23/24
                new Piloto(6, "George Russell", 0, 245.0, equiposPorNombre.get("Mercedes-AMG PETRONAS F1 Team"), 63, "RUS", 128, 2, 13, 2, 8, Map.of(), "Británico", 27, new TemporadaActual(6, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(5, "Lewis Hamilton", 7, 223.0, equiposPorNombre.get("Mercedes-AMG PETRONAS F1 Team"), 44, "HAM", 354, 103, 198, 104, 67, Map.of(), "Británico", 40, new TemporadaActual(7, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(2, "Sergio Pérez", 0, 152.0, equiposPorNombre.get("Oracle Red Bull Racing"), 11, "PER", 281, 6, 41, 3, 11, Map.of(), "Mexicano", 35, new TemporadaActual(8, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(9, "Fernando Alonso", 2, 71.0, equiposPorNombre.get("Aston Martin Aramco F1 Team"), 14, "ALO", 400, 32, 106, 22, 25, Map.of(), "Español", 43, new TemporadaActual(9, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(12, "Pierre Gasly", 0, 42.0, equiposPorNombre.get("BWT Alpine F1 Team"), 10, "GAS", 152, 1, 5, 0, 3, Map.of(), "Francés", 29, new TemporadaActual(10, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(19, "Nico Hülkenberg", 0, 41.0, equiposPorNombre.get("MoneyGram Haas F1 Team"), 27, "HUL", 225, 0, 0, 1, 2, Map.of(), "Alemán", 37, new TemporadaActual(11, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(16, "Yuki Tsunoda", 0, 30.0, equiposPorNombre.get("Visa Cash App RB F1 Team"), 22, "TSU", 91, 0, 0, 0, 1, Map.of(), "Japonés", 24, new TemporadaActual(12, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(10, "Lance Stroll", 0, 23.0, equiposPorNombre.get("Aston Martin Aramco F1 Team"), 18, "STR", 167, 0, 3, 1, 0, Map.of(), "Canadiense", 26, new TemporadaActual(13, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(11, "Esteban Ocon", 0, 23.0, equiposPorNombre.get("BWT Alpine F1 Team"), 31, "OCO", 154, 1, 3, 0, 0, Map.of(), "Francés", 28, new TemporadaActual(14, 23, 0, "Abu Dhabi GP", parse("2024-12-08"))), // Corrió 23/24
                new Piloto(20, "Kevin Magnussen", 0, 16.0, equiposPorNombre.get("MoneyGram Haas F1 Team"), 20, "MAG", 185, 0, 1, 1, 3, Map.of(), "Danés", 32, new TemporadaActual(15, 22, 0, "Abu Dhabi GP", parse("2024-12-08"))), // Corrió 22/24
                new Piloto(13, "Alexander Albon", 0, 12.0, equiposPorNombre.get("Williams Racing"), 23, "ALB", 104, 0, 2, 0, 0, Map.of(), "Tailandés", 29, new TemporadaActual(16, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(15, "Daniel Ricciardo", 0, 12.0, equiposPorNombre.get("Visa Cash App RB F1 Team"), 3, "RIC", 257, 8, 32, 3, 16, Map.of(), "Australiano", 35, new TemporadaActual(17, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(21, "Oliver Bearman", 0, 7.0, equiposPorNombre.get("Scuderia Ferrari"), 38, "BEA", 3, 0, 0, 0, 0, Map.of(), "Británico", 19, new TemporadaActual(18, 3, 0, "Brazilian GP", parse("2024-11-03"))), // Corrió 3 GPs (Ferrari/Haas)
                new Piloto(22, "Franco Colapinto", 0, 5.0, equiposPorNombre.get("Williams Racing"), 43, "COL", 9, 0, 0, 0, 0, Map.of(), "Argentino", 21, new TemporadaActual(19, 9, 0, "Abu Dhabi GP", parse("2024-12-08"))), // Corrió 9 GPs
                new Piloto(17, "Valtteri Bottas", 0, 0.0, equiposPorNombre.get("Stake F1 Team Kick Sauber"), 77, "BOT", 246, 10, 67, 20, 19, Map.of(), "Finlandés", 35, new TemporadaActual(20, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(14, "Logan Sargeant", 0, 0.0, equiposPorNombre.get("Williams Racing"), 2, "SAR", 36, 0, 0, 0, 0, Map.of(), "Estadounidense", 24, new TemporadaActual(21, 15, 0, "Italian GP", parse("2024-09-01"))), // Corrió 15 GPs
                new Piloto(18, "Guanyu Zhou", 0, 0.0, equiposPorNombre.get("Stake F1 Team Kick Sauber"), 24, "ZHO", 68, 0, 0, 0, 2, Map.of(), "Chino", 25, new TemporadaActual(22, 24, 0, "Abu Dhabi GP", parse("2024-12-08"))),
                new Piloto(23, "Jack Doohan", 0, 0.0, equiposPorNombre.get("BWT Alpine F1 Team"), 61, "DOO", 1, 0, 0, 0, 0, Map.of(), "Australiano", 22, new TemporadaActual(24, 1, 0, "Abu Dhabi GP", parse("2024-12-08"))), // Corrió 1 GP
                new Piloto(24, "Liam Lawson", 0, 0.0, equiposPorNombre.get("Visa Cash App RB F1 Team"), 58, "LAW", 1, 0, 0, 0, 0, Map.of(), "Neozelandés", 22, new TemporadaActual(24, 1, 0, "Abu Dhabi GP", parse("2024-12-08"))) //
        );

        // *** VINCULACIÓN PILOTO <-> EQUIPO ***
        // Es crucial para que equipo.getPilotos() funcione en Main.java
        Map<Integer, Equipo> equiposMapById = equipos.stream().collect(Collectors.toMap(Equipo::getId, Function.identity()));

        for (Piloto piloto : pilotosList) {
            Equipo equipoDelPiloto = piloto.getEquipo(); // Equipo asignado en el constructor del piloto
            if (equipoDelPiloto != null) {
                // Obtenemos la instancia del equipo de la lista principal (para asegurar que es la misma)
                Equipo equipoEnLista = equiposMapById.get(equipoDelPiloto.getId());
                if (equipoEnLista != null) {
                    equipoEnLista.agregarPiloto(piloto); // Agregamos el piloto a la lista interna del equipo
                }
            }
        }

        return pilotosList; // Devolvemos la lista de pilotos creada
    }


    // Carreras 2024: Completadas con RESULTADOS REALES y compatibles con nuevos modelos
    public static List<Carrera> cargarCarreras(List<Piloto> pilotos, List<Equipo> equipos, List<Circuito> circuitos) {
        Map<String, Circuito> circuitosMap = circuitos.stream().collect(Collectors.toMap(Circuito::getNombre, Function.identity()));
        Map<Integer, Piloto> pilotosMap = pilotos.stream().collect(Collectors.toMap(Piloto::getId, Function.identity()));
        List<Carrera> carreras = new ArrayList<>();

        // Helper para crear los mapas de resultados por carrera usando IDs de Piloto
        // Adaptado para asegurar que se usan los objetos Piloto correctos
        TriFunction<Map<Integer, String>, Map<Integer, Integer>, Circuito, Carrera> crearCarrera =
                (posMapIds, ptsMapIds, circuito, nombreCarrera, tipoCarrera) -> {
                    Map<Piloto, String> posPilotoMap = new HashMap<>();
                    Map<Piloto, Integer> ptsPilotoMap = new HashMap<>();
                    Map<Equipo, Integer> ptsEquipoMap = new HashMap<>();

                    posMapIds.forEach((pilotoId, posicion) -> {
                        Piloto p = pilotosMap.get(pilotoId);
                        if (p != null) {
                            posPilotoMap.put(p, posicion); // Usa el objeto Piloto correcto como clave
                            int puntos = ptsMapIds.getOrDefault(pilotoId, 0);
                            ptsPilotoMap.put(p, puntos); // Usa el objeto Piloto correcto como clave
                            if (p.getEquipo() != null && puntos > 0) {
                                // Asegurarse de usar la instancia correcta de Equipo
                                ptsEquipoMap.put(p.getEquipo(), ptsEquipoMap.getOrDefault(p.getEquipo(), 0) + puntos);
                            }
                        }
                    });

                    // Agrega pilotos que participaron pero no están en posMapIds (e.g., DNF sin posición clara)
                    // Esto asume que los mapas provistos ya contienen a todos los participantes relevantes.
                    // Si necesitas incluir a TODOS los que tomaron la salida, incluso sin resultado,
                    // se necesitaría una lista explícita de participantes por carrera.

                    return new Carrera(nombreCarrera, circuito,
                            tipoCarrera.equals("Sprint") ? circuito.getFechaCarreraSprint() : circuito.getFechaCarreraPrincipal(),
                            tipoCarrera, posPilotoMap, ptsPilotoMap, ptsEquipoMap);
                };

        // --- 1. GP de Bahrein ---
        Circuito c1 = circuitosMap.get("Bahrain International Circuit");
        Map<Integer, String> pos1 = Map.ofEntries(
                Map.entry(1, "1"), Map.entry(2, "2"), Map.entry(4, "3"), Map.entry(3, "4"), Map.entry(6, "5"),
                Map.entry(7, "6"), Map.entry(5, "7"), Map.entry(8, "8"), Map.entry(9, "9"), Map.entry(10, "10"),
                Map.entry(18, "11"), Map.entry(20, "12"), Map.entry(13, "13"), Map.entry(15, "14"), Map.entry(16, "15"),
                Map.entry(19, "16"), Map.entry(11, "17"), Map.entry(12, "18"), Map.entry(17, "19"), Map.entry(14, "20")
        );
        Map<Integer, Integer> pts1 = Map.ofEntries(
                Map.entry(1, 26), Map.entry(2, 18), Map.entry(4, 15), Map.entry(3, 12), Map.entry(6, 10), // Verstappen +1 FL
                Map.entry(7, 8), Map.entry(5, 6), Map.entry(8, 4), Map.entry(9, 2), Map.entry(10, 1)
        );
        if (c1 != null) carreras.add(crearCarrera.apply(pos1, pts1, c1, "GP de Bahrein", "Principal"));

        // --- 2. GP Arabia Saudita ---
        Circuito c2 = circuitosMap.get("Jeddah Corniche Circuit");
        Map<Integer, String> pos2 = Map.ofEntries(
                Map.entry(1, "1"), Map.entry(2, "2"), Map.entry(3, "3"), Map.entry(8, "4"), Map.entry(9, "5"),
                Map.entry(6, "6"), Map.entry(21, "7"), Map.entry(7, "8"), Map.entry(5, "9"), Map.entry(19, "10"), // Bearman #21 sustituye a Sainz #4
                Map.entry(13, "11"), Map.entry(20, "12"), Map.entry(11, "13"), Map.entry(16, "14"), Map.entry(12, "15"),
                Map.entry(18, "16"), Map.entry(17, "17"), Map.entry(15, "18"), Map.entry(10, "DNF"), Map.entry(14, "DNF")
        );
        Map<Integer, Integer> pts2 = Map.ofEntries(
                Map.entry(1, 25), Map.entry(2, 18), Map.entry(3, 16), Map.entry(8, 12), Map.entry(9, 10), // Leclerc +1 FL
                Map.entry(6, 8), Map.entry(21, 6), Map.entry(7, 4), Map.entry(5, 2), Map.entry(19, 1)
        );
        if (c2 != null) carreras.add(crearCarrera.apply(pos2, pts2, c2, "GP de Arabia Saudita", "Principal"));

        // --- 3. GP Australia ---
        Circuito c3 = circuitosMap.get("Albert Park Circuit");
        Map<Integer, String> pos3 = Map.ofEntries(
                Map.entry(4, "1"), Map.entry(3, "2"), Map.entry(7, "3"), Map.entry(8, "4"), Map.entry(2, "5"),
                Map.entry(10, "6"), Map.entry(16, "7"), Map.entry(9, "8"), Map.entry(20, "9"), Map.entry(19, "10"),
                Map.entry(17, "11"), Map.entry(15, "12"), Map.entry(12, "13"), Map.entry(18, "14"), Map.entry(11, "15"),
                Map.entry(1, "DNF"), Map.entry(5, "DNF"), Map.entry(6, "DNF") // Sargeant (14) no participó
        );
        Map<Integer, Integer> pts3 = Map.ofEntries(
                Map.entry(4, 25), Map.entry(3, 19), Map.entry(7, 15), Map.entry(8, 12), Map.entry(2, 10), // Leclerc +1 FL
                Map.entry(10, 8), Map.entry(16, 6), Map.entry(9, 4), Map.entry(20, 2), Map.entry(19, 1)
        );
        if (c3 != null) carreras.add(crearCarrera.apply(pos3, pts3, c3, "GP de Australia", "Principal"));

        // --- 4. GP Japón ---
        Circuito c4 = circuitosMap.get("Suzuka International Racing Course");
        Map<Integer, String> pos4 = Map.ofEntries(
                Map.entry(1, "1"), Map.entry(2, "2"), Map.entry(4, "3"), Map.entry(3, "4"), Map.entry(7, "5"),
                Map.entry(9, "6"), Map.entry(6, "7"), Map.entry(8, "8"), Map.entry(16, "9"), Map.entry(19, "10"),
                Map.entry(10, "11"), Map.entry(12, "12"), Map.entry(20, "13"), Map.entry(11, "14"), Map.entry(17, "15"),
                Map.entry(18, "16"), Map.entry(5, "17"), Map.entry(15, "DNF"), Map.entry(13, "DNF"), Map.entry(14, "DNF")
        );
        Map<Integer, Integer> pts4 = Map.ofEntries(
                Map.entry(1, 26), Map.entry(2, 18), Map.entry(4, 15), Map.entry(3, 12), Map.entry(7, 10), // Verstappen +1 FL
                Map.entry(9, 8), Map.entry(6, 6), Map.entry(8, 4), Map.entry(16, 2), Map.entry(19, 1)
        );
        if (c4 != null) carreras.add(crearCarrera.apply(pos4, pts4, c4, "GP de Japón", "Principal"));

        // --- 5. GP China ---
        Circuito c5 = circuitosMap.get("Shanghai International Circuit");
        Map<Integer, String> pos5 = Map.ofEntries(
                Map.entry(1, "1"), Map.entry(7, "2"), Map.entry(2, "3"), Map.entry(3, "4"), Map.entry(6, "5"),
                Map.entry(9, "6"), Map.entry(5, "7"), Map.entry(8, "8"), Map.entry(19, "9"), Map.entry(12, "10"),
                Map.entry(11, "11"), Map.entry(13, "12"), Map.entry(10, "13"), Map.entry(20, "14"), Map.entry(18, "15"),
                Map.entry(17, "16"), Map.entry(14, "17"), Map.entry(4, "DNF"), Map.entry(15, "DNF"), Map.entry(16, "DNF")
        );
        Map<Integer, Integer> pts5 = Map.ofEntries(
                Map.entry(1, 25), Map.entry(7, 18), Map.entry(2, 15), Map.entry(3, 12), Map.entry(6, 10),
                Map.entry(9, 9), Map.entry(5, 6), Map.entry(8, 4), Map.entry(19, 2), Map.entry(12, 1) // Alonso +1 FL
        );
        if (c5 != null) carreras.add(crearCarrera.apply(pos5, pts5, c5, "GP de China", "Principal"));

        // --- 6. GP Miami ---
        Circuito c6 = circuitosMap.get("Miami International Autodrome");
        Map<Integer, String> pos6 = Map.ofEntries(
                Map.entry(7, "1"), Map.entry(1, "2"), Map.entry(3, "3"), Map.entry(2, "4"), Map.entry(4, "5"),
                Map.entry(5, "6"), Map.entry(16, "7"), Map.entry(6, "8"), Map.entry(9, "9"), Map.entry(19, "10"),
                Map.entry(11, "11"), Map.entry(12, "12"), Map.entry(8, "13"), Map.entry(20, "14"), Map.entry(13, "15"),
                Map.entry(17, "16"), Map.entry(10, "17"), Map.entry(18, "18"), Map.entry(15, "19"), Map.entry(14, "DNF")
        );
        Map<Integer, Integer> pts6 = Map.ofEntries(
                Map.entry(7, 25), Map.entry(1, 18), Map.entry(3, 15), Map.entry(2, 12), Map.entry(4, 10),
                Map.entry(5, 8), Map.entry(16, 6), Map.entry(6, 4), Map.entry(9, 2), Map.entry(19, 1), Map.entry(8,1) // Piastri +1 FL
        );
        if (c6 != null) carreras.add(crearCarrera.apply(pos6, pts6, c6, "GP de Miami", "Principal"));

        // --- 7. GP Emilia Romagna ---
        Circuito c7 = circuitosMap.get("Imola Circuit");
        Map<Integer, String> pos7 = Map.ofEntries(
                Map.entry(1, "1"), Map.entry(7, "2"), Map.entry(3, "3"), Map.entry(8, "4"), Map.entry(4, "5"),
                Map.entry(5, "6"), Map.entry(2, "7"), Map.entry(6, "8"), Map.entry(10, "9"), Map.entry(16, "10"),
                Map.entry(19, "11"), Map.entry(20, "12"), Map.entry(15, "13"), Map.entry(12, "14"), Map.entry(18, "15"),
                Map.entry(11, "16"), Map.entry(17, "17"), Map.entry(14, "18"), Map.entry(9, "19"), Map.entry(13, "DNF")
        );
        Map<Integer, Integer> pts7 = Map.ofEntries(
                Map.entry(1, 25), Map.entry(7, 18), Map.entry(3, 15), Map.entry(8, 12), Map.entry(4, 10),
                Map.entry(5, 8), Map.entry(2, 6), Map.entry(6, 5), Map.entry(10, 2), Map.entry(16, 1) // Russell +1 FL
        );
        if (c7 != null) carreras.add(crearCarrera.apply(pos7, pts7, c7, "GP de Emilia Romagna", "Principal"));

        // --- 8. GP Mónaco ---
        Circuito c8 = circuitosMap.get("Circuit de Monaco");
        Map<Integer, String> pos8 = Map.ofEntries(
                Map.entry(3, "1"), Map.entry(8, "2"), Map.entry(4, "3"), Map.entry(7, "4"), Map.entry(6, "5"),
                Map.entry(1, "6"), Map.entry(16, "7"), Map.entry(5, "8"), Map.entry(12, "9"), Map.entry(11, "10"),
                Map.entry(10, "11"), Map.entry(9, "12"), Map.entry(17, "13"), Map.entry(15, "14"), Map.entry(14, "15"),
                Map.entry(18, "16"), Map.entry(2, "DNF"), Map.entry(19, "DNF"), Map.entry(20, "DNF"), Map.entry(13, "DNF")
        );
        Map<Integer, Integer> pts8 = Map.ofEntries(
                Map.entry(3, 25), Map.entry(8, 18), Map.entry(4, 15), Map.entry(7, 12), Map.entry(6, 10),
                Map.entry(1, 8), Map.entry(16, 6), Map.entry(5, 5), Map.entry(12, 2), Map.entry(11, 1) // Hamilton +1 FL
        );
        if (c8 != null) carreras.add(crearCarrera.apply(pos8, pts8, c8, "GP de Mónaco", "Principal"));

        // --- 9. GP Canadá ---
        Circuito c9 = circuitosMap.get("Circuit Gilles Villeneuve");
        Map<Integer, String> pos9 = Map.ofEntries(
                Map.entry(1, "1"), Map.entry(7, "2"), Map.entry(6, "3"), Map.entry(5, "4"), Map.entry(8, "5"),
                Map.entry(9, "6"), Map.entry(15, "7"), Map.entry(16, "8"), Map.entry(12, "9"), Map.entry(11, "10"),
                Map.entry(19, "11"), Map.entry(17, "12"), Map.entry(10, "13"), Map.entry(20, "14"), Map.entry(18, "15"),
                Map.entry(2, "DNF"), Map.entry(13, "DNF"), Map.entry(4, "DNF"), Map.entry(3, "DNF"), Map.entry(14, "DNF")
        );
        Map<Integer, Integer> pts9 = Map.ofEntries(
                Map.entry(1, 25), Map.entry(7, 18), Map.entry(6, 15), Map.entry(5, 13), Map.entry(8, 10), // Hamilton +1 FL
                Map.entry(9, 8), Map.entry(15, 6), Map.entry(16, 4), Map.entry(12, 2), Map.entry(11, 1)
        );
        if (c9 != null) carreras.add(crearCarrera.apply(pos9, pts9, c9, "GP de Canadá", "Principal"));

        // --- 10. GP España ---
        Circuito c10 = circuitosMap.get("Circuit de Barcelona-Catalunya");
        Map<Integer, String> pos10 = Map.ofEntries(
                Map.entry(1, "1"), Map.entry(7, "2"), Map.entry(5, "3"), Map.entry(6, "4"), Map.entry(3, "5"),
                Map.entry(4, "6"), Map.entry(12, "7"), Map.entry(8, "8"), Map.entry(2, "9"), Map.entry(11, "10"),
                Map.entry(19, "11"), Map.entry(10, "12"), Map.entry(15, "13"), Map.entry(9, "14"), Map.entry(17, "15"),
                Map.entry(20, "16"), Map.entry(18, "17"), Map.entry(13, "18"), Map.entry(14, "19"), Map.entry(16, "20")
        );
        Map<Integer, Integer> pts10 = Map.ofEntries(
                Map.entry(1, 25), Map.entry(7, 19), Map.entry(5, 15), Map.entry(6, 12), Map.entry(3, 10), // Norris +1 FL
                Map.entry(4, 8), Map.entry(12, 6), Map.entry(8, 4), Map.entry(2, 2), Map.entry(11, 1)
        );
        if (c10 != null) carreras.add(crearCarrera.apply(pos10, pts10, c10, "GP de España", "Principal"));

        // --- 11. GP Austria ---
        Circuito c11 = circuitosMap.get("Red Bull Ring");
        Map<Integer, String> pos11 = Map.ofEntries(
                Map.entry(6, "1"), Map.entry(8, "2"), Map.entry(7, "3"), Map.entry(1, "4"), Map.entry(5, "5"),
                Map.entry(3, "6"), Map.entry(4, "7"), Map.entry(2, "8"), Map.entry(12, "9"), Map.entry(11, "10"),
                Map.entry(10, "11"), Map.entry(19, "12"), Map.entry(16, "13"), Map.entry(20, "14"), Map.entry(15, "15"),
                Map.entry(17, "16"), Map.entry(13, "17"), Map.entry(18, "18"), Map.entry(9, "19"), Map.entry(14, "20")
        );
        Map<Integer, Integer> pts11 = Map.ofEntries(
                Map.entry(6, 25), Map.entry(8, 18), Map.entry(7, 15), Map.entry(1, 12), Map.entry(5, 10),
                Map.entry(3, 8), Map.entry(4, 6), Map.entry(2, 4), Map.entry(12, 2), Map.entry(11, 1), Map.entry(9,1) // Alonso +1 FL
        );
        if (c11 != null) carreras.add(crearCarrera.apply(pos11, pts11, c11, "GP de Austria", "Principal"));

        // --- 12. GP Gran Bretaña ---
        Circuito c12 = circuitosMap.get("Silverstone Circuit");
        Map<Integer, String> pos12 = Map.ofEntries(
                Map.entry(5, "1"), Map.entry(1, "2"), Map.entry(7, "3"), Map.entry(8, "4"), Map.entry(6, "5"),
                Map.entry(19, "6"), Map.entry(4, "7"), Map.entry(10, "8"), Map.entry(13, "9"), Map.entry(12, "10"),
                Map.entry(11, "11"), Map.entry(15, "12"), Map.entry(9, "13"), Map.entry(16, "14"), Map.entry(17, "15"),
                Map.entry(18, "16"), Map.entry(20, "17"), Map.entry(2, "DNF"), Map.entry(3, "DNF") // Sargeant (14) no corrió
        );
        Map<Integer, Integer> pts12 = Map.ofEntries(
                Map.entry(5, 25), Map.entry(1, 18), Map.entry(7, 15), Map.entry(8, 12), Map.entry(6, 10),
                Map.entry(19, 8), Map.entry(4, 7), Map.entry(10, 4), Map.entry(13, 2), Map.entry(12, 1) // Sainz +1 FL
        );
        if (c12 != null) carreras.add(crearCarrera.apply(pos12, pts12, c12, "GP de Gran Bretaña", "Principal"));

        // --- 13. GP Hungría ---
        Circuito c13 = circuitosMap.get("Hungaroring");
        Map<Integer, String> pos13 = Map.ofEntries(
                Map.entry(8, "1"), Map.entry(7, "2"), Map.entry(1, "3"), Map.entry(6, "4"), Map.entry(3, "5"),
                Map.entry(2, "6"), Map.entry(5, "7"), Map.entry(4, "8"), Map.entry(9, "9"), Map.entry(10, "10"),
                Map.entry(16, "11"), Map.entry(15, "12"), Map.entry(19, "13"), Map.entry(11, "14"), Map.entry(12, "15"),
                Map.entry(20, "16"), Map.entry(17, "17"), Map.entry(18, "18"), Map.entry(13, "19"), Map.entry(14, "20")
        );
        Map<Integer, Integer> pts13 = Map.ofEntries(
                Map.entry(8, 25), Map.entry(7, 18), Map.entry(1, 12), Map.entry(6, 11), Map.entry(3, 10), // Russell +1 FL
                Map.entry(2, 8), Map.entry(5, 6), Map.entry(4, 4), Map.entry(9, 2), Map.entry(10, 1)
        );
        if (c13 != null) carreras.add(crearCarrera.apply(pos13, pts13, c13, "GP de Hungría", "Principal"));

        // --- 14. GP Bélgica ---
        Circuito c14 = circuitosMap.get("Circuit de Spa-Francorchamps");
        Map<Integer, String> pos14 = Map.ofEntries(
                Map.entry(5, "1"), Map.entry(6, "2"), Map.entry(3, "3"), Map.entry(7, "4"), Map.entry(1, "5"),
                Map.entry(8, "6"), Map.entry(4, "7"), Map.entry(9, "8"), Map.entry(12, "9"), Map.entry(11, "10"),
                Map.entry(10, "11"), Map.entry(19, "12"), Map.entry(16, "13"), Map.entry(15, "14"), Map.entry(13, "15"),
                Map.entry(20, "16"), Map.entry(18, "17"), Map.entry(17, "18"), Map.entry(2, "DNF"), Map.entry(14, "DNF")
        );
        Map<Integer, Integer> pts14 = Map.ofEntries(
                Map.entry(5, 25), Map.entry(6, 18), Map.entry(3, 15), Map.entry(7, 12), Map.entry(1, 10),
                Map.entry(8, 8), Map.entry(4, 6), Map.entry(9, 4), Map.entry(12, 2), Map.entry(11, 1), Map.entry(2,1) // Perez +1 FL
        );
        if (c14 != null) carreras.add(crearCarrera.apply(pos14, pts14, c14, "GP de Bélgica", "Principal"));

        // --- 15. GP Países Bajos ---
        Circuito c15 = circuitosMap.get("Circuit Zandvoort");
        Map<Integer, String> pos15 = Map.ofEntries(
                Map.entry(7, "1"), Map.entry(1, "2"), Map.entry(3, "3"), Map.entry(8, "4"), Map.entry(4, "5"),
                Map.entry(6, "6"), Map.entry(2, "7"), Map.entry(9, "8"), Map.entry(20, "9"), Map.entry(19, "10"),
                Map.entry(18, "11"), Map.entry(12, "12"), Map.entry(15, "13"), Map.entry(11, "14"), Map.entry(17, "15"),
                Map.entry(16, "16"), Map.entry(10, "17"), Map.entry(13, "18"), Map.entry(5, "19"), Map.entry(14, "DNF")
        );
        Map<Integer, Integer> pts15 = Map.ofEntries(
                Map.entry(7, 26), Map.entry(1, 18), Map.entry(3, 15), Map.entry(8, 12), Map.entry(4, 10), // Norris +1 FL
                Map.entry(6, 8), Map.entry(2, 6), Map.entry(9, 4), Map.entry(20, 2), Map.entry(19, 1)
        );
        if (c15 != null) carreras.add(crearCarrera.apply(pos15, pts15, c15, "GP de Países Bajos", "Principal"));

        // --- 16. GP Italia ---
        Circuito c16 = circuitosMap.get("Monza Circuit");
        // Colapinto #22 sustituye a Sargeant #14 desde esta carrera
        Map<Integer, String> pos16 = Map.ofEntries(
                Map.entry(3, "1"), Map.entry(8, "2"), Map.entry(7, "3"), Map.entry(4, "4"), Map.entry(6, "5"),
                Map.entry(1, "6"), Map.entry(5, "7"), Map.entry(2, "8"), Map.entry(20, "9"), Map.entry(19, "10"),
                Map.entry(9, "11"), Map.entry(22, "12"), Map.entry(17, "13"), Map.entry(10, "14"), Map.entry(12, "15"),
                Map.entry(18, "16"), Map.entry(11, "17"), Map.entry(15, "18"), Map.entry(13, "19"), Map.entry(16, "DNF")
        );
        Map<Integer, Integer> pts16 = Map.ofEntries(
                Map.entry(3, 25), Map.entry(8, 18), Map.entry(7, 16), Map.entry(4, 12), Map.entry(6, 10), // Norris +1 FL
                Map.entry(1, 8), Map.entry(5, 6), Map.entry(2, 4), Map.entry(20, 2), Map.entry(19, 1)
        );
        if (c16 != null) carreras.add(crearCarrera.apply(pos16, pts16, c16, "GP de Italia", "Principal"));

        // --- 17. GP Azerbaiyán ---
        Circuito c17 = circuitosMap.get("Baku City Circuit");
        // Bearman #21 sustituye a Magnussen #20 (suspendido)
        Map<Integer, String> pos17 = Map.ofEntries(
                Map.entry(8, "1"), Map.entry(3, "2"), Map.entry(2, "3"), Map.entry(4, "4"), Map.entry(7, "5"),
                Map.entry(1, "6"), Map.entry(5, "7"), Map.entry(9, "8"), Map.entry(6, "9"), Map.entry(21, "10"),
                Map.entry(19, "11"), Map.entry(13, "12"), Map.entry(16, "13"), Map.entry(11, "14"), Map.entry(10, "15"),
                Map.entry(15, "16"), Map.entry(17, "17"), Map.entry(18, "18"), Map.entry(12, "19"), Map.entry(22, "DNF")
        );
        Map<Integer, Integer> pts17 = Map.ofEntries(
                Map.entry(8, 25), Map.entry(3, 18), Map.entry(2, 15), Map.entry(4, 12), Map.entry(7, 11), // Norris +1 FL
                Map.entry(1, 8), Map.entry(5, 6), Map.entry(9, 4), Map.entry(6, 2), Map.entry(21, 1)
        );
        if (c17 != null) carreras.add(crearCarrera.apply(pos17, pts17, c17, "GP de Azerbaiyán", "Principal"));

        // --- 18. GP Singapur ---
        Circuito c18 = circuitosMap.get("Marina Bay Street Circuit");
        Map<Integer, String> pos18 = Map.ofEntries(
                Map.entry(7, "1"), Map.entry(1, "2"), Map.entry(8, "3"), Map.entry(3, "4"), Map.entry(6, "5"),
                Map.entry(4, "6"), Map.entry(2, "7"), Map.entry(16, "8"), Map.entry(19, "9"), Map.entry(20, "10"),
                Map.entry(10, "11"), Map.entry(13, "12"), Map.entry(11, "13"), Map.entry(17, "14"), Map.entry(22, "15"),
                Map.entry(18, "16"), Map.entry(9, "17"), Map.entry(5, "DNF"), Map.entry(15, "DNF"), Map.entry(12, "DSQ") // Gasly #12 DSQ
        );
        Map<Integer, Integer> pts18 = Map.ofEntries(
                Map.entry(7, 25), Map.entry(1, 18), Map.entry(8, 15), Map.entry(3, 12), Map.entry(6, 10),
                Map.entry(4, 8), Map.entry(2, 6), Map.entry(16, 4), Map.entry(19, 2), Map.entry(20, 1), Map.entry(15, 1) // Ricciardo +1 FL
        );
        if (c18 != null) carreras.add(crearCarrera.apply(pos18, pts18, c18, "GP de Singapur", "Principal"));

        // --- 19. GP Estados Unidos (Austin) ---
        Circuito c19 = circuitosMap.get("Circuit of the Americas");
        Map<Integer, String> pos19 = Map.ofEntries(
                Map.entry(3, "1"), Map.entry(7, "2"), Map.entry(1, "3"), Map.entry(2, "4"), Map.entry(6, "5"),
                Map.entry(8, "6"), Map.entry(4, "7"), Map.entry(12, "8"), Map.entry(16, "9"), Map.entry(19, "10"),
                Map.entry(9, "11"), Map.entry(11, "12"), Map.entry(13, "13"), Map.entry(22, "14"), Map.entry(17, "15"),
                Map.entry(15, "16"), Map.entry(18, "17"), Map.entry(20, "18"), Map.entry(10, "19"), Map.entry(5, "DSQ") // Hamilton #5 DSQ
        );
        Map<Integer, Integer> pts19 = Map.ofEntries(
                Map.entry(3, 25), Map.entry(7, 18), Map.entry(1, 15), Map.entry(2, 12), Map.entry(6, 10),
                Map.entry(8, 8), Map.entry(4, 6), Map.entry(12, 4), Map.entry(16, 2), Map.entry(19, 1), Map.entry(9, 1) // Alonso +1 FL
        );
        if (c19 != null) carreras.add(crearCarrera.apply(pos19, pts19, c19, "GP de Estados Unidos", "Principal"));

        // --- 20. GP México ---
        Circuito c20 = circuitosMap.get("Autódromo Hermanos Rodríguez");
        Map<Integer, String> pos20 = Map.ofEntries(
                Map.entry(4, "1"), Map.entry(3, "2"), Map.entry(1, "3"), Map.entry(6, "4"), Map.entry(7, "5"),
                Map.entry(8, "6"), Map.entry(20, "7"), Map.entry(19, "8"), Map.entry(11, "9"), Map.entry(12, "10"),
                Map.entry(10, "11"), Map.entry(15, "12"), Map.entry(13, "13"), Map.entry(22, "14"), Map.entry(17, "15"),
                Map.entry(18, "16"), Map.entry(2, "17"), Map.entry(9, "DNF"), Map.entry(16, "DNF"), Map.entry(5, "DNF")
        );
        Map<Integer, Integer> pts20 = Map.ofEntries(
                Map.entry(4, 25), Map.entry(3, 19), Map.entry(1, 15), Map.entry(6, 12), Map.entry(7, 10), // Leclerc +1 FL
                Map.entry(8, 8), Map.entry(20, 6), Map.entry(19, 4), Map.entry(11, 2), Map.entry(12, 1)
        );
        if (c20 != null) carreras.add(crearCarrera.apply(pos20, pts20, c20, "GP de México", "Principal"));

        // --- 21. GP Brasil (São Paulo) ---
        Circuito c21 = circuitosMap.get("Interlagos Circuit");
        // Bearman #21 sustituye a Magnussen #20 (enfermo)
        Map<Integer, String> pos21 = Map.ofEntries(
                Map.entry(1, "1"), Map.entry(11, "2"), Map.entry(12, "3"), Map.entry(6, "4"), Map.entry(3, "5"),
                Map.entry(7, "6"), Map.entry(8, "7"), Map.entry(15, "8"), Map.entry(19, "9"), Map.entry(10, "10"),
                Map.entry(2, "11"), Map.entry(21, "12"), Map.entry(9, "13"), Map.entry(16, "14"), Map.entry(13, "15"),
                Map.entry(22, "16"), Map.entry(17, "17"), Map.entry(18, "18"), Map.entry(4, "19"), Map.entry(5, "DNF")
        );
        Map<Integer, Integer> pts21 = Map.ofEntries(
                Map.entry(1, 26), Map.entry(11, 18), Map.entry(12, 15), Map.entry(6, 12), Map.entry(3, 10), // Verstappen +1 FL
                Map.entry(7, 8), Map.entry(8, 6), Map.entry(15, 4), Map.entry(19, 2), Map.entry(10, 1)
        );
        if (c21 != null) carreras.add(crearCarrera.apply(pos21, pts21, c21, "GP de Brasil", "Principal"));

        // --- 22. GP Las Vegas ---
        Circuito c22 = circuitosMap.get("Las Vegas Strip Circuit");
        Map<Integer, String> pos22 = Map.ofEntries(
                Map.entry(6, "1"), Map.entry(3, "2"), Map.entry(4, "3"), Map.entry(7, "4"), Map.entry(1, "5"),
                Map.entry(5, "6"), Map.entry(20, "7"), Map.entry(19, "8"), Map.entry(16, "9"), Map.entry(8, "10"),
                Map.entry(12, "11"), Map.entry(10, "12"), Map.entry(13, "13"), Map.entry(18, "14"), Map.entry(22, "15"),
                Map.entry(17, "16"), Map.entry(15, "17"), Map.entry(11, "18"), Map.entry(9, "19"), Map.entry(2, "20")
        );
        Map<Integer, Integer> pts22 = Map.ofEntries(
                Map.entry(6, 25), Map.entry(3, 18), Map.entry(4, 15), Map.entry(7, 13), Map.entry(1, 10), // Norris +1 FL (corregido, era Norris)
                Map.entry(5, 8), Map.entry(20, 6), Map.entry(19, 4), Map.entry(16, 2), Map.entry(8, 1)
        );
        if (c22 != null) carreras.add(crearCarrera.apply(pos22, pts22, c22, "GP de Las Vegas", "Principal"));

        // --- 23. GP Qatar ---
        Circuito c23 = circuitosMap.get("Lusail International Circuit");
        Map<Integer, String> pos23 = Map.ofEntries(
                Map.entry(1, "1"), Map.entry(8, "2"), Map.entry(3, "3"), Map.entry(7, "4"), Map.entry(4, "5"),
                Map.entry(6, "6"), Map.entry(2, "7"), Map.entry(19, "8"), Map.entry(20, "9"), Map.entry(5, "10"),
                Map.entry(9, "11"), Map.entry(10, "12"), Map.entry(11, "13"), Map.entry(15, "14"), Map.entry(13, "15"),
                Map.entry(22, "16"), Map.entry(16, "17"), Map.entry(17, "18"), Map.entry(18, "19"), Map.entry(12, "20")
        );
        Map<Integer, Integer> pts23 = Map.ofEntries(
                Map.entry(1, 25), Map.entry(8, 18), Map.entry(3, 15), Map.entry(7, 13), Map.entry(4, 10), // Norris +1 FL
                Map.entry(6, 8), Map.entry(2, 6), Map.entry(19, 4), Map.entry(20, 2), Map.entry(5, 1)
        );
        if (c23 != null) carreras.add(crearCarrera.apply(pos23, pts23, c23, "GP de Qatar", "Principal"));

        // --- 24. GP Abu Dhabi ---
        Circuito c24 = circuitosMap.get("Yas Marina Circuit");
        // Doohan #23 sustituye a Ocon #11
        Map<Integer, String> pos24 = Map.ofEntries(
                Map.entry(7, "1"), Map.entry(4, "2"), Map.entry(8, "3"), Map.entry(3, "4"), Map.entry(1, "5"),
                Map.entry(6, "6"), Map.entry(2, "7"), Map.entry(12, "8"), Map.entry(9, "9"), Map.entry(5, "10"),
                Map.entry(19, "11"), Map.entry(16, "12"), Map.entry(15, "13"), Map.entry(10, "14"), Map.entry(23, "15"),
                Map.entry(13, "16"), Map.entry(18, "17"), Map.entry(17, "18"), Map.entry(22, "19"), Map.entry(20, "DNF")
        );
        Map<Integer, Integer> pts24 = Map.ofEntries(
                Map.entry(7, 25), Map.entry(4, 18), Map.entry(8, 15), Map.entry(3, 12), Map.entry(1, 10),
                Map.entry(6, 8), Map.entry(2, 6), Map.entry(12, 4), Map.entry(9, 2), Map.entry(5, 1), Map.entry(20, 1) // Magnussen +1 FL
        );
        if (c24 != null) carreras.add(crearCarrera.apply(pos24, pts24, c24, "GP de Abu Dhabi", "Principal"));

        return carreras;
    }

    // Helper TriFunction (Interfaz funcional para 3 argumentos + retorno)
    @FunctionalInterface
    private interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v, String n, String ty);
    }
}