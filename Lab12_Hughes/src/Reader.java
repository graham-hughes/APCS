//Name - Graham Hughes
//Date - February 3, 2016
//Class - APCS
//Lab  - Lab #12 Reader class
//
//This class parses a text file to generate two arrays of names and numbers
//where names[i] corresponds to numbers[i]
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    ArrayList <String> namesList = new ArrayList();
    ArrayList <String> numbersList = new ArrayList();
    String[] names;
    String[] numbers;
    public Reader (String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(new FileReader(fileName));
        
        while(reader.hasNextLine()){ //adds name and number to respective ArrayLists
            String line = reader.nextLine();
            namesList.add(line.split("\"")[1]);
            numbersList.add(line.split("\"")[3]);
        }
        names = (String[]) namesList.toArray(new String[namesList.size()]);
        numbers = (String[]) numbersList.toArray(new String[numbersList.size()]);
    }
}
