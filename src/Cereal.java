

public record Cereal(String name, String mfr, String type, int calories, int protein, int fat, int sodium,
                     double fiber, double carbo, int sugars, int potass, int vitamins, int shelf,
                     double weight, double cups, double rating) {
    public Cereal(String[] data) {
        this(data[0], data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]),
                Integer.parseInt(data[5]), Integer.parseInt(data[6]), Double.parseDouble(data[7]),
                Double.parseDouble(data[8]), Integer.parseInt(data[9]), Integer.parseInt(data[10]),
                Integer.parseInt(data[11]), Integer.parseInt(data[12]), Double.parseDouble(data[13]),
                Double.parseDouble(data[14]), Double.parseDouble(data[15]));
    }
}
