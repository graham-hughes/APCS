/* 
 Graham Hughes * APCS Final Project * December 8, 2015 * Mrs. Hemiup

 This is the melody class, which constructs an ArrayList of notes
 and then finds the order in which to play them by adjusting for repeats.

 It contains:
 - a constructor with input file name that creates the notes array
 - methods for retrieving song title, author, and duration
 - methods for changing tempo and octave 
 - a method for playing the song
 - a method for reversing the song 
 - a method for scrambling the song (Extra Credit)
 - a method for playing the song with an offset (Extra Credit)
 - a method that rick rolls unsuspecting victims. Uses a secret combination of button presses:
 reverse, reverse, play
 */

import java.awt.Desktop;
import java.util.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import melody.audio.Accidental;
import melody.audio.Note;
import melody.audio.Pitch;
//import static melody.audio.StdAudio.AudioEvent.Type.PLAY;
//import melody.audio.*;

public class Melody {

    private String artist = "";
    private String title = "";
    private int numLines = 0;
    private Note[] notes;

    public Melody(String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(new FileReader(fileName));
        String line = "";
        title = reader.nextLine(); //sets song title
        artist = reader.nextLine(); //sets song artist
        numLines = Integer.parseInt(reader.nextLine()); //sets number of lines
        notes = new Note[numLines]; //number of notes = number of lines stated at top of file

        for (int i = 0; i < numLines; i++) { //loops through the rest of the .txt file, line by line   
            Note noteTemp; //this is the temporary Note object that will be defined and added to notes array
            line = reader.nextLine();

                //deals with deviations from the standard spacing format (ie in epona.txt)
            //by removing white space at the beginning and end of files
            //and by switching out double spaces with single spaces
            line = line.trim().replaceAll(" +", " ");

            double duration = Double.parseDouble(line.substring(0, line.indexOf(" "))); //sets duration
            line = line.substring(line.indexOf(" ") + 1); //trims off duration
            boolean repeat = Boolean.parseBoolean(line.substring(line.lastIndexOf(" ") + 1)); //sets repeat
            line = line.substring(0, line.lastIndexOf(" "));//trims off repeat

            if (line.charAt(0) == 'R') {
                //sets noteTemp to a new rest Note 
                noteTemp = new Note(duration, repeat);
            } else {
                    //not a rest note, so defines Pitch, Octave, and Accidental and
                //then assigns them to noteTemp

                Pitch pitch = Pitch.getValueOf(line.substring(0, 1));//sets pitch
                line = line.substring(line.indexOf(" ") + 1); //trims
                int octave = Integer.parseInt(line.substring(0, 1));//sets octave
                line = line.substring(line.indexOf(" ") + 1); //trims
                Accidental accidental = Accidental.getValueOf(line.substring(0));//sets Accidental
                noteTemp = new Note(duration, pitch, octave, accidental, repeat);
            }
            notes[i] = noteTemp; //assigns the note object at location i of notes array to noteTemp
        }
    }

    public void changeTempo(double ratio) { //multiplies each note duration by the given ratio
        for (Note note : notes) { //loops through notes
            note.setDuration(ratio * note.getDuration()); //sets duration to ratio*duration
        }
    }

    public String getArtist() { //returns the song's artist
        return artist;
    }

    public String getTitle() { //returns the song's title
        return title;
    }

    public double getTotalDuration() { //gets total duration of song
        double totalDuration = 0; //starts at 0

        int startRepeating = -1;
        int endRepeating = -1;

        for (int i = 0; i < notes.length; i++) { //loops through noteOrder 
            totalDuration += notes[i].getDuration(); //adds each note duration 
            if (notes[i].isRepeat() && startRepeating == -1) {
                startRepeating = i;
            }
            else if (notes[i].isRepeat() && startRepeating != -1) {
                endRepeating = i;//then repeated section is over
                for (int s = startRepeating; s <= endRepeating; s++) {
                    totalDuration += notes[s].getDuration(); //re-adds the duration of a repeated section
                }
                startRepeating = -1; //resets repeat checker
                endRepeating = -1;   //resets repeat checker
            }
        }
        return totalDuration;
    }

