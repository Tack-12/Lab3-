import javax.swing.*;
import java.awt.*;

// Panel to display detailed information about a selected cereal
// Implements CerealObserver so it can be notified when a cereal is selected
class DetailsPanel extends JPanel implements CerealObserver {
    private JTextArea detailsArea; // Area to display cereal details

    // Constructor sets up the panel layout and visual style
    public DetailsPanel() {
        setLayout(new BorderLayout()); // Use BorderLayout for full-width text area
        setBorder(BorderFactory.createTitledBorder("Cereal Details")); // Add titled border

        // Initialize the details area with default text
        detailsArea = new JTextArea("Choose one of the cereals to show details.");
        detailsArea.setEditable(false); // Make text area read-only
        detailsArea.setFont(new Font("SansSerif", Font.PLAIN, 14)); // Set font style
        detailsArea.setLineWrap(true); // Enable line wrap
        detailsArea.setWrapStyleWord(true); // Wrap at word boundaries
        detailsArea.setBackground(new Color(240, 248, 255)); // Set background color

        // Add the text area inside a scroll pane to allow scrolling if needed
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);
    }

    // Observer method called when a new cereal is selected
    @Override
    public void update(Cereal cereal) {
        detailsArea.setText(formatCerealDetails(cereal));
    }

    // Helper method to convert a Cereal object into a formatted string
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
