/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pets;

import petstore.Gender;
import petstore.Pet;

/**
 *
 * @author rdash
 */
public class Cat extends Pet{
    
    // Constructors are special methods
    // same name as class, no return type
    public Cat(String name, int age, Gender gender) {
        super("Cat", name, age, gender);
        // 1) Call superclass constructor
        // 2) invoke superclass methods
        // 3) manipulate superclass fields
    }
    
    // method overloading (polymorphism)
    public void meow(int meow) {
        for(int i = 0; i < meow; i++) {
            System.out.println("meow.");
        }
    }
    
    public void meow() {
        meow(1);
    }
    
    String meow(String noise) {
        System.out.println(noise);
        return noise;
    }
    
    // method overriding (polymorphism)
    @Override
    public int getAge() {
        return age * 7;
    }
    
    // Types of Constructors
        // Default
        // No-arg
        // Parameterized
    
    // If you implement any constructor then you no lonber receive a default
    
    // Although you may see some people claim that the default and no-arg constructor
    // is same but in fact they are not, even if you write public Demo{} in your class
    // Demo it cannot be alled default constructor since you have written the code of it
    
}
