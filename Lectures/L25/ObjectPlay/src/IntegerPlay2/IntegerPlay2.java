/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntegerPlay2;

/**
 *
 * @author Professor Wergeles <Professor Wergeles at cs3330@missouri.edu>
 */
public class IntegerPlay2 {
    
    /**
     * @notes In this project we create several objects, we perform some simple
     *          additions and subtractions, it is proven that a new object is created
     *          whenever ANY manipulation is done to the variable (object) since
     *          the Integer object is immutable
     */
    public static void main(String[] args) {
        
        // 'a' holds an address (reference) to the Integer object created here
        Integer a = 5;
        
        // we now copy a's address over to b
        // now both 'a' and 'b' point to the same object
        Integer b = a;
        
        // 'c' holds a reference to the Integer object created here
        Integer c = 8;
        
        System.out.println("a " + System.identityHashCode(a) + " " + a.toString());
        System.out.println("b " + System.identityHashCode(b) + " " + b.toString());
        System.out.println("c " + System.identityHashCode(c) + " " + c.toString());
        
        // when we change th4e value, Intger is immutable, so the original a is
        //destroyed and a new one is created, a holds the value of 8 which happens
        // to be the same as 'c'
        a = a + 3;
  
        System.out.println("a " + System.identityHashCode(a) + " " + a.toString());
        System.out.println("b " + System.identityHashCode(b) + " " + b.toString());
        System.out.println("c " + System.identityHashCode(c) + " " + c.toString());
  
        // with ALL objects, you will never use the '==' because it's "lazy"
        // you will always use the .equals() method to compare objects
//        if (a == b) {     
        if (a.equals(b)) {
            
            System.out.println("a equals b"); 
            
        } else {

            System.out.println("a DOES NOT b"); 
            
        }
    }
}
