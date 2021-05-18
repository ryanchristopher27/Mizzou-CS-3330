/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflectionexample;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Professor Wergeles
 * 
 * @References: https://www.journaldev.com/1789/java-reflection-example-tutorial
 */
public class ReflectionExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // Get Class using reflection
                Class<?> concreteClass = ConcreteClass.class;
                concreteClass = new ConcreteClass(5).getClass();
                try {
                        // below method is used most of the times in frameworks like JUnit
                        //Spring dependency injection, Tomcat web container
                        //Eclipse auto completion of method names, hibernate, Struts2 etc.
                        //because ConcreteClass is not available at compile time
                        concreteClass = Class.forName("reflectionexample.ConcreteClass");
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }
                
                System.out.println(concreteClass.getCanonicalName()); // prints com.journaldev.reflection.ConcreteClass

        // Get Super Class
                Class<?> superClass = null;
                try {

                    superClass = Class.forName("reflectionexample.ConcreteClass").getSuperclass();
                   

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ReflectionExample.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println(superClass); // prints "class com.journaldev.reflection.BaseClass"
                
                System.out.println(Object.class.getSuperclass()); // prints "null"
                System.out.println(String[][].class.getSuperclass());// prints "class java.lang.Object"

                
        // Get All Public Methods
                Method[] publicMethods = Class.forName("reflectionexample.ConcreteClass").getMethods();
                //prints public methods of ConcreteClass, BaseClass, Object
                System.out.println(Arrays.toString(publicMethods));
                      
        // Get All Public Fields
                //Get All public fields
                Field[] publicFields = Class.forName("reflectionexample.ConcreteClass").getFields();
                //prints public fields of ConcreteClass, it's superclass and super interfaces
                System.out.println(Arrays.toString(publicFields));
           
        // Get/Set Public Field Value        
                Field field = null;
                try {
                
                    field = Class.forName("reflectionexample.ConcreteClass").getField("publicInt");
                
                } catch (NoSuchFieldException ex) {
                    Logger.getLogger(ReflectionExample.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(ReflectionExample.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ConcreteClass obj = new ConcreteClass(5);
                
                try {

                    System.out.println(field.get(obj)); //prints 5
                    field.setInt(obj, 10); //setting field value to 10 in object
                    System.out.println(field.get(obj)); //prints 10

                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(ReflectionExample.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ReflectionExample.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}