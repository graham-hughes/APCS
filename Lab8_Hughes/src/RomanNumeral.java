/*
Graham Hughes
Mrs. Hemiup
November 2, 2015
Lab 8
*/
import static java.lang.System.*;

public class RomanNumeral
{
	private Integer number = 0;
	private String roman ="";

	private final static int[] NUMBERS= {1000,900,500,400,100,90,50,40,10,9,5,4,1};

	private final static String[] LETTERS = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

	public RomanNumeral(String str)
	{
            roman = str;
            number = 0;
	}

	public RomanNumeral(Integer orig)
	{
            number = orig;
            roman = "";
	}

	public void setNumber(Integer num)
	{
            number = num;
            roman = "";
	}

	public void setRoman(String rom)
	{
            roman = rom;
            number = 0;
	}

	public Integer getNumber()
	{
            while(roman.length()>0){
                for (int i = 0; i < LETTERS.length; i++) {
                    int length = LETTERS[i].length();
                    if(roman.length()>=length && roman.substring(0,length).equals(LETTERS[i])){
                        roman = roman.substring(length);
                        number += NUMBERS[i];
                        break;
                    }
                }
            }
            return number;
	}

	public String toString()
	{
            while (number>0){
                for(int i =0; i<NUMBERS.length; i++){
                    if (number >= NUMBERS[i]){
                        number -= NUMBERS[i];
                        roman += LETTERS[i];
                        break;
                    }
                }
            }
		return roman + "\n";
	}
}