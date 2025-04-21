import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Panel that displays cereal data in a sortable table and notifies observers on selection change
class TablePanel extends JPanel {
    private JTable table; // JTable for displaying cereal data
    private DefaultTableModel tableModel; // Model backing the table
    private TableRowSorter<DefaultTableModel> sorter; // Allows sorting functionality
    private List<Cereal> cereals; // Current list of cereals being displayed
    private List<CerealObserver> observers = new ArrayList<>(); // List of registered observers

    // Constructor initializes table with provided cereal list
    public TablePanel(List<Cereal> cereals) {
        this.cereals = cereals;
        setLayout(new BorderLayout());

        // Define column headers
        String[] columns = {"Name", "Calories", "Protein", "Fat", "Sugar", "Rating"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Enable sorting
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        // Populate table
        updateTable(cereals);

        // Add table to scroll pane and layout
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Listen for row selection and notify observers when selection changes
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                notifyObservers(cereals.get(selectedRow));
            }
        });
    }

    // Method to register an observer
    public void addObserver(CerealObserver observer) {
        observers.add(observer);
    }

    // Notify all registered observers of selected cereal
    private void notifyObservers(Cereal cereal) {
        for (CerealObserver observer : observers) {
            observer.update(cereal);
        }
    }

    // Update table content with new list of cereals
    public void updateTable(List<Cereal> cereals) {
        tableModel.setRowCount(0); // Clear existing rows
        this.cereals = cereals;    // Update reference
        for (Cereal cereal : cereals) {
            tableModel.addRow(new Object[]{
                    cereal.name(),
                    cereal.calories(),
                    cereal.protein(),
                    cereal.fat(),
                    cereal.sugars(),
                    cereal.rating()
            });
        }
    }

    // Expose the JTable instance for external access
    public JTable getTable() {
        return table;
    }
}
