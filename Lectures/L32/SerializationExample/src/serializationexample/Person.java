/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializationexample;

/**
 *
 * @author Professor Wergeles
 */
public class Person implements java.io.Serializable {
    
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    
    
    
    public Person() {
        
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
   
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public String getGender() {
        return gender;
    }
    
    
}
