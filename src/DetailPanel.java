import javax.swing.*;
import java.awt.*;

class DetailsPanel extends JPanel {
    private JTextArea detailsArea;

    public DetailsPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Cereal Details"));
        detailsArea = new JTextArea("Choose one of the cereals to show details.");
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setBackground(new Color(240, 248, 255));
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);
    }

    public void updateDetails(Cereal cereal) {
        detailsArea.setText(formatCerealDetails(cereal));
    }

    private String formatCerealDetails(Cereal cereal) {
        return "Name: " + cereal.name() + "\n" +
                "Manufacturer: " + cereal.mfr() + "\n" +
                "Type: " + cereal.type() + "\n" +
                "Calories: " + cereal.calories() + "\n" +
                "Protein: " + cereal.protein() + "g\n" +
                "Fat: " + cereal.fat() + "g\n" +
                "Sodium: " + cereal.sodium() + "mg\n" +
                "Fiber: " + cereal.fiber() + "g\n" +
                "Sugars: " + cereal.sugars() + "g";
    }
}
