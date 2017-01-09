//Name - Graham Hughes
//Date - January 19, 2016
//Class - APCS
//Lab  - Lab #11

import static java.lang.System.*;
import java.util.Arrays;

public class Lab11 {

    public static void main(String args[]) {
        //Add test cases to test lab 11a
        String inputs = "11\n"
                + "freddy\n"
                + "at\n"
                + "elephant\n"
                + "whoooooodat\n"
                + "alice\n"
                + "tommy\n"
                + "bobby\n"
                + "it\n"
                + "at\n"
                + "about";
        //finds number of inputs
        int numWords = Integer.parseInt(inputs.substring(0, inputs.indexOf("\n")));
        inputs = inputs.substring(inputs.indexOf("\n"));
        //puts inputs into a string array
        String[] inputArr = inputs.split("\n");

        Word[] wordArr = new Word[numWords];

        //gets an array of word objects based on the inputs
        for (int i = 0; i < numWords; i++) {
            wordArr[i] = new Word(inputArr[i]);
        }

        //sorts the array using compareTo 
        Arrays.sort(wordArr);

        //prints out sorted
        for (Word wrd : wordArr) {
            System.out.println(wrd.toString());
        }

        //adds spacing between lab parts
        System.out.println("\n\n\n\n\n");

        //Test cases to test lab 11b
        Rational test = new Rational();
        out.println("test = " + test);

        Rational newOne = new Rational(3, 4);
        out.println("newOne = " + newOne);

        out.println("test.equals(newOne) = " + test.equals(newOne));

        newOne = (Rational) test.clone();
        out.println("\n\nnewOne after test.clone() = " + newOne);
        out.println("test.equals(newOne) = " + test.equals(newOne));

        Rational rOne = new Rational(1, 2);
        Rational rTwo = new Rational(2, 3);
        out.println("1/2.equals(2/3) = " + rOne.equals(rTwo));
        test.setRational(4, 6);
        out.println("2/3.equals(4/6) = " + rTwo.equals(test));

        out.println("\n\nrOne = " + rOne);
        out.println("rTwo = " + rTwo);

        out.println("rOne.compareTo(rTwo) = " + rOne.compareTo(rTwo));
        out.println("rTwo.compareTo(rOne) = " + rTwo.compareTo(rOne));

        rOne.add(rTwo);
        out.println("\n\nrOne.add(rTwo) = " + rOne);

        rOne.setRational(1, 2);
        rTwo.setRational(1, 3);
        rOne.add(rTwo);
        out.println("\n\n1/2.add(1/3) = " + rOne);

        rOne.setRational(4, 10);
        rTwo.setRational(3, 5);
        rOne.add(rTwo);
        out.println("\n\n4/10.add(3/5) = " + rOne);

        rOne.setRational(2, 10);
        rTwo.setRational(3, 6);
        rOne.add(rTwo);
        out.println("\n\n2/10.add(3/6) = " + rOne);

        //1/4 + 2/8 = 1/2
        rOne.setRational(1, 4);
        rTwo.setRational(2, 8);
        out.println("\n\n1/4.equals(2/8) = " + rOne.equals(rTwo));
        rOne.add(rTwo);
        out.println("\n\n1/4.add(2/8) = " + rOne);

        //1/6 + 2/8 = 5/12
        rOne.setRational(1, 6);
        rTwo.setRational(2, 8);
        out.println("\n\n1/6.equals(2/8) = " + rOne.equals(rTwo));
        rOne.add(rTwo);
        out.println("\n\n1/6.add(2/8) = " + rOne);

        //runs 
        SimpleStopwatch stop = new SimpleStopwatch();
    }
}
