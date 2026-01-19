# Polygon & Prism Calculator

A Java desktop application that calculates geometric properties of regular and arbitrary polygons, including their corresponding prism volumes.

## Overview

The Polygon & Prism Calculator is a Swing-based desktop application that allows users to calculate the area, perimeter, and volume of different polygon types. The application features a user-friendly interface with tabbed panels for different calculation modes.

## Features

- **Regular Polygon Calculator**: Calculate properties of regular polygons (triangles, squares, pentagons, etc.)
  - Input: Number of sides, side length, and prism height
  - Output: Area, perimeter, and volume
  
- **Arbitrary Polygon Calculator**: Calculate properties of polygons defined by coordinate points
  - Input: Vertex coordinates (x, y) and prism height
  - Output: Area, perimeter, and volume
  - Dynamic vertex management (add/remove vertices)

- **Real-time Validation**: Comprehensive input validation with descriptive error messages
- **Formatted Output**: Precise decimal formatting for calculated values
- **Intuitive UI**: Tabbed interface with organized input controls and results display

## Technologies Used

- **Java SE**: Core programming language
- **Swing**: GUI framework for desktop application
- **Object-Oriented Programming**: Inheritance, abstraction, encapsulation, and polymorphism

## Project Structure

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

## Class Diagram

- **Polygon** (Abstract): Base class defining the contract for polygon calculations
- **RegularPolygon**: Implements calculations for regular polygons using trigonometric formulas
- **ArbitraryPolygon**: Implements calculations for arbitrary polygons using the Shoelace formula
- **Prism**: Calculates 3D prism volume based on polygon area and height
- **PolygonAppUI**: Main GUI class with tabbed interface for user interaction

## Mathematical Concepts

- **Shoelace Formula**: For calculating the area of arbitrary polygons
- **Trigonometric Functions**: For regular polygon area calculations using `(n * s²) / (4 * tan(π/n))`
- **Euclidean Distance**: For calculating perimeter of arbitrary polygons using `Math.hypot()`
- **Prism Volume**: Base area × height

## How to Run

1. Compile the Java files:
   ```bash
   javac -d bin src/Gui/PolygonAppUI.java src/Logic/*.java
   ```

2. Run the application:
   ```bash
   java -cp bin Gui.PolygonAppUI
   ```

## Usage Instructions

1. **Regular Polygon Tab**:
   - Enter the number of sides (must be ≥ 3)
   - Enter the side length (must be > 0)
   - Enter the prism height (can be 0 for 2D polygon)
   - Click "Compute" to see results
   - Use "Clear" to reset all fields

2. **Arbitrary Polygon Tab**:
   - Enter vertex coordinates in the table (at least 3 vertices required)
   - Enter the prism height
   - Use "+ Add vertex" to add more coordinate pairs
   - Use "− Remove selected" to remove selected vertices
   - Click "Compute" to see results
   - Use "Clear" to reset all fields

## Design Patterns

- **Inheritance**: `RegularPolygon` and `ArbitraryPolygon` inherit from abstract `Polygon` class
- **Abstraction**: Abstract methods in `Polygon` class that are implemented by subclasses
- **Encapsulation**: Private data members with public accessor methods
- **Polymorphism**: Different polygon types can be treated uniformly through the common interface

## Error Handling

The application includes comprehensive error handling for:
- Invalid numeric inputs
- Invalid polygon parameters (sides < 3, negative lengths)
- Insufficient vertices for polygon creation

## Educational Value

This project demonstrates:
- Object-oriented programming principles
- Proper separation of GUI and business logic
- Implementation of mathematical algorithms in code
- Good error handling and input validation practices
- Effective use of Swing components for desktop application development