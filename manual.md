# Building the Polygon & Prism Calculator in Apache NetBeans IDE 25

This manual provides step-by-step instructions for creating the Polygon & Prism Calculator application from scratch using Apache NetBeans IDE 25.

## Prerequisites

- Apache NetBeans IDE 25 installed
- Java Development Kit (JDK) 8 or higher
- Basic understanding of Java programming
- Basic understanding of Object-Oriented Programming concepts

## Step 1: Setting Up the Project

### 1.1 Create a New Java Project
1. Open Apache NetBeans IDE 25
2. Go to `File` → `New Project...` (or press Ctrl+Shift+N)
3. In the New Project wizard:
   - Select `Java` under Categories
   - Select `Java Application` under Projects
   - Click `Next`
4. Configure the project:
   - Project Name: `Polygon_123`
   - Project Location: Choose your desired directory (e.g., `C:\Dan\Polygon_123`)
   - Uncheck `Create Main Class` (we'll create our own)
   - Click `Finish`

### 1.2 Verify Project Structure
After creation, your project should appear in the Projects window with the following structure:
```
Polygon_123
├── Source Packages
├── Libraries
└── Test Packages
```

## Step 2: Creating the Logic Package and Classes

### 2.1 Create the Logic Package
1. Right-click on `Source Packages` in the Projects window
2. Select `New` → `Java Package`
3. Name the package: `Logic`
4. Click `Finish`

### 2.2 Create the Abstract Polygon Class
1. Right-click on the `Logic` package
2. Select `New` → `Java Class`
3. Name the class: `Polygon`
4. Click `Finish`

Replace the generated code with:
```java
package Logic;

public abstract class Polygon {
    private int n;
    private double sideLength;
    private double[] x;
    private double[] y;

    public abstract double getArea();
    public abstract double getPerimeter();
}
```

### 2.3 Create the RegularPolygon Class
1. Right-click on the `Logic` package
2. Select `New` → `Java Class`
3. Name the class: `RegularPolygon`
4. Click `Finish`

Replace the generated code with:
```java
package Logic;

public class RegularPolygon extends Polygon {
    //data field declaration
    private final int n;
    private final double sideLength;

    public RegularPolygon(int n, double sideLength) {
        if (sideLength == 0 && n < 3)
            throw new IllegalArgumentException("Number of sides must be ≥ 3 and side length must be greater than zero");
        if (sideLength < 0 && n < 3)
            throw new IllegalArgumentException("Number of sides must be ≥ 3 and Side length can not be a negative number");
        if (n < 3) throw new IllegalArgumentException("Number of sides must be ≥ 3");
        if (sideLength < 0) throw new IllegalArgumentException("Side length can not be a negative number");
        if (sideLength == 0) throw new IllegalArgumentException("Side length must be greater than zero");

        this.n = n;
        this.sideLength = sideLength;
    }

    //method for area overridden from polygon class
    @Override
    public double getArea() {
        return (n * Math.pow(sideLength, 2)) / (4.0 * Math.tan(Math.PI / n));
    }

    //method for perimeter
    @Override
    public double getPerimeter() {
        return n * sideLength;
    }
}
```

### 2.4 Create the ArbitraryPolygon Class
1. Right-click on the `Logic` package
2. Select `New` → `Java Class`
3. Name the class: `ArbitraryPolygon`
4. Click `Finish`

Replace the generated code with:
```java
package Logic;

public class ArbitraryPolygon extends Polygon{
    //data field declaration
    private final double[] x;
    private final double[] y;
    //constructor instantiation  and throw statement
    public ArbitraryPolygon(double[] x, double[] y) {

        //check if the coordinates are not equal or one of them is <3 using .length
        if (x.length != y.length || x.length < 3) {
            // throw statement exception
            throw new IllegalArgumentException("Enter at least 3 vertices");
        }
        //instantiation
        this.x = x;
        this.y = y;
    }
    //method for Area and setter getter method
    @Override
    public double getArea() {
        double sum = 0.0;
        int n = x.length;
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            sum += x[i] * y[j] - x[j] * y[i];
        }
        return Math.abs(sum) / 2.0;
    }
    @Override
    public double getPerimeter() {
        double perimeter = 0.0;
        int n = x.length;
        for (int i = 0; i < n-1; i++) {
            int j = (i + 1) % n;
            double dist = Math.hypot(x[j] - x[i], y[j] - y[i]);
            perimeter += dist;
        }
        if (perimeter <= 0) throw new IllegalArgumentException("Enter valid vertices");
        return perimeter;
    }
}
```

### 2.5 Create the Prism Class
1. Right-click on the `Logic` package
2. Select `New` → `Java Class`
3. Name the class: `Prism`
4. Click `Finish`

Replace the generated code with:
```java
package Logic;

public class Prism {
    private final double baseArea;
    private final double height;

    public Prism(double baseArea, double height) {
        if (height < 0) throw new IllegalArgumentException("Height cannot be negative");
        this.baseArea = baseArea;
        this.height = height;
    }

    public double getVolume() {
        return baseArea * height;
    }
}
```

## Step 3: Creating the GUI Package and Class

### 3.1 Create the GUI Package
1. Right-click on `Source Packages` in the Projects window
2. Select `New` → `Java Package`
3. Name the package: `Gui`
4. Click `Finish`

### 3.2 Create the PolygonAppUI Class
1. Right-click on the `Gui` package
2. Select `New` → `Java Class`
3. Name the class: `PolygonAppUI`
4. Click `Finish`

Replace the generated code with:
```java
package Gui;

import Logic.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;

//Jframe tells java that we're using windows and helps it inherit windows' properties
public class PolygonAppUI extends JFrame { //decimal format-sub class of number format
    private final DecimalFormat df = new DecimalFormat("#,##0.######"); //the number of digits after the decimal are 6, one for each #

    public static void main(String[] args) {
        //since the computer works on many different things, invoke helps it focus on 'event dispatch thread'(interface)
        SwingUtilities.invokeLater(() -> new PolygonAppUI().setVisible(true)); //set visible (true)- java's window isn't usually visible. this forces it to be
    }

    public PolygonAppUI() { //constructor. used access modifier and class name
        setTitle("Polygon & Prism Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650); //sets the size entire frame
        setLocationRelativeTo(null); // gui placed at the center of the screen

        JTabbedPane tabs = new JTabbedPane(); //object created
        tabs.addTab("Regular Polygon", createRegularPanel());
        tabs.addTab("Arbitrary Polygon", createArbitraryPanel());
        setContentPane(tabs);
    }

    private JPanel createRegularPanel() { //connects the tabs to the panel
        //Jpanel contains: buttons, text field, and labels
        JPanel panel = new JPanel(new BorderLayout(12, 20));//for the components portion size
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));//the border size of the result space

        JTextField nField = new JTextField("0");//Jtextfield- single line text
        JTextField sField = new JTextField("0");
        JTextField hField = new JTextField("0");
        JTextArea resultArea = makeResultArea();//Jtextfiels- multi line text

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));//both row and col can't be zero at the same time
        form.add(new JLabel("Number of sides (n≥3):")); form.add(nField);
        form.add(new JLabel("Side length (L>0):")); form.add(sField);
        form.add(new JLabel("Prism Height (H≥0):")); form.add(hField);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton computeBtn = new JButton("Compute");
        JButton clearBtn = new JButton("Clear");
        buttonsPanel.add(clearBtn);
        buttonsPanel.add(computeBtn);

        panel.add(form, BorderLayout.NORTH);//for input portion
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);//for area portion
        panel.add(buttonsPanel, BorderLayout.SOUTH);//for buttons portion

        computeBtn.addActionListener(e -> {// add acton listener- waits until there's an input
            try {
                int n = Integer.parseInt(nField.getText().trim());//parse- changes input to numerical values
                double l = Double.parseDouble(sField.getText().trim());//getText.trim- gets the text and trims the leading and trailing whitespace
                double h = Double.parseDouble(hField.getText().trim());

                RegularPolygon poly = new RegularPolygon(n, l);
                Prism prism = new Prism(poly.getArea(), h);//base area= poly.getArea

                resultArea.setText(formatResults(poly.getArea(), poly.getPerimeter(), prism.getVolume()));
            } catch (NumberFormatException ex) {
                resultArea.setText("Error: Please enter numeric values");
            } catch (IllegalArgumentException ex) {
                resultArea.setText("Error: " + ex.getMessage());//5 messages from regular polygon.
            }
        });

        clearBtn.addActionListener(ae -> {
            nField.setText("0");
            sField.setText("0");
            hField.setText("0");
            resultArea.setText("");
        });

        return panel;
    }

    private JPanel createArbitraryPanel() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        // table setup
        DefaultTableModel model = new DefaultTableModel(new String[]{"x", "y"}, 0);//array because we use coordinates
        model.addRow(new Object[]{"0", "0"});
        model.addRow(new Object[]{"0", "0"});
        model.addRow(new Object[]{"0", "0"});
        JTable table = new JTable(model);

        // top controls
        JPanel top = new JPanel(new GridBagLayout());//GridBag is  ore flexible than Grid layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);//for h, add, and remove vertex
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField heightField = new JTextField("0");
        JButton addRowBtn = new JButton("+ Add vertex");
        JButton removeRowBtn = new JButton("− Remove selected");


        gbc.gridx = 0; gbc.weightx = 0; top.add(new JLabel("Prism height (H ≥ 0):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1; top.add(heightField, gbc);//gridx-left and right
        gbc.gridx = 2; gbc.weightx = 0; top.add(addRowBtn, gbc);//weightx- horizontal and vertical
        gbc.gridx = 3; gbc.weightx = 0; top.add(removeRowBtn, gbc);

        // result and buttons
        JTextArea resultArea = makeResultArea();
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton computeBtn = new JButton("Compute");
        JButton clearBtn = new JButton("Clear");
        bottomPanel.add(clearBtn);
        bottomPanel.add(computeBtn);


        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(new JScrollPane(resultArea), BorderLayout.EAST);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // listeners
        addRowBtn.addActionListener(ae -> model.addRow(new Object[]{"0", "0"}));
        removeRowBtn.addActionListener(ae -> {
            int idx = table.getSelectedRow();
            if (idx >= 0) model.removeRow(idx);
        });
        // clear button logic
        clearBtn.addActionListener(ae -> {
            model.setRowCount(0); // Remove all rows
            model.addRow(new Object[]{"0", "0"}); // Re-add default triangle
            model.addRow(new Object[]{"0", "0"});
            model.addRow(new Object[]{"0", "0"});
            heightField.setText("0");
            resultArea.setText("");
        });

        computeBtn.addActionListener(e -> {
            try {
                double H = Double.parseDouble(heightField.getText().trim());
                int rows = model.getRowCount();
                double[] xs = new double[rows];
                double[] ys = new double[rows];

                for (int i = 0; i < rows; i++) {
                    xs[i] = Double.parseDouble(model.getValueAt(i, 0).toString().trim());//accepts value, turns it to string, then parses
                    ys[i] = Double.parseDouble(model.getValueAt(i, 1).toString().trim());
                }

                ArbitraryPolygon poly = new ArbitraryPolygon(xs, ys);
                Prism prism = new Prism(poly.getArea(), H);

                resultArea.setText(formatResults(poly.getArea(), poly.getPerimeter(), prism.getVolume()));
            } catch (NumberFormatException ex) {
                resultArea.setText("Error: Check vertex coordinates and height");
            } catch (IllegalArgumentException ex) {
                resultArea.setText("Error: " + ex.getMessage());
            }
        });

        return panel;
    }

    private JTextArea makeResultArea() {
        JTextArea ta = new JTextArea(10, 70);
        ta.setEditable(false);//can't edit the results
        ta.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        ta.setBorder(BorderFactory.createTitledBorder("Results"));
        return ta;
    }

    private String formatResults(double area, double perimeter, double volume) {
        StringBuilder sb = new StringBuilder();
        sb.append("Computed values:\n");
        sb.append("  Area (A):      ").append(df.format(area)).append("\n");
        sb.append("  Perimeter (P): ").append(df.format(perimeter)).append("\n");
        sb.append("  Volume (V):    ").append(df.format(volume)).append("\n\n");
        sb.append("Notes:\n");
        sb.append(" - Valid sides/height enforced.");
        return sb.toString();
    }
}
```

## Step 4: Verify and Build the Project

### 4.1 Verify Package Structure
Your project structure should now look like this:
```
Polygon_123
├── Source Packages
│   └── Gui
│       └── PolygonAppUI.java
│   └── Logic
│       ├── Polygon.java
│       ├── RegularPolygon.java
│       ├── ArbitraryPolygon.java
│       └── Prism.java
├── Libraries
└── Test Packages
```

### 4.2 Build the Project
1. Right-click on the project name (`Polygon_123`) in the Projects window
2. Select `Clean and Build`
3. Check the Output window for any compilation errors
4. If there are no errors, the build should complete successfully

## Step 5: Running the Application

### Method 1: From NetBeans IDE
1. Right-click on the `PolygonAppUI.java` file in the Projects window
2. Select `Run`
3. The application window should appear

### Method 2: Set as Main Class (Alternative)
1. Right-click on the `PolygonAppUI.java` file
2. Select `Properties`
3. In the Properties window, check `Main Class` and select `Gui.PolygonAppUI`
4. Right-click on the project name and select `Run`

## Step 6: Testing the Application

### 6.1 Test Regular Polygon Calculator
1. Navigate to the "Regular Polygon" tab
2. Enter values: Number of sides = 4, Side length = 5, Prism Height = 3
3. Click "Compute"
4. Verify that the results show:
   - Area ≈ 25
   - Perimeter = 20
   - Volume ≈ 75

### 6.2 Test Arbitrary Polygon Calculator
1. Navigate to the "Arbitrary Polygon" tab
2. Enter coordinates for a triangle: (0,0), (4,0), (0,3)
3. Set Prism Height = 2
4. Click "Compute"
5. Verify that the results show:
   - Area = 6
   - Perimeter ≈ 12 (3+4+5)
   - Volume = 12

## Troubleshooting Common Issues

### Issue 1: Compilation Errors
- Ensure all classes are in the correct packages
- Verify that import statements are correct
- Check for typos in class names and method signatures

### Issue 2: "Cannot find symbol" Errors
- Make sure the Logic package classes are properly created
- Verify that inheritance relationships are correct (e.g., RegularPolygon extends Polygon)

### Issue 3: Runtime Exceptions
- The application has proper error handling for invalid inputs
- Check that numeric values are entered in the correct format
- Ensure at least 3 vertices are provided for arbitrary polygons

## Understanding the Code Structure

### MVC Pattern
- **Model**: Logic package classes (Polygon, RegularPolygon, etc.) - handle data and calculations
- **View**: GUI package class (PolygonAppUI) - handles user interface
- **Controller**: Event listeners in PolygonAppUI - handle user interactions

### Object-Oriented Concepts Demonstrated
- **Inheritance**: RegularPolygon and ArbitraryPolygon inherit from Polygon
- **Abstraction**: Abstract methods in Polygon class
- **Encapsulation**: Private fields with public methods
- **Polymorphism**: Different polygon types treated uniformly

## Additional Tips

1. **Code Navigation**: Use Ctrl+Click to navigate between classes
2. **Auto-formatting**: Use Alt+Shift+F to format your code
3. **Syntax Highlighting**: NetBeans provides color-coded syntax highlighting
4. **Debugging**: Set breakpoints by clicking in the left margin and use F5 to debug
5. **Documentation**: Press Ctrl+Shift+D over any method to see documentation