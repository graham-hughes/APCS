
/**
 * * * Graham Hughes * APCS Lab 9 * 11/9/2015
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionairyReader {
        int wordCount = 0;
        int palindromeCount = 0;
        int longestPalindromeLength = 0;
        int repetitiousCount = 0;
        int longestMatchLength = 0;
        int numberDoubleLetters = 0;
        int longestRepetitiousLength = 0;
        
        String line;
        ArrayList<String> palindrome = new ArrayList<>();
        ArrayList<String> palindromeLongest = new ArrayList<>();
        ArrayList<String> repetitious = new ArrayList<>();
        ArrayList<String> longestRepetitious = new ArrayList<>();
        ArrayList<String> longestMatch = new ArrayList<>();
        ArrayList<String> mostDoubleLettersSoFar = new ArrayList<>();     
        ArrayList<String> mostDoubleLetters = new ArrayList<>();     
        
        ArrayList<String> dictionairy = new ArrayList<>();
    public DictionairyReader () throws FileNotFoundException{
        Scanner reader = new Scanner(new FileReader("dictionary"));
//        DictionairyReader isReader = new DictionairyReader();
        
        while(reader.hasNextLine()){
            wordCount++;
            line = reader.nextLine();
            if (isMostDoubleLetters(line) >= numberDoubleLetters) {
                numberDoubleLetters = isMostDoubleLetters(line);
                mostDoubleLettersSoFar.add(line);
            }
            //fills the repetiitous ArrayList and determines
            //longestMatchLength and longestRepetitiousLength
            if (isPalindrome(line)) {
                palindromeCount++;
                palindrome.add(line);
                if(line.length()>longestPalindromeLength){
                    longestPalindromeLength = line.length();
                }
            }
            //fills the repetitous ArrayList and determines
            //longestMatchLength and longestRepetitiousLength
            if (line.length() > 5 && isRepetitious(line) > 2) {
                repetitiousCount++;
                repetitious.add(line);
                if (isRepetitious(line) > longestMatchLength) {
                    longestMatchLength = isRepetitious(line);
                }
                if (line.length() > longestRepetitiousLength) {
                    longestRepetitiousLength = line.length();
                }
            }
        }
        //fills the palindromeLongest ArrayList
        for (String str : palindrome){
            if(str.length() == longestPalindromeLength){
                palindromeLongest.add(str);
            }
        }
        for (String str : repetitious){
            //fills the longestRepetitious ArrayList
            if(str.length() == longestRepetitiousLength){
                longestRepetitious.add(str);
            }
            //fills the longestMatch ArrayList
            if(isRepetitious(str)==longestMatchLength){
                longestMatch.add(str);
            }
        }  
        //fills the mostDoubleLetters ArrayList
        for (String str : mostDoubleLettersSoFar){
            if(isMostDoubleLetters(str) == numberDoubleLetters){
                mostDoubleLetters.add(str);
            }
        }
    }
    
    //returns true/false for is/isn't a palindrome
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        return s.equalsIgnoreCase(sb.reverse().toString());
    }
    
    //returns the length of repetition for a given word
    public static int isRepetitious(String s) {
        int num = 0;
        int n = s.length() / 2 + 1;
        for (int i = 0; i < n; i++) {
            if (s.substring(0, i).equalsIgnoreCase(s.substring(s.length() - i, s.length()))) {
                num = i;
            }
        }
        return num;
    }
    
    //returns the number of double letters for a given word
    public static int isMostDoubleLetters(String s) {
        int num = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                num++;
            }
        }
        return num;
    }

    public void printStats() {
//      prints stats and words
        System.out.println("Number of words :: " + wordCount);
        System.out.println("Number of palindromes :: " + palindromeCount);
        System.out.println("Palindromes Found :: " + palindrome);
        System.out.println("Longest palindrome :: " + palindromeLongest);
        System.out.println("Number of repetitious :: " + repetitiousCount);
        System.out.println("Repetitious Found :: " + repetitious);
        System.out.println("Longest repetitious :: " + longestRepetitious);
        System.out.println("Longest matching prefix / suffix :: " + longestMatch);
        System.out.println("Most double letters (Extra Credit) :: " + mostDoubleLetters);
    }
}
