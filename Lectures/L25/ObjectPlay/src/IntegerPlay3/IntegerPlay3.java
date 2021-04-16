/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntegerPlay3;

/**
 *
 * @author Professor Wergeles <Professor Wergeles at cs3330@missouri.edu>
 */
public class IntegerPlay3 {
    
    /**
     * @notes  We increment -128 in the range (-128 to 127) the '==' will work.
     *          Why? This is because of Object Interning.
     * 
     *          Remember never use '==' because whenever you compare reference
     *           objects (not primitive type) you should use the .equals() method
     *           Why? Because if you use the '==' with objects, it is actually
     *           only referring tot he reference (memory location) of  the object
     *           and not the value. When '==' is used for Primitive data types
     *           it's comparing the actual value. But with Integer, String, Boolean,
     *           Double, Dog, Cat, etc. it is not comparing the actual value
     */
    public static void main(String[] args) {
        
        Integer x = -129;
        
        Integer y = -130;
        
        System.out.println("x " + System.identityHashCode(x) + " " + x.toString());
        System.out.println("y " + System.identityHashCode(y) + " " + y.toString());

        y = y + 1;
        
        System.out.println("x " + System.identityHashCode(x) + " " + x.toString());
        System.out.println("y " + System.identityHashCode(y) + " " + y.toString());

        
        if (x == y) {
            System.out.println("They are the same object"); 
        } else {
            System.out.println("They are different objects"); 
        }
        
        
        if (x.equals(y)) {
            System.out.println("They are the same value"); 
        } else {
            System.out.println("They are the different values");
        }
    }
}
