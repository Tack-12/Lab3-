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
                frame.setSize(900, 900);
                frame.add(new DataVisualizationPanel(cereals));
                frame.setVisible(true);
                frame.pack()
;            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

