# F1 Management System

A comprehensive Formula 1 data management and visualization system built in Java, featuring real-time statistics, interactive comparisons, and detailed analytics for the 2024 F1 season.

## 🏎️ Features

### Data Management
- Complete F1 2024 season data management
- Real-time statistics tracking
- Comprehensive driver and team information
- Circuit details with precise measurements
- Race results and standings

### Interactive Comparisons
#### Driver Comparison
- **Simple Comparison**: Direct points and position comparison
- **Visual Comparison**: Detailed statistical analysis including:
  - Victories
  - Podiums
  - Pole positions
  - Fastest laps
  - Total points

#### Team Comparison
- **Simple Comparison**: Direct championship points comparison
- **Visual Comparison**: Comprehensive team statistics including:
  - Total points
  - Race victories
  - Last victory date
  - Historical championships

### User Interface
- Intuitive command-line interface
- ASCII art banner for enhanced visual appeal
- Unicode-based table formatting
- Dynamic spacing and alignment
- Error-resistant input handling

## 💻 Technical Implementation

### Core Technologies
- Java 17+
- Object-Oriented Programming
- Stream API for data processing
- LocalDate for date management
- Custom comparators for sorting

### Key Design Patterns
- **Data Access Layer**: Separate data loading and management
- **Model-View Pattern**: Clear separation of data and presentation
- **Factory Pattern**: For object creation and data initialization
- **Strategy Pattern**: For different comparison methods

### Code Quality
- Comprehensive error handling
- Input validation at all levels
- Clean code principles
- Consistent formatting
- Detailed documentation

## 🛠️ Technical Features

### Data Processing
```java
// Efficient data filtering using Stream API
List<Carrera> filtradas = carreras.stream()
    .filter(c -> c.getNombre().equalsIgnoreCase(filtro) ||
                fechaCoincide(c.getFecha(), filtro))
    .toList();
```

### Dynamic Formatting
```java
// Unicode-based table formatting with dynamic spacing
System.out.printf("║ %-30s │ %-20.1f ║\n", 
    equipo.getNombreCompleto(), 
    equipo.getPuntos2024());
```

### Error Handling
```java
try {
    int id = Integer.parseInt(input);
    // Process valid input
} catch (NumberFormatException e) {
    // Handle invalid input
} finally {
    scanner.nextLine(); // Buffer cleanup
}
```

## 📊 Data Visualization

### Circuit Information
```
╔════════════════════════════════════════════════════════════╗
║ 📏 CIRCUIT CHARACTERISTICS                                ║
╟────────────────────────────────────────────────────────────╢
║ Length: 5412 meters                                       ║
║ Laps: 57                                                 ║
║ Total Distance: 308484 meters                            ║
╚════════════════════════════════════════════════════════════╝
```

### Driver Comparison
```
╔════════════════════════════════════════════════════════════╗
║ 📊 DRIVER COMPARISON                                      ║
╟────────────────────────────────────────────────────────────╢
║ [Driver 1] leads [Driver 2] by [X] points                 ║
╟────────────────────────────────────────────────────────────╢
║ DRIVER                    │ POINTS 2024                   ║
╟────────────────────────────────────────────────────────────╢
║ [Driver 1]               │ [Points]                       ║
║ [Driver 2]               │ [Points]                       ║
╚════════════════════════════════════════════════════════════╝
```

## 🔍 Key Methods

### Data Management
- `CargadorDatos.cargarEquipos()`: Team data loading
- `CargadorDatos.cargarPilotos()`: Driver data loading
- `CargadorDatos.cargarCircuitos()`: Circuit data loading
- `CargadorDatos.cargarCarreras()`: Race data loading

### Comparison Methods
- `compararPilotos()`: Driver comparison logic
- `compararEquipos()`: Team comparison logic
- `mostrarComparacionSimplePilotos()`: Simple driver comparison
- `mostrarComparacionSimpleEquipos()`: Simple team comparison

### Data Display
- `mostrarInfoCircuito()`: Circuit information display
- `mostrarInfoPiloto()`: Driver information display
- `mostrarInfoEquipo()`: Team information display

## 🚀 Future Enhancements
- Database integration for persistent storage
- REST API for remote access
- Real-time data updates
- Historical data analysis
- Predictive analytics
- Web interface

## 🛠️ Development Setup
1. Clone the repository
2. Ensure Java 17+ is installed
3. Compile the project:
   ```bash
   javac Main.java
   ```
4. Run the application:
   ```bash
   java Main
   ```

## 📝 License
This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Contributing
Contributions are welcome! Please feel free to submit a Pull Request. 