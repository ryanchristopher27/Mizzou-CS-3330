/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listandsetdemo;

import java.util.LinkedList;


/**
 *
 * @author Professor Wergeles
 */
public class LinkedListDemo {
    
    public static void main(String[] args){
        
        LinkedList<String> ll = new LinkedList<>();
        
        ll.add("Fig"); 
        ll.add("Banana"); 
        ll.add("Date"); 
        ll.add("Elderberry"); 
        ll.add("Cherry"); 
        
        System.out.println("Orginal contents of ll: " + ll); 
        
        
        
        System.out.println("Orginal contents of ll: " + ll);
        
        System.out.println("Iterate through list and print items."); 
        
        for(String item : ll){
            System.out.println(item);
        }
        
         
        
        System.out.println("Contents of ll after deletion: "+ ll);
        
        
        
        System.out.println("Contents after deleting first and last: "+ ll);
        
        
        
        System.out.println("ll after change: "+ ll);
        
        
        System.out.println("First: " + first); 
        
        System.out.println(ll.getFirst().getClass()); 
        
    }
    
}
