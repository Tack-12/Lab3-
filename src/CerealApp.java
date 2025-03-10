import javax.swing.*;
import java.io.*;
import java.util.*;

// Main application for reading and displaying cereal data
public class CerealApp {
    private static final String CSV_FILE_PATH = "src\\cereal.csv";
    private static final int FIRST_CEREAL_INDEX = 0;
    private static final int TENTH_CEREAL_INDEX = 9;
    private static final String APP_TITLE = "Cereal Data Visualization";

    public static void main(String[] args) {
        try {
            // Read cereal data from CSV file
            List<Cereal> cereals = CerealDataProcessor.readCSV(CSV_FILE_PATH);

            // Console output
            System.out.println("First cereal: " + cereals.get(FIRST_CEREAL_INDEX));
            System.out.println("Tenth cereal: " +
                    (cereals.size() > TENTH_CEREAL_INDEX ? cereals.get(TENTH_CEREAL_INDEX) : "Not enough entries"));
            System.out.println("Total number of cereals: " + cereals.size());

            // Launch GUI
            SwingUtilities.invokeLater(() -> new CerealApp().createAndShowGUI(cereals));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Creates and displays the GUI
    private void createAndShowGUI(List<Cereal> cereals) {
        JFrame frame = new JFrame(APP_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(new TablePanel(cereals)); // Add table panel

        frame.pack();
        frame.setVisible(true);
    }
}
