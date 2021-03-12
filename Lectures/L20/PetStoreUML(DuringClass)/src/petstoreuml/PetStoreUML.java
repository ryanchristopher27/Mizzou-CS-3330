/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petstoreuml;

import pets.Cat;
import pets.Dog;
import pets.Gender;
import pets.Pet;

/**
 *
 * @author Professor Wergeles
 */
public class PetStoreUML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Dog dog = new Dog("Maggie", 5, Gender.FEMALE);
        dog.praiseDog();
        dog.assignLicense();
        String bark = dog.bark(3);
        System.out.println(dog);
        
        Cat cat = new Cat("Whiskey", 2, Gender.FEMALE);
        cat.assignLicense();
        System.out.println(cat);
        
        System.out.println("Num of Pets: " + Pet.getNumOfPets());
    }
    
}
