/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntegerPlay5;

/**
 *
 * @author Professor Wergeles <Professor Wergeles at cs3330@missouri.edu>
 */
public class IntegerPlay5 {
    
    /**
     * @notes  
     */
    public static void main(String[] args) {
        
        Integer a = 100;
        
        Integer b = new Integer(100);

        System.out.println("a: " + a + " ("+System.identityHashCode(a)+")");
        System.out.println("b: " + b + " ("+System.identityHashCode(b)+")");
        

        if (a == b) System.out.println("a == b");
        else System.out.println("a != b");
        

        if (a.equals(b)) System.out.println("a equals b");
        else System.out.println("a doesn't equal b");
    }
}
