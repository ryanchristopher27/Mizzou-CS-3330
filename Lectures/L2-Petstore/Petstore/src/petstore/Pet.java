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
public class Pet {
    public String type;
    private String name;
    public int age;
    public Gender gender;
    
    // no-arg constructor
    public Pet() {
        // Constructors are special methods, with the same as the class and no return type
        
        age = 0;
    }
    
    public Pet(int age) {
        this.age = age;
    }
    
    public Pet(String type, String name) {        
        this.type = type;
        this.name = name;
    }
    
    // parameter constructor
    public Pet(String type, String name, int age, Gender gender) {
        this(type, name); // Constructor chaining
        
        this.age = age;
        this.gender = gender;
    }
    
    // Getter 
    public String getName() {
        return name;
    }
    
    // Setter
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void birthday() {
        age++;
    }
    
    public int getAge() {
        return age;
    }
}
