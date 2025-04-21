import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import javax.swing.*;
import java.awt.*;
import java.util.List;

// Panel that displays statistics about cereal data
// Implements CerealObserver to respond to individual cereal selection
class StatsPanel extends JPanel implements CerealObserver {
    private JLabel avgCaloriesLabel; // Label to show average calories
    private JLabel maxCaloriesLabel; // Label to show max calories
    private JLabel minCaloriesLabel; // Label to show min calories

    // Constructor to initialize the layout and components
    public StatsPanel(List<Cereal> cereals) {
        setLayout(new GridLayout(3, 1, 10, 10)); // Vertical grid layout with spacing
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the panel

        // Create labels
        avgCaloriesLabel = createStatLabel("");
        maxCaloriesLabel = createStatLabel("");
        minCaloriesLabel = createStatLabel("");

        // Add labels to panel
        add(avgCaloriesLabel);
        add(maxCaloriesLabel);
        add(minCaloriesLabel);

        // Set initial stats based on full list
        updateStats(cereals);
    }

    // Updates the labels with aggregated stats from the full cereal list
    void updateStats(List<Cereal> cereals) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (Cereal cereal : cereals) {
            stats.addValue(cereal.calories()); // Add calorie values for analysis
        }
        avgCaloriesLabel.setText("Average Calories: " + stats.getMean());
        maxCaloriesLabel.setText("Max Calories: " + stats.getMax());
        minCaloriesLabel.setText("Min Calories: " + stats.getMin());
    }

    // Helper method to create a styled JLabel
    private JLabel createStatLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        return label;
    }

    // Observer method triggered when a specific cereal is selected
    @Override
    public void update(Cereal cereal) {
        // Display nutritional stats for the selected cereal
        avgCaloriesLabel.setText("Calories: " + cereal.calories());
        maxCaloriesLabel.setText("Protein: " + cereal.protein());
        minCaloriesLabel.setText("Sugars: " + cereal.sugars());
    }
}
