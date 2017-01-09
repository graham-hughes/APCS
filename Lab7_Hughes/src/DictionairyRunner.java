import java.io.FileNotFoundException;
public class DictionairyRunner {
    public static void main( String args[] ) throws FileNotFoundException{
        DictionairyReader readDict = new DictionairyReader();
        readDict.printStats();
    }
}
