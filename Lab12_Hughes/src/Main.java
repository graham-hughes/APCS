//Name - Graham Hughes
//Date - February 3, 2016
//Class - APCS
//Lab  - Lab #12 Main class
//
//This is the main class, it creates a new Reader object with a text file as input
//and then uses the names and numbers arrays of that object as inputs for the GUI class
import java.io.FileNotFoundException;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
                Reader reader = new Reader("res/AdressBook.txt");
                new GUI(reader.names, reader.numbers);
    }
}
