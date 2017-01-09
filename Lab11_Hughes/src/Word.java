//Name - Graham Hughes
//Date - January 19, 2016
//Class - APCS
//Lab  - Lab #11 Word class

public class Word implements Comparable<Word>
{
	private final String word;

	public Word( String s)
	{
            word = s;
	}

        @Override
	public int compareTo(Word rhs)
	{
            //tries to sort by number of values
            if(getNumVowels(this) > getNumVowels(rhs)) return 1;
            else if(getNumVowels(this) < getNumVowels(rhs)) return -1;
            
            //if they have the same number of values, sorts alphabetically
            else return this.toString().compareTo(rhs.toString());
     	}

	public int getNumVowels(Word wrd)
	{
           int numVowels = 0;
           for(int i=0;i < wrd.word.length();i++){
                if((wrd.word.charAt(i) == 'a') || 
                    (wrd.word.charAt(i) == 'e')  ||
                    (wrd.word.charAt(i) == 'i') || 
                    (wrd.word.charAt(i) == 'o') ||
                    (wrd.word.charAt(i) == 'u')) {
                    numVowels++;
                }
            } 
           return numVowels;
	}
        
        @Override
        public String toString(){
            return word;
        }
}