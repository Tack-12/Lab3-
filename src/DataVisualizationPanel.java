import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

// Main panel combining Table, Stats, Details, and Chart Panels
public class DataVisualizationPanel extends JPanel {
    public DataVisualizationPanel(List<Cereal> cereals) {
        setLayout(new BorderLayout());

        TablePanel tablePanel = new TablePanel(cereals);
        StatsPanel statsPanel = new StatsPanel(cereals);
        DetailsPanel detailsPanel = new DetailsPanel();
        ChartPanel chartPanel = new ChartPanel(createBarChart(cereals));

        // Listen for table selection to update details panel
        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tablePanel.getTable().getSelectedRow();
            if (selectedRow != -1) {
                detailsPanel.updateDetails(cereals.get(selectedRow));
            }
        });

        JSplitPane topSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tablePanel), detailsPanel);
        JSplitPane bottomSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, statsPanel, chartPanel);
        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplit, bottomSplit);

        add(mainSplit, BorderLayout.CENTER);
    }

    // Creates a bar chart comparing cereal calories
    private JFreeChart createBarChart(List<Cereal> cereals) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Cereal cereal : cereals) {
            dataset.addValue(cereal.calories(), "Calories", cereal.name());
        }
        return ChartFactory.createBarChart(
                "Calories Comparison",  // Chart title
                "Cereal",              // Category axis label
                "Calories",            // Value axis label
                dataset,               // Dataset
                PlotOrientation.VERTICAL,  // Chart orientation
                true,                  // Include legend
                true,                  // Enable tooltips
                false                  // Disable URLs
        );
    }


}
