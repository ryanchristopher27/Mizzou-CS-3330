/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcexample;

import java.util.ArrayList;

/**
 *
 * @author Ryan  Christopher
 */
public class MVCExampleModel {
    // model = data
    private ArrayList<String>  people;
    
    // but also the methods that manipulate the date (CRUD operations)
    public MVCExampleModel() {
        people = new ArrayList<>();
    }
    
    public boolean addPeople(String person) {
        return people.add(person);
    }
    
    public ArrayList<String> getEveryone() {
        return people;
    }
    
    public boolean deleteEveryone() {
        people.clear();
        
        return people.isEmpty();
    }
}