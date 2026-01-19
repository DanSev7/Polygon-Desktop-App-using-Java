# Polygon Calculator Application - Project Summary

## Table of Contents
1. [GUI Framework Comparison: AWT vs Swing vs JavaFX](#gui-framework-comparison)
2. [Project Architecture](#project-architecture)
3. [GUI Implementation Analysis](#gui-implementation-analysis)
4. [Logic Classes Explanation](#logic-classes-explanation)
5. [Project Purpose and Functionality](#project-purpose-and-functionality)

## GUI Framework Comparison

### AWT (Abstract Window Toolkit)
AWT is Java's original platform-dependent windowing toolkit. It was the first GUI framework provided with Java and relies heavily on native peers (native GUI components) for rendering. Key characteristics include:

- **Platform dependency**: Uses native system components, resulting in native look and feel
- **Limited components**: Provides basic components like Button, TextField, Label, etc.
- **Less flexible**: Offers limited customization options
- **Simple architecture**: Direct mapping to native GUI components
- **Drawbacks**: Platform-specific appearance and limited functionality

### Swing
Swing is built on top of AWT and provides a more sophisticated and flexible GUI framework. It's a platform-independent toolkit that offers rich components and features:

- **Lightweight components**: Does not rely on native peers, drawn entirely in Java
- **Rich component set**: JButtons, JTextFields, JTables, JTree, etc.
- **Pluggable look and feel**: Can mimic different OS appearances
- **MVC architecture**: Separates data model from presentation
- **Advanced features**: Better graphics, drag and drop, accessibility support
- **Still widely used**: For legacy applications and simple desktop applications

### JavaFX
JavaFX is Oracle's modern replacement for Swing, designed for creating rich internet applications and desktop applications:

- **Modern graphics engine**: Uses hardware acceleration via Prism graphics engine
- **CSS styling**: Supports Cascading Style Sheets for UI customization
- **FXML**: Declarative UI definition using XML-like syntax
- **Scene graph architecture**: Uses nodes in a hierarchical structure
- **Rich media support**: Built-in support for audio, video, and animations
- **Web integration**: Can embed web content using WebView
- **Active development**: Continues to receive updates and improvements

### Framework Used in This Project
This project uses **Swing** for the GUI implementation. Evidence includes:
- Import statements: `javax.swing.*`, `javax.swing.border.*`, `javax.swing.table.*`
- Component classes: `JFrame`, `JPanel`, `JTabbedPane`, `JTextField`, `JTextArea`, `JButton`, `JTable`, `JScrollPane`
- Swing-specific utilities: `SwingUtilities.invokeLater()`

## Project Architecture

### Directory Structure
```
src/
├── Gui/                 # Graphical User Interface components
│   └── PolygonAppUI.java
└── Logic/              # Business Logic and Mathematical Calculations
    ├── Polygon.java
    ├── RegularPolygon.java
    ├── ArbitraryPolygon.java
    └── Prism.java
```

### Package Organization
- **Gui Package**: Contains the user interface implementation using Swing components
- **Logic Package**: Contains the mathematical models and calculations

### Layered Architecture
The project follows a clear Model-View separation of concerns:
- **GUI Layer** ([`PolygonAppUI.java`](file:///c:/Dan/Polygon_123/src/Gui/PolygonAppUI.java)): Handles user interface, input validation, and display
- **Logic Layer** ([`Polygon.java`](file:///c:/Dan/Polygon_123/src/Logic/Polygon.java), [`RegularPolygon.java`](file:///c:/Dan/Polygon_123/src/Logic/RegularPolygon.java), [`ArbitraryPolygon.java`](file:///c:/Dan/Polygon_123/src/Logic/ArbitraryPolygon.java), [`Prism.java`](file:///c:/Dan/Polygon_123/src/Logic/Prism.java)): Contains mathematical calculations and business logic

### Design Patterns Used
- **Inheritance**: [`RegularPolygon`](file:///c:/Dan/Polygon_123/src/Logic/RegularPolygon.java) and [`ArbitraryPolygon`](file:///c:/Dan/Polygon_123/src/Logic/ArbitraryPolygon.java) extend the abstract [`Polygon`](file:///c:/Dan/Polygon_123/src/Logic/Polygon.java) class
- **Encapsulation**: Private fields with public methods for controlled access
- **Abstraction**: Abstract methods in the [`Polygon`](file:///c:/Dan/Polygon_123/src/Logic/Polygon.java) class that are implemented by subclasses

## GUI Implementation Analysis

### PolygonAppUI.java
This is the main entry point and user interface class extending `JFrame`. The class implements the complete graphical user interface:

#### Main Components:
- **Main Window**: Extends `JFrame` with title "Polygon & Prism Calculator"
- **Tabbed Interface**: Uses `JTabbedPane` to provide two calculation modes
- **Thread Management**: Uses `SwingUtilities.invokeLater()` for safe UI updates

#### Key Features:
- **Tabbed Interface**: Uses `JTabbedPane` to provide two calculation modes:
  1. Regular Polygon: For polygons with equal sides
  2. Arbitrary Polygon: For polygons with custom vertex coordinates

#### Regular Polygon Panel ([createRegularPanel()](file:///c:/Dan/Polygon_123/src/Gui/PolygonAppUI.java#L31-L81)):
- Input fields: Number of sides (`nField`), Side length (`sField`), Prism height (`hField`)
- Action buttons: Compute and Clear
- Results display: Scrollable text area with formatted output
- Validation: Ensures n≥3 and side length > 0
- Calculation: Computes area, perimeter, and volume of regular polygon prism

#### Arbitrary Polygon Panel ([createArbitraryPanel()](file:///c:/Dan/Polygon_123/src/Gui/PolygonAppUI.java#L83-L165)):
- Interactive table: `JTable` with `DefaultTableModel` for vertex coordinates (x, y)
- Dynamic controls: Add/remove vertex functionality
- Prism height input field
- Results display area
- Validation: Checks for minimum 3 vertices

#### Technical Implementation Details:
- **Swing Components Used**:
  - `JFrame`, `JPanel`, `JTabbedPane`, `JTextField`, `JTextArea`, `JButton`, `JTable`, `JScrollPane`
  - `DefaultTableModel` for dynamic table management
- **Layout Managers**:
  - `BorderLayout`: Primary panel organization
  - `GridLayout`: Form layout for input fields
  - `FlowLayout`: Button panel arrangement
  - `GridBagLayout`: Advanced control for table controls
- **Event Handling**:
  - Action listeners for compute and clear buttons
  - Dynamic table manipulation (add/remove rows)
- **Error Handling**:
  - `NumberFormatException` for invalid numeric inputs
  - `IllegalArgumentException` for business logic violations
- **Output Formatting**: Uses `DecimalFormat` for precise number representation
- **Input Validation**: Comprehensive validation to prevent calculation errors

## Logic Classes Explanation

### Abstract Class: [Polygon](file:///c:/Dan/Polygon_123/src/Logic/Polygon.java)
- Base abstract class defining the contract for polygon calculations
- Declares abstract methods `getArea()` and `getPerimeter()`
- Serves as the foundation for specialized polygon implementations
- Contains private fields for common polygon properties (n, sideLength, x[], y[])
- Enforces polymorphism by requiring subclasses to implement abstract methods

### RegularPolygon Class ([RegularPolygon.java](file:///c:/Dan/Polygon_123/src/Logic/RegularPolygon.java))
- Represents regular polygons (equal sides and angles)
- Constructor validates inputs with multiple checks:
  - Minimum 3 sides requirement
  - Positive side length requirement
  - Non-negative side length validation
- Implements area calculation using the formula: `(n * s²) / (4 * tan(π/n))`
- Implements perimeter calculation: `n * side_length`
- Inherits from [Polygon](file:///c:/Dan/Polygon_123/src/Logic/Polygon.java) abstract class

### ArbitraryPolygon Class ([ArbitraryPolygon.java](file:///c:/Dan/Polygon_123/src/Logic/ArbitraryPolygon.java))
- Represents polygons with arbitrary vertex coordinates
- Uses the Shoelace formula for area calculation: `|Σ(xi * yi+1 - xi+1 * yi)| / 2`
- Calculates perimeter by summing distances between consecutive vertices using Euclidean distance: `Math.hypot(x[j] - x[i], y[j] - y[i])`
- Validates input (minimum 3 vertices, equal number of x and y coordinates)
- Implements special validation to ensure valid vertices (perimeter > 0)
- Inherits from [Polygon](file:///c:/Dan/Polygon_123/src/Logic/Polygon.java) abstract class

### Prism Class ([Prism.java](file:///c:/Dan/Polygon_123/src/Logic/Prism.java))
- Represents a 3D prism with polygonal base
- Calculates volume as: `base_area * height`
- Validates that height is non-negative
- Independent class that works with any polygon implementation
- Uses composition to calculate volume based on polygon area

### Class Relationships
- **Inheritance Relationship**: Both [RegularPolygon](file:///c:/Dan/Polygon_123/src/Logic/RegularPolygon.java) and [ArbitraryPolygon](file:///c:/Dan/Polygon_123/src/Logic/ArbitraryPolygon.java) inherit from [Polygon](file:///c:/Dan/Polygon_123/src/Logic/Polygon.java)
- **Composition Relationship**: [Prism](file:///c:/Dan/Polygon_123/src/Logic/Prism.java) class takes a polygon's area and height to calculate volume
- **Polymorphism**: Both regular and arbitrary polygons can be used interchangeably where a Polygon is expected

## Project Purpose and Functionality

### Primary Functions:
1. **Regular Polygon Calculations**: Calculate area and perimeter of regular polygons (triangles, squares, pentagons, etc.) and their corresponding prisms' volumes
2. **Arbitrary Polygon Calculations**: Calculate area and perimeter of polygons defined by coordinate points and their corresponding prisms' volumes

### Mathematical Concepts Implemented:
- **Shoelace Formula**: For calculating the area of arbitrary polygons
- **Trigonometric Functions**: For regular polygon area calculations using `(n * s²) / (4 * tan(π/n))`
- **Euclidean Distance**: For calculating perimeter of arbitrary polygons using `Math.hypot()`
- **Prism Volume Calculation**: Base area × height

### Object-Oriented Programming Principles Demonstrated:
- **Abstraction**: Abstract [Polygon](file:///c:/Dan/Polygon_123/src/Logic/Polygon.java) class defines a contract that must be implemented by subclasses
- **Inheritance**: [RegularPolygon](file:///c:/Dan/Polygon_123/src/Logic/RegularPolygon.java) and [ArbitraryPolygon](file:///c:/Dan/Polygon_123/src/Logic/ArbitraryPolygon.java) inherit from the [Polygon](file:///c:/Dan/Polygon_123/src/Logic/Polygon.java) base class
- **Encapsulation**: Private data members with public accessor methods
- **Polymorphism**: The ability to treat different polygon types uniformly through the common interface

### User Experience:
- Intuitive tabbed interface separating the two calculation methods
- Real-time validation and error messaging with descriptive error messages
- Clean, formatted output with proper decimal precision using `DecimalFormat`
- Easy-to-use controls for adding/removing vertices in arbitrary polygon mode
- Responsive UI with proper event handling

### Software Engineering Practices:
- **Separation of Concerns**: Clear division between GUI layer and business logic
- **Error Handling**: Comprehensive try-catch blocks for different exception types
- **Input Validation**: Multiple validation checks to ensure mathematical correctness
- **Code Organization**: Logical package structure separating GUI and Logic
- **User-Friendly Interface**: Clear labels, organized layout, and intuitive controls

### Educational Value:
- Demonstrates object-oriented programming principles (inheritance, abstraction, encapsulation, polymorphism)
- Shows proper separation of GUI and business logic layers
- Implements mathematical algorithms in practical code
- Good error handling and input validation practices
- Proper use of Swing components for desktop application development