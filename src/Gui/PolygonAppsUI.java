package Gui;

import Logic.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;

//Jframe tells java that we're using windows and helps it inherit windows' properties
public class PolygonAppsUI extends JFrame { //decimal format-sub class of number format
    private final DecimalFormat df = new DecimalFormat("#,##0.######"); //the number of digits after the decimal are 6, one for each #

    public static void main(String[] args) {
        //since the computer works on many different things, invoke helps it focus on 'event dispatch thread'(interface)
        SwingUtilities.invokeLater(() -> new PolygonAppsUI().setVisible(true)); //set visible (true)- java's window isn't usually visible. this forces it to be
    }

    public PolygonAppsUI() { //constructor. used access modifier and class name
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