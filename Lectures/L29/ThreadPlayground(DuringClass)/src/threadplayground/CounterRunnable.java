/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadplayground;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 *
 * @author Professor Wergeles
 */
public class CounterRunnable {

    public Boolean stop = false; 
    
    private Integer countTo = 0; 
    private TextArea textArea; 
    
    public CounterRunnable(Integer countTo, TextArea textArea){
        this.countTo = countTo; 
        this.textArea = textArea; 
    }
    
    
    public void run() {
        for(int r = 0; r < countTo; r++){
            String message = "r = " + r; 
            
            textArea.appendText(message + "\n"); 
             
            if(stop){
                return; 
            }
    
            try { 
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(CounterRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
