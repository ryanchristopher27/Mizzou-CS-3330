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
public class Pet {
    public String type; 
    private String name; 
    public int age; 
    public Gender gender; 
    
    public Pet() {
        
    }
    
    public Pet(String type, String name, int age, Gender gender) {
        this.type = type; 
        this.name = name; 
        this.age = age; 
        this.gender = gender; 
    }
    
    public void setName(String input){
        this.name = input; 
    }
    
    public String getName(){
        return this.name; 
    }
    
    public void birthday(){
        age++; 
    }
    public int getAge(){
        return age; 
    }
}