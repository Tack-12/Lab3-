import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class CerealVisualization {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                List<Cereal> cereals = CerealDataProcessor.readCSV("src/cereal.csv");
                JFrame frame = new JFrame("Cereal Data Visualization");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.add(new DataVisualizationPanel(cereals));
                frame.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

