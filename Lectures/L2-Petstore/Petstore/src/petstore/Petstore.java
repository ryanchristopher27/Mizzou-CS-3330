/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petstore;

import pets.Cat;
import static petstore.Gender.FEMALE;
import static petstore.Gender.MALE;

/**
 *
 * @author rdash
 */
public class Petstore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Created an empty object
        Pet pet = new Pet();
        
        // Sets the values of all the aspects of the object
        pet.setName("Maggie");
        pet.age = 3;
        pet.gender = Gender.FEMALE;
        pet.type = "Dog";
        
        Pet pet2 = new Pet("Cat", "Kitty", 2, Gender.FEMALE);
        
        String name = pet.getName();
        
        System.out.println("Pet name: " + name);
        System.out.println("Pet2 name: " + pet2.getName());
        
        // Code from lecture 3
        Dog dog = new Dog("Fido", 3, MALE);
        
        System.out.println(dog.getName() + " is " + dog.getAge() + " years old");
        
        dog.birthday();
        
        System.out.println(dog.getName() + " is " + dog.getAge() + " dog years old");
        
        Dog dog2 = new Dog("Halo", 11, FEMALE);
        
        System.out.println(dog2.getName() + " is " + dog2.getAge() + " dog years old");
        
        dog2.birthday();
        
        System.out.println(dog2.getName() + " is " + dog2.getAge() + " dog years old");
        
        //What can you do in a subclass?
            // Inherited fields and methods can be used directly as they are, just like any other fields and methods
            // Declare fields in subclass with same name as one in superclass
            // Declare new fields and methods in subclass that are not in superclass
            // You can write new instance method in subclass with some siganature as one in superclass
            // You can write new static method in subclass with same signature as one in superclass
            // You can write a subclass constructore that invokes the constructor of the superclass, either implicitly or by using the keyword super
            
        // Code from lecture 4
        Cat cat = new Cat("Susan", 2, FEMALE);
        
        // Polymorphism (most important type)
            // When you put a subclass in a superclass
        Pet cat2 = new Cat("Poly", 3, MALE);
        
        Pet dog3 = new Dog("Poly2", 3, FEMALE);
        
        // Code from lecture 5
        Cat cat1 = new Cat("Susan", 2, FEMALE);
        
        // Implicit casting (polymorphism)
        Pet pet3 = cat1;
//        Pet pet4 = dog3; // Test to show polymorphism
        
//        pet3.meow();

        // If we try it the other way around, we get an error
        // Why? Because, not all pets are cats
//        Cat cat4 = pet3;
        
        // Explicit casting (not polymorphism
            // Inserts a runtime check that checks to see if pet3 was ever a cat prior
        Cat cat3 = (Cat) pet3;
//        Cat cat4 = (Cat) pet4; // Test to show not polymorphism
        
        cat3.meow();
        
        Cat cat5 = null;
        // We also do a logical test as to the type of a particular object
        // using the instanceof operator
        if (pet3 instanceof Cat) {
            cat5 = (Cat) pet3;
            
            cat5.meow(5);
        }
        
//        cat5.meow(5);
        
        // Test to try it the other way around ... failed .. runtime error
//        Pet test = new Pet("Cat", "No", 2, FEMALE);
        
//        Cat test2 = (Cat) test;

        Pet pet1 = new Pet(10);
        Pet pet4 = new Pet();
        
        System.out.println(pet1.getAge());
        System.out.println(pet4.getAge());

    }
    
}