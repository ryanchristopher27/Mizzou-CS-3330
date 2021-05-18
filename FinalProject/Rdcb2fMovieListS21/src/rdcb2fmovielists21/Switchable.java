/*
 * Name: Ryan Christopher
 * Pawprint: rdcb2f
 * Class: CS 3330 Object Oriented Programming
 * Final Project: Movie List
 */
package rdcb2fmovielists21;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Ryan Christopher
 * 
 * Code taken from switchable lecture (Lecture 31)
 */
public abstract class Switchable {
    public static Scene scene;
    public static final HashMap<String, Switchable> CONTROLLERS = new HashMap<> ();
    
    private Parent root;
    
    public static Switchable add(String name) {
        Switchable controller; 
        
        controller = CONTROLLERS.get(name); 
        
        if (controller == null) {
            try {
                
                FXMLLoader loader = new FXMLLoader(Switchable.class.getResource(name + ".fxml"));
                 
                Parent root = loader.load();               
                controller = loader.getController();                
                controller.setRoot(root);                
                CONTROLLERS.put(name, controller); 
                
            } catch (IOException ex) {
                Logger.getLogger(Switchable.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error loading " + name + ".fxml \n\n " + ex); 
                controller = null; 
            } catch (Exception ex){
                System.out.println("Error loading " + name + ".fxml \n\n " + ex); 
                controller = null; 
            }
        }
        return controller; 
    }
    
    public static void switchTo(String name) {
        Switchable controller = CONTROLLERS.get(name);
        
        if (controller == null) {
            controller = add(name);
        }
        
        if (controller != null) {
            if (scene != null) {
                scene.setRoot(controller.getRoot());
            }
        }
    }
    
    public void setRoot(Parent root) {
        this.root = root;
    }
    
    public Parent getRoot() {
        return root;
    }
    
    public static Switchable getControllerByName(String name) {
        return CONTROLLERS.get(name);
    }
}
