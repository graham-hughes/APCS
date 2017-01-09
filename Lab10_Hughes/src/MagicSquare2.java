/*
Graham Hughes
Mrs. Hemiup
November 23, 2015
Lab 10
*/
public class MagicSquare2
{
	private int[][] mat;

	//size the matrix and make the magic square pattern
	//write all nested loop code here in the constructor
	public MagicSquare2(int size)
	{
            mat = new int[size][size];
           
            int maxNum =size*size;
            
            int c = (size-1)/2 -1; //c starts in center
            int r = 1; //r starts at 0
            
            
            for(int i = 1; i<= maxNum; i++){ //increments the number to be added
                
                c +=1; //increments c
                if(c == size) c=0; // resets c to bound
                r -=1; //increments r
                if (r<0) r=size-1; //resets r to bound
                
                if(mat[r][c] !=0){
                    r +=1; //increments r
                    if(r == size) r=0; // resets r to bound
                    c -=1; //increments c
                    if (c<0) c=size-1; //resets c to bound
                    r+=1;
                }
                
                mat[r][c] = i;
            }
           System.out.println("" + toString());

	}


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