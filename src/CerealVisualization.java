import javax.swing.*;
import java.io.IOException;
import java.util.List;

// Entry point for the cereal data visualization GUI application
public class CerealVisualization {
    public static void main(String[] args) {
        // Run GUI-related code on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            try {
                // Read cereal data from CSV file
                List<Cereal> cereals = CerealDataProcessor.readCSV("src/cereal.csv");

                // Create the main application window
                JFrame frame = new JFrame("Cereal Data Visualization");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
                frame.setSize(900, 900); // Set initial size

                // Add the custom panel that contains all charts and tables
                frame.add(new DataVisualizationPanel(cereals));

                frame.setVisible(true); // Show the window
                frame.pack(); // Adjust size to fit contents
            } catch (IOException e) {
                // Handle errors while reading the CSV file
                e.printStackTrace();
            }
        });
    }
}

