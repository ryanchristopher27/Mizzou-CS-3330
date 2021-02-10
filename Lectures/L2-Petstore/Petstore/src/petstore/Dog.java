/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petstore;

/**
 *
 * @author rdash
 */
public class Dog extends Pet {
    
    // Class field (static variable
    private static int numOfDogs = 0;
    
    // Instance field (non-static variable)
    private int id = 0;
    
    // Constant
    private static final String GENUS = "Canis";
    
    public Dog(String name, int age, Gender gender) {
        super("Dog", name, age, gender);
    }
    
//    @Override
//    public void birthday() {
//        age += 7;
//    }
    
    @Override
    public int getAge() {
        return age * 7;
    }
    
    public void bark(int num) {
        for (int i = 0; i < num; i++) {
            System.out.println("Bark!");
        }
    }
    
    public void bark() {
        bark(1);
    }
    
    // If a constructor does not explicitly invoke a superclass constructor, the
    // Java compiler automatically inserts a call to the no-argument costructor of the superclass
    
    // If your class claims to implement an interface, all methods defined by
    // that interface must appear in its source code before the class will compile
    
//    public void test() {
//        this.age = 10;
//    }
    
    // Polymorphism:
        // The most common use of polymorphism in OOP occurs when a parent class reference is used to refere to a child class object
}