    public boolean octaveDown() { //Decreases octave of all notes if possible, returning true, if not, returning false
        //Checks for special case
        for (Note note : notes) { //loops through notes
            if (note.getOctave() == 1) {
                return false; //returns false if any octave is too low
            }
        }
        // If there is no special case, increments all octaves down one
        for (Note note : notes) { //loops through notes
            if (!note.isRest()) {
                note.setOctave(note.getOctave() - 1); //sets octave to itself-1
            }
        }
        return true;
    }

    public boolean octaveUp() { //Increases octave of all notes if possible, returning true, if not, returning false
        //Checks for special case
        for (Note note : notes) {//loops through notes
            if (note.getOctave() == 10) {
                return false; //returns false if any octave is too high
            }
        }

        // If there is no special case, increments all octaves up y one
        for (Note note : notes) { //loops through notes
            if (!note.isRest()) {
                note.setOctave(1 + note.getOctave()); //sets octave to 1+itself
            }
        }
        return true;
    }

    public void play() { //plays "notes" in the order of "noteOrder"
        //these values store the beginning and ending indexes of repeat sections
        //they are also used to determine if a note is part of a repeated section
        //because there is no index <0, so they will only be -1 after a reset
        int startRepeating = -1;
        int endRepeating = -1;

        for (int i = 0; i < notes.length; i++) { //loops through noteOrder 
            notes[i].play();
            if (notes[i].isRepeat() && startRepeating == -1) {
                startRepeating = i; //checks if repeat started
            }
            else if (notes[i].isRepeat() && startRepeating != -1) { //checks if repeat is ver
                endRepeating = i;
                for (int s = startRepeating; s <= endRepeating; s++) { //loops through repeated section
                    notes[s].play(); //plays repeated notes
                }
                startRepeating = -1; //resets repeat checker
                endRepeating = -1;   //resets repeat checker
            }
        }
    }

    public void playOffset(double off) { //extra credit method that plays starting at an offset
        //these values store the beginning and ending indexes of repeat sections
        //they are also used to determine if a note is part of a repeated section
        //because there is no index <0, so they will only be -1 after a reset
        int startRepeating = -1;
        int endRepeating = -1;

        double offset = off;

        for (int i = 0; i < notes.length; i++) { //loops through noteOrder 
            offset -= notes[i].getDuration(); //subtracts from offset
            if (offset <= 0) {
                notes[i].play(); //when offset is passed, plays note
            }
            if (notes[i].isRepeat() && startRepeating == -1) {
                startRepeating = i; //checks if repeat started
            }
            if (notes[i].isRepeat() && startRepeating != -1) { //checks if repeat is ver
                endRepeating = i;
                for (int s = startRepeating; s <= endRepeating; s++) { //loops through repeated section
                    offset -= notes[i].getDuration(); //subtracts from offset
                    if (offset <= 0) {
                        notes[i].play(); //when offset is passed, plays note
                    }
                }
                startRepeating = -1; //resets repeat checker
                endRepeating = -1;   //resets repeat checker
            }
        }
    }

    public void reverse() {
            //reverses the notes of a song
        //by looping through and switching by i in from either side

        for (int i = 0; i < notes.length / 2; i++) {
            Note temp = notes[i];
            notes[i] = notes[notes.length - i - 1];
            notes[notes.length - i - 1] = temp;
        }
    }

    public void rickRoll() throws IOException {
            //extra credit method
        //opens link, called in MelodyGUI when a certain sequence of buttons are pressed
        // --> reverse reverse play
        try {
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        } catch (URISyntaxException ex) {
            Logger.getLogger(Melody.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void scrambleSong() {
            //extra credit method that scrambles the notes of a song
        //using a random number generator 

        Random randomNumber = new Random();
        for (int i = 0; i < notes.length; i++) {
            int randomPosition = randomNumber.nextInt(notes.length);
            Note temp = notes[i];
            notes[i] = notes[randomPosition];
            notes[randomPosition] = temp;
        }
    }
    
    public void mergeMelody(Melody melody2){
        numLines = numLines + melody2.numLines;
        Note[] notes1 = new Note[numLines];
        for(int i = 0; i < numLines; i++){
            if(i<notes.length) notes1[i] = notes [i];
            else if(i>=notes.length) notes1[i] = melody2.notes[i-notes.length];
        }
        notes = notes1;
        title = title + melody2.title;
        artist = artist+melody2.artist;
    }
}
