import java.io.*;
import java.util.*;

// Processes cereal data from a CSV file
public class CerealDataProcessor {
    // Reads CSV and returns a list of Cereal objects
    public static List<Cereal> readCSV(String filename) throws IOException {
        List<Cereal> cereals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                cereals.add(CerealFactory.createCereal(line.split(",")));
            }
        }
        return cereals;
    }
}


