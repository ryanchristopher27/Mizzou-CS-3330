/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petstore;

//import static petstore.Gender.FEMALE;
//import static petstore.Gender.MALE;

import static petstore.Gender.*; 

/**
 *
 * @author Professor Wergeles
 */
public class PetStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Dog dog = new Dog("Fido", 3, MALE); 
        
        System.out.println(dog.getName() + " is " + dog.getAge() +  " years old"); 
        
        dog.birthday();
        
        System.out.println(dog.getName() + " is " + dog.getAge() +  " years old");
        
        Dog dog2 = new Dog("Halo", 11, FEMALE); 
        
        System.out.println(dog2.getName() + " is " + dog2.getAge() +  " years old");
        
        dog2.birthday();
        
        System.out.println(dog2.getName() + " is " + dog2.getAge() +  " years old");   
    }   
}