import javax.swing.*;
import java.io.*;
import java.util.*;

// Main application for reading and displaying cereal data
public class CerealApp {
    public static void main(String[] args) {
        try {
            // Read cereal data from CSV file
            List<Cereal> cereals = CerealDataProcessor.readCSV("src\\cereal.csv");

            // Console output
            System.out.println("First cereal: " + cereals.get(0));
            System.out.println("Tenth cereal: " + (cereals.size() >= 10 ? cereals.get(9) : "Not enough entries"));
            System.out.println("Total number of cereals: " + cereals.size());

            // Launch GUI
            SwingUtilities.invokeLater(() -> new CerealApp().createAndShowGUI(cereals));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Creates and displays the GUI
    private void createAndShowGUI(List<Cereal> cereals) {
        JFrame frame = new JFrame("Cereal Data Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(new TablePanel(cereals)); // Add table panel

        frame.pack();
        frame.setVisible(true);
    }
}