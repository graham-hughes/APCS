/*
Graham Hughes
Mrs. Hemiup
November 23, 2015
Lab 10
*/
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MagicSquare2Runner
{
	public static void main( String args[] ) throws IOException
	{
            //Runs the magic square generation program using the magic2 file as input
            Scanner readFile = new Scanner (new FileReader("magic2.dat"));
            while(readFile.hasNext()){
                MagicSquare2 magic2 = new MagicSquare2(readFile.nextInt());
            }
           
	}
}