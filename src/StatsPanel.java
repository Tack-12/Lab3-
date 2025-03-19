import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class StatsPanel extends JPanel {
    private JLabel avgCaloriesLabel;
    private JLabel maxCaloriesLabel;
    private JLabel minCaloriesLabel;

    public StatsPanel(List<Cereal> cereals) {
        setLayout(new GridLayout(3, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        avgCaloriesLabel = createStatLabel("");
        maxCaloriesLabel = createStatLabel("");
        minCaloriesLabel = createStatLabel("");

        add(avgCaloriesLabel);
        add(maxCaloriesLabel);
        add(minCaloriesLabel);
        updateStats(cereals);
    }

    public void updateStats(List<Cereal> cereals) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Cereal cereal : cereals) {
            stats.addValue(cereal.calories());
        }
        avgCaloriesLabel.setText("Average Calories: " + stats.getMean());
        maxCaloriesLabel.setText("Max Calories: " + stats.getMax());
        minCaloriesLabel.setText("Min Calories: " + stats.getMin());
    }

    private JLabel createStatLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        return label;
    }
}