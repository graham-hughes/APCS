/*
Graham Hughes
Mrs. Hemiup
November 23, 2015
Lab 10
*/
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MagicSquare1Runner
{
	public static void main( String args[] ) throws IOException
	{
            Scanner readFile = new Scanner (new FileReader("magic1.dat"));
            int numSquares = readFile.nextInt();
           
            String numbers = "";
            int size = 0;
           
            //runs magic square detection code for the number of squares in the file
            for(int i = 0; i < numSquares; i++){
                size = readFile.nextInt();
                readFile.nextLine();
                numbers = readFile.nextLine();
                MagicSquare1 square = new MagicSquare1(size, numbers);
            }
            
	}
}