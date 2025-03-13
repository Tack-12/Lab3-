import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.*;

// JPanel that displays cereal data in a sortable table
public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    // Constructor initializes table with cereals data
    public TablePanel(List<Cereal> cereals) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String[] columns = {"Name", "Calories", "Protein", "Fat", "Sugar", "Rating"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        //Sorts the rows in the table
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        updateTable(cereals); // Populate table
        add(new JScrollPane(table)); // Add scroll support
    }

    // Updates table with new cereal data
    public void updateTable(List<Cereal> cereals) {
        tableModel.setRowCount(0); // Clear table
        for (Cereal cereal : cereals) {
            tableModel.addRow(new Object[]{
                    cereal.name(), cereal.calories(), cereal.protein(),
                    cereal.fat(), cereal.sugars(), cereal.rating()
            });
        }
    }

    public JTable getTable(){
        return table;
    }

}
