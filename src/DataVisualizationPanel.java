import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class DataVisualizationPanel extends JPanel {
    private ChartPanel pieChartPanel;
    private DefaultPieDataset pieDataset;
    private TablePanel tablePanel;
    private StatsPanel statsPanel;
    private DetailsPanel detailsPanel;
    private ChartPanel barChartPanel;
    private List<Cereal> originalCereals;

    public DataVisualizationPanel(List<Cereal> cereals) {
        setLayout(new BorderLayout());
        this.originalCereals = cereals;

        tablePanel = new TablePanel(cereals);
        statsPanel = new StatsPanel(cereals);
        detailsPanel = new DetailsPanel();
        barChartPanel = new ChartPanel(createBarChart(cereals));

        // Initialize pie chart with a random cereal
        pieDataset = new DefaultPieDataset();
        JFreeChart pieChart = ChartFactory.createPieChart("Cereal Nutrients", pieDataset, true, true, false);
        pieChartPanel = new ChartPanel(pieChart);

        if (!cereals.isEmpty()) {
            updatePieChart(cereals.get(0));
        }

        // Listen for table selection to update details and pie chart
        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tablePanel.getTable().getSelectedRow();
            if (selectedRow != -1) {
                Cereal selectedCereal = cereals.get(selectedRow);
                detailsPanel.update(selectedCereal);
                updatePieChart(selectedCereal);
            }
        });

        JPanel filterPanel = createFilterPanel();

        JSplitPane topSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tablePanel), detailsPanel);
        JSplitPane bottomSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, statsPanel, pieChartPanel);
        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplit, bottomSplit);

        add(filterPanel, BorderLayout.NORTH);
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

    // Create filter panel with three toggleable filters
    private JPanel createFilterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JCheckBox lowCalFilter = new JCheckBox("Low Calories (<100)");
        JCheckBox highProteinFilter = new JCheckBox("High Protein (>3g)");
        JCheckBox lowSugarFilter = new JCheckBox("Low Sugar (<5g)");

        ActionListener filterListener = e -> applyFilters(lowCalFilter.isSelected(), highProteinFilter.isSelected(), lowSugarFilter.isSelected());

        lowCalFilter.addActionListener(filterListener);
        highProteinFilter.addActionListener(filterListener);
        lowSugarFilter.addActionListener(filterListener);

        panel.add(lowCalFilter);
        panel.add(highProteinFilter);
        panel.add(lowSugarFilter);
        return panel;
    }

    // Apply selected filters
    private void applyFilters(boolean lowCal, boolean highProtein, boolean lowSugar) {
        List<Cereal> filtered = originalCereals.stream()
                .filter(c -> !lowCal || c.calories() < 100)
                .filter(c -> !highProtein || c.protein() > 3)
                .filter(c -> !lowSugar || c.sugars() < 5)
                .collect(Collectors.toList());

        tablePanel.updateTable(filtered);
        statsPanel.updateStats(filtered);
        barChartPanel.setChart(createBarChart(filtered));
        tablePanel.addObserver(detailsPanel);
        tablePanel.addObserver(statsPanel);

    }
}
