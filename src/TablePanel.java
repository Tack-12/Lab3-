import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private List<Cereal> cereals;
    private List<CerealObserver> observers = new ArrayList<>();

    public TablePanel(List<Cereal> cereals) {
        this.cereals = cereals;
        setLayout(new BorderLayout());
        String[] columns = {"Name", "Calories", "Protein", "Fat", "Sugar", "Rating"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        updateTable(cereals);
        add(new JScrollPane(table), BorderLayout.CENTER);

        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                notifyObservers(cereals.get(selectedRow));
            }
        });
    }

    public void addObserver(CerealObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Cereal cereal) {
        for (CerealObserver observer : observers) {
            observer.update(cereal);
        }
    }

    public void updateTable(List<Cereal> cereals) {
        tableModel.setRowCount(0);
        this.cereals = cereals;
        for (Cereal cereal : cereals) {
            tableModel.addRow(new Object[]{cereal.name(), cereal.calories(), cereal.protein(), cereal.fat(), cereal.sugars(), cereal.rating()});
        }
    }

    public JTable getTable() {
        return table;
    }
}
