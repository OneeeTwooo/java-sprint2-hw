import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        FileReader fr = new FileReader();
        ArrayList<String> strings = fr.readFileContents("y.2021.csv");
        for (String st: strings){
            System.out.println(st);
        }
    }
}

