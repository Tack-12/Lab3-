import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class StatsPanel extends JPanel {
    public StatsPanel(List<Cereal> cereals) {
        setLayout(new GridLayout(3, 1));
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Cereal cereal : cereals) {
            stats.addValue(cereal.calories());
        }

        add(new JLabel("Average Calories: " + stats.getMean()));
        add(new JLabel("Max Calories: " + stats.getMax()));
        add(new JLabel("Min Calories: " + stats.getMin()));
    }
}