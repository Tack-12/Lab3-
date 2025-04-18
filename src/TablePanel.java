import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.*;
import java.util.List;

// JPanel that displays cereal data in a sortable table
class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    public TablePanel(List<Cereal> cereals) {
        setLayout(new BorderLayout());
        String[] columns = {"Name", "Calories", "Protein", "Fat", "Sugar", "Rating"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        updateTable(cereals);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void updateTable(List<Cereal> cereals) {
        tableModel.setRowCount(0);
        for (Cereal cereal : cereals) {
            tableModel.addRow(new Object[]{cereal.name(), cereal.calories(), cereal.protein(), cereal.fat(), cereal.sugars(), cereal.rating()});
        }
    }

    public JTable getTable() {
        return table;
    }
}
