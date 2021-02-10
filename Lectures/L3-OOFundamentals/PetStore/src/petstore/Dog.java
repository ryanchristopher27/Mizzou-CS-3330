/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petstore;

/**
 *
 * @author Professor Wergeles
 */
public class Dog extends Pet {
    
    public Dog(String name, int age, Gender gender){
        super("dog", name, age, gender); 
    }
    
    public void birthday(){
        age += 7; // age = age + 7; 
    }
}
