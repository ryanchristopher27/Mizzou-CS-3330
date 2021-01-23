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
    
    // no-arg constructor
    public Pet() {
        // Constructors are special methods, with the same as the class and no return type
    }
    
    // parameter constructor
    public Pet(String type, String name, int age) {
        this.type = type;
        this.name = name;
        this.age = age;
    }
    
    // Getter 
    public String getName() {
        return name;
    }
    
    // Setter
    public void setName(String name) {
        this.name = name;
    }
}
