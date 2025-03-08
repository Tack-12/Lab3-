import javax.swing.*;
import java.io.*;
import java.util.*;

public class CerealApp {
    public static void main(String[] args) {
        try {
            List<Cereal> cereals = CerealDataProcessor.readCSV("src\\cereal.csv");

            // Part 1A: Console Output
            System.out.println("First cereal: " + cereals.get(0));
            System.out.println("Tenth cereal: " + (cereals.size() >= 10 ? cereals.get(9) : "Not enough entries"));
            System.out.println("Total number of cereals: " + cereals.size());

            // Part 1B: GUI Table
            SwingUtilities.invokeLater(() -> new CerealApp().createAndShowGUI(cereals));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createAndShowGUI(List<Cereal> cereals) {
        JFrame frame = new JFrame("Cereal Data Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        TablePanel tablePanel = new TablePanel(cereals);
        frame.add(tablePanel);

        frame.pack();
        frame.setVisible(true);
    }
}