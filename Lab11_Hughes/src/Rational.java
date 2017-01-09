//Name - Graham Hughes
//Date - January 19, 2016
//Class - APCS
//Lab  - Lab #11 Rational class

class Rational implements Comparable<Rational>
{
    private int num;
    private int den;
    
    Rational() {
        num = 1;
        den = 1;
    }
    Rational(int a, int b) {
        num = a;
        den = b;
    }
    //resets rational numbers to the provided inputs
    void setRational(int a, int b) {
        num = a;
        den = b;        
    }
    
    //write a set method for numerator and denominator
    public void add(Rational  other)
    {
        //multiply num and den of each fraction 
        //by the den of the other before adding and then reducing
        num = this.num*other.den + other.num*this.den;
        den = this.den*other.den;
        reduce();
    }

    public void sub(Rational  other)
    {
        //multiply num and den of each fraction 
        //by the den of the other before subtracting and then reducing
        num = this.num*other.den - other.num*this.den;
        den = this.den*other.den;
        reduce();
    }

    public void mul(Rational  other)
    {
        num = this.num * other.num;
        den = this.den * other.den;
        reduce();     
    }

    public void div(Rational  other)
    {
        num = this.num / other.num;
        den = this.den / other.den;
        reduce();
    }

    private void reduce()
    {
        //finds largest number that can devide num and den
        int largestDivisor = gcd(num, den);  
        //divides num and den by largest divisor
        num = num / largestDivisor;
        den = den / largestDivisor;
    }

    //finds greatest common denominator (to use in reduce())
    private int gcd(int numOne, int numTwo)
    {
        int num1 = numOne;
        int num2 = numTwo;
        if(num2==0) return num1;
        else return gcd(num2, num1%num2);
    }

    public Object clone ()
    {
            return new Rational (this.num, this.den);
    }


    //ACCESSORS

    //write get methods for numerator and denominator


    public boolean equals(Object obj)
    {
        if(this.compareTo((Rational) obj) == 0) return true;
        return false;
    }

    public int compareTo(Rational other)
    {
        if(this.num*other.den  > other.num * this.den) return 1;
        if(this.num*other.den  < other.num * this.den) return -1;
        return 0;
    }

    @Override
    public String toString(){
        return num + "/" + den;
    }	
}