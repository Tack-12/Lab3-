import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class DataVisualizationPanel extends JPanel {
    private ChartPanel pieChartPanel;
    private DefaultPieDataset pieDataset;

    public DataVisualizationPanel(List<Cereal> cereals) {
        setLayout(new BorderLayout());

        TablePanel tablePanel = new TablePanel(cereals);
        StatsPanel statsPanel = new StatsPanel(cereals);
        DetailsPanel detailsPanel = new DetailsPanel();
        ChartPanel barChartPanel = new ChartPanel(createBarChart(cereals));

        // Initialize pie chart with a random cereal
        pieDataset = new DefaultPieDataset();
        JFreeChart pieChart = ChartFactory.createPieChart("Cereal Nutrients", pieDataset, true, true, false);
        pieChartPanel = new ChartPanel(pieChart);

        if (!cereals.isEmpty()) {
            Cereal defaultCereal = cereals.get(0);
            updatePieChart(defaultCereal);
        }

        // Listen for table selection to update details and pie chart
        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tablePanel.getTable().getSelectedRow();
            if (selectedRow != -1) {
                Cereal selectedCereal = cereals.get(selectedRow);
                detailsPanel.updateDetails(selectedCereal);
                updatePieChart(selectedCereal);
            }
        });

        JSplitPane topSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tablePanel), detailsPanel);
        JSplitPane bottomSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, statsPanel, pieChartPanel);
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
                "Calories Comparison",
                "Cereal",
                "Calories",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    // Updates the pie chart with the selected cereal's nutrient breakdown
    private void updatePieChart(Cereal cereal) {
        pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Protein", cereal.protein());
        pieDataset.setValue("Fat", cereal.fat());
        pieDataset.setValue("Sugars", cereal.sugars());
        pieDataset.setValue("Fiber", cereal.fiber());
        pieDataset.setValue("Carbohydrates", cereal.carbo());

        JFreeChart pieChart = ChartFactory.createPieChart("Cereal Nutrients", pieDataset, true, true, false);
        pieChartPanel.setChart(pieChart);
    }
}