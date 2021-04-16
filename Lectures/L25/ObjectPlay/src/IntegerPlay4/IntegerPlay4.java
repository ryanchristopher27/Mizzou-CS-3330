/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntegerPlay4;

/**
 *
 * @author Professor Wergeles <Professor Wergeles at cs3330@missouri.edu>
 */
public class IntegerPlay4 {
    
    /**
     * @notes This code was created to show the range (-128 to 127)
     *          We went all combinations to find '==' would fail
     *          This proof that when Integer is out of range your code
     *          will have a bug even though it compiles if you are using '=='
     *          to compare Integer objects. Therefore, we always use .equals() method
     * 
     *          Primitive type like "int" are faster with high performance for
     *          calculations, however with int, int cannot store numbers past a
     *          certain size limit. Primitive data types are mutable which means
     *          you can change their value at any time.
     * 
     *          Objects like Integer have more functionality, can be used as
     *          Generic Types, can use for HashMap keys, arraylist, etc. However,
     *          Integer is immutable which means if you want to affect or change
     *          the value of an Integer variable the only way is to create a new
     *          Integer object and discard the old one (which the garbage collection
     *          handles for us in java).
     */
    public static void main(String[] args) {
        
        Integer a = -140;
        Integer b = -140;
        
        int c = -140;
        int d = -140;
        
        for (int step = 0; step < 300; step++) {
            
            a++;
            b++;
            c++;
            d++;
            
            System.out.println("a: " + a + " ("+System.identityHashCode(a)+")");
            System.out.println("b: " + b + " ("+System.identityHashCode(b)+")");
            System.out.println("c: " + c + " ("+System.identityHashCode(c)+")");
            System.out.println("d: " + d + " ("+System.identityHashCode(d)+")");
            
            
            if (a == b) System.out.println("a == b");
            else  System.out.println("a != b");
            
            
            if (a == c) System.out.println("a == c");
            else  System.out.println("a != c");
            

            if (a == d) System.out.println("a == d");
            else  System.out.println("a != d");
            

            System.out.println("----------------------");
        }
    }
}
