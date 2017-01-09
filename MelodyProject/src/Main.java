/*
 * Melody Player
 *
 * Authors: Allison Obourn and Marty Stepp
 * Version: Tue 2015/03/04
 * 
 * Modified by Graham Hughes on  2015/12/9
 * This file contains the main method to run the program.
 */

import java.io.FileNotFoundException;

public class Main {
	/*
	 * Runs the program.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		new MelodyGUI();
                  //uncomment the code below (leave either 23 or 24 commented out),
                  // and comment out "new MelodyGUI();" to test the merge and scramble methods
//                Melody mel1 = new Melody("res/melodies/scale.txt");
//                Melody mel2 = new Melody("res/melodies/twinkle.txt");
//                mel1.mergeMelody(mel2);
//                mel1.scrambleSong();
//                mel1.play();
	}   
}
