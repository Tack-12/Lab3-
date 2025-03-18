import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class StatsPanel extends JPanel {
    public StatsPanel(List<Cereal> cereals) {
        setLayout(new GridLayout(0, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setFont(new Font("SansSerif", Font.BOLD, 14));

        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Cereal cereal : cereals) {
            stats.addValue(cereal.calories());
        }

        add(createStatLabel("Average Calories:"));
        add(createStatLabel(String.format("%.2f", stats.getMean())));
        add(createStatLabel("Max Calories:"));
        add(createStatLabel(String.valueOf(stats.getMax())));
        add(createStatLabel("Min Calories:"));
        add(createStatLabel(String.valueOf(stats.getMin())));
    }

    private JLabel createStatLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        return label;
    }
}