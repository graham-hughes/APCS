/*
Graham Hughes
Mrs. Hemiup
November 23, 2015
Lab 10
*/
import java.util.ArrayList;
public class MagicSquare1
{
       int[][] mat;
       int matSize;
	//size the matrix and load in the numbers into the matrix
	//write all nested loop code here in the constructor
	public MagicSquare1(int size, String numbers)
	{
            matSize = size;
            mat = new int [size][size];
            
            // Removes the spaces and turns the numbers into int's in an ArrayList
            int i = 0;
            String nums = numbers;
            ArrayList <Integer> numbersArrayList = new ArrayList<>();
            while(!nums.isEmpty()){
                String subString;
                int strToInt;
                if (nums.length()>1){
                    subString = nums.substring(0,nums.indexOf(' ', 0));
                    nums = nums.substring(nums.indexOf(' ',0)+1);
                }else{ 
                    subString = nums.substring(0);
                    nums = "";
                }
                strToInt = Integer.decode(subString);
                numbersArrayList.add(strToInt);
            } 
            
            //Adds the ArrayList of ints to a matrix, mat
            for(int r = 0; r<size;r++){
                for(int c = 0; c <size; c++){
                    mat[c][r] = numbersArrayList.get(0);
                    numbersArrayList.remove(0);
                }
            }
            //prints out the matrix and whether or not it is magic
            System.out.println("" + toString() +  isMagicSquare()+ "\n");
	}
        

        // I decided not to use dedicated sumRow / sumColumn / sumLeftDiagonal / sumRightDiagonal
        // methods, becuase I can check everything in just four loops if I do it all in
        // one method, which is more efficient than the seven loops it would take otherwise.
	public boolean isMagicSquare()
	{
            boolean isMagic = true;
            int totalAdded = 0;
            
            //determines the number to be tested against
            for(int r=0; r<matSize; r++){
              totalAdded += mat[0][r];
            }
           
            //checks that rows and columns add up to totalAdded
            for(int r=0; r<matSize; r++){
              int temp1=0;
              int temp2=0;
              for(int c=0; r<matSize; r++){
                 temp1 += mat[c][r];
                 temp2 += mat[r][c];
              }
              if(temp1 != totalAdded||temp2 != totalAdded) isMagic = false;
            }

            //checks that both diagonals add up to totalAdded
            int temp1 = 0;
            int temp2 = 0;
            for(int i=0;i<matSize; i++){
                temp1 += mat[i][i];
                temp2 += mat[i][matSize-i-1];
            }
            if(temp1 != totalAdded || temp2 != totalAdded) isMagic = false;
            return isMagic;
	}
        
        //turns mat into a string
	public String toString()
	{            
            String output= "";                
            String str = "\t";
            for(int i=0;i<mat.length;i++){
                for(int j=0;j<mat.length;j++){
                    str += mat[i][j] + "\t";
                }
                output = output + str  + "\n";
                str = "\t";   
            }
            return output;
	}
}