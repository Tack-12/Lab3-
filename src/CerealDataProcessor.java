import java.io.*;
import java.util.*;

public class CerealDataProcessor {
    public static List<Cereal> readCSV(String filename) throws IOException {
        List<Cereal> cereals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                cereals.add(new Cereal(values));
            }
        }
        return cereals;
    }
}

