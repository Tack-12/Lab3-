import javax.swing.*;
import java.awt.*;

class DetailsPanel extends JPanel {
    private JTextArea detailsArea;

    public DetailsPanel() {
        setLayout(new BorderLayout());
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);
    }

    public void updateDetails(Cereal cereal) {
        detailsArea.setText(cereal.toString());
    }
}
