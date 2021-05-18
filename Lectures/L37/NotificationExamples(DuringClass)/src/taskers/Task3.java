/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskers;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.application.Platform;

/**
 *
 * @author Professor Wergeles
 * 
 * @notes This example uses PropertyChangeSupport to implement
 * property change listeners.
 * 
 */

public class Task3 extends Thread {
    
    private int maxValue, notifyEvery;
    boolean exit = false;
    
    private final PropertyChangeSupport pcs;
    
    public Task3(int maxValue, int notifyEvery)  {
        this.maxValue = maxValue;
        this.notifyEvery = notifyEvery;
        
        this.pcs = new PropertyChangeSupport(this);
    }
    
    @Override
    public void run() {
        doNotify("Task3 start.");
        for (int i = 0; i < maxValue; i++) {
            
            if (i % notifyEvery == 0) {
                doNotify("It happened in Task3: " + i);
            }
            
            if (exit) {
                return;
            }
        }
        doNotify("Task3 done.");
    }
    
    public void end() {
        exit = true;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    
    private void doNotify(String message) {
       // this provides the notification throught the property change listener
       Platform.runLater(() -> {
           // I'm choosing not to send the old value (second parameter)
           // you could send "" or null instead
           pcs.firePropertyChange("message", null, message);
       });
    }
}