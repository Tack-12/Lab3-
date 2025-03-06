import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class gettingData {
    private final String filename;

    public gettingData(String filename){
        this.filename= filename;
    }


    public String storingData(){
        StringBuilder data  = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(this.filename))){
            String line;
            while(( line = reader.readLine())!= null){
                data.append(line).append("\n");
            }
        }
        catch(IOException e){
            System.out.println("This file is not there !!");
        }
        return data.toString();
    }
}
