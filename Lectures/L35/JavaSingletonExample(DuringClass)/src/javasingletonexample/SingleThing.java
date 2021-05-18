/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasingletonexample;

/**
 *
 * @author Professor Wergeles
 * 
 * @notes
 * This example is based on:
 * https://sourcemaking.com/design_patterns/singleton/java/1
 * 
 * University of Maryland Computer Science researcher Bill Pugh has written 
 * about the code issues underlying the Singleton pattern when implemented 
 * in Java. Pugh's efforts on the "Double-checked locking" idiom led to 
 * changes in the Java memory model in Java 5 and to what is generally 
 * regarded as the standard method to implement Singletons in Java. 
 * The technique is known as the initialization on demand holder idiom, 
 * is as lazy as possible, and works in all known versions of Java. 
 * It takes advantage of language guarantees about class initialization, 
 * and will therefore work correctly in all Java-compliant compilers and 
 * virtual machines.
 *
 * The inner class is referenced no earlier (and therefore loaded no 
 * earlier by the class loader) than the moment that getInstance() is 
 * called. Thus, this solution is thread-safe without requiring special 
 * language constructs (i.e. volatile or synchronized).
 * 
 * 
 * http://www.oodesign.com/singleton-pattern.html
 * http://www.javaworld.com/article/2073352/core-java/simply-singleton.html
 * https://dzone.com/articles/singleton-design-pattern-–
 * 
 */
public class SingleThing {
    private String name = ""; 
    
    // 1) private inner class, this is your static member
    //      which will hold the single instance field/variable
    private static class SingleThingHolder {
        // static means only one memory location, to ensure one instance variable (object)
        private static final SingleThing INSTANCE = new SingleThing();
    }
    
    // 2) private constructor, no instances can be created outside the class
    private SingleThing() {
        
    }
    
    // 3) global access point to retreive and create the instance (object) variable
    public static SingleThing getInstance() {
        return SingleThingHolder.INSTANCE;
    }
    
    
    public String getName(){
        return name; 
    }
    public void setName(String name){
        this.name = name; 
    }

}

