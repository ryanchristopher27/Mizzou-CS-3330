/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasingletonexample;

/**
 *
 * @author Professor Wergeles
 */
public class JavaSingletonExample {
    
    public static void main(String[] args) {

//        SingleThing s1 = new SingleThing();

        // This is a static/class method because we are using the class as a
        // reference and no object was created
        SingleThing s1 = SingleThing.getInstance();
        
        SingleThing s2 = SingleThing.getInstance();
        
        Example example = new Example();
        
        if(s1 == s2){
            System.out.println("Same object (s1 == s2)"); 
        }
        
        if(example.sthing == s1){
            System.out.println("Same object (example.s3 == s1"); 
        }
        
        s1.setName("Nick");
        s2.setName("Stewey");
        
        System.out.println("s1 filename: " + s1.getName()); 
        System.out.println("s2 filename: " + s2.getName()); 
        System.out.println("example s3 filename: " + example.sthing.getName());
    }
}