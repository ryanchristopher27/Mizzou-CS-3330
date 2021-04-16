/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntegerPlay1;

/**
 *
 * @author Professor Wergeles <Professor Wergeles at cs3330@missouri.edu>
 */
public class IntegerPlay1 {
    
    /**
     * @notes We using the System.identifyHashCode() method to distinguish between
     *          objects since the hash function will return the same result if the
     *          same object is passed to it
     */
    public static void main(String[] args) {
       
        // primitive data type, store by value, no methods only operations
//        int a = 1;

        // Capital I denotes a class, so 'a' is an object, store by reference
        Integer a = 1;
        
        // We are calling the Integer class to create this variable 'a' will hold
        // a reference to this object's location
        
//        a.toString();

        System.out.println("a: " + a + " (" + System.identityHashCode(a) + ")");
       
        // we increment 'a' by 1, a new object is being created sing the Integer
        // object is immutable
        a++;
        System.out.println("a: " + a + " ("+System.identityHashCode(a)+")");
        
        // we decrease 'a' here, also a new object is being created
        a--;
        System.out.println("a: " + a + " ("+System.identityHashCode(a)+")");
    }
}
