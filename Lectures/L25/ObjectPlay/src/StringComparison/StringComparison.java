/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringComparison;

/**
 *
 * @author Professor Wergeles <Professor Wergeles at cs3330@missouri.edu>
 */
public class StringComparison {
    
    /**
     * @notes 
     */
    public static void main(String[] args) {
        
        String name = "Nick";
        String name2 = "Nick";

        System.out.println("name: \"" + name + "\"");
        System.out.println("name2: \"" + name2 + "\"");
        
        System.out.println("\nname == \"Nick\":");

        if (name == "Nick") {
            System.out.println("They are equal!");
        } else {
            System.out.println("They are not equal!");
        }

        
        
        System.out.println("\nname2 == \"Nick\":");

        if (name2 == "Nick") {
            System.out.println("They are equal!");
        } else {
            System.out.println("They are not equal!");
        }

        
        
        System.out.println("\n\"Nick\" == name2:");

        if ("Nick" == name2) {
            System.out.println("They are equal!");
        } else {
            System.out.println("They are not equal!");
        }

        
        System.out.println("\n\"Nick\".equals(name):");

        if ("Nick".equals(name) ) {
            System.out.println("They are equal!");
        } else {
            System.out.println("They are not equal!");
        }

        
        System.out.println("\nname.equals(\"Nick\"):");

        if (name.equals("Nick") ) {
            System.out.println("They are equal!");
        } else {
            System.out.println("They are not equal!");
        }

        
        System.out.println("\n\"Nick\" == \"Nick\":");

        if ("Nick" == "Nick") {
            System.out.println("They are equal!");
        } else {
            System.out.println("They are not equal!");
        }

        
        
        System.out.println("\nname == name2:");

        if(name == name2){
            System.out.println("They are equal!");
        } else {
            System.out.println("They are not equal!");
        }

        
        
        System.out.println("\nname2 == name:");

        if(name2 == name){
            System.out.println("They are equal!");
        } else {
            System.out.println("They are not equal!");
        }

        
        System.out.println("\nname == name:");

        if(name == name){
            System.out.println("They are equal!");
        } else {
            System.out.println("They are not equal!");
        }

        
        
        System.out.println("\nname2 == name2:");

        if(name2 == name2){
            System.out.println("They are equal!");
        } else {
            System.out.println("They are not equal!");
        }
        
        
        
        System.out.println("\nname.equals(name2):");
        
        if(name.equals(name2)){
            System.out.println("They are equal!");
        } else {
            System.out.println("They are not equal!");
        }
    }
}
