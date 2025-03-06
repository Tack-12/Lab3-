
public class Main {
    public static void main(String[] args) {

        gettingData harvard = new gettingData("src\\data.json");
        String data = harvard.storingData();

        System.out.println(data);
    }
}
