/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvccpumonitorfxml;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 *
 * @author Professor Wergeles <Professor Wergeles at cs3330@missouri.edu>
 */
public class Model extends AbstractModel { 
    // step 1: extends abstractmodel 
    
    private double angle; 
    
    public Model(){
//        super(); 
        tickTimeInSeconds = 0.1;
        cpu = 0;
    }
 
    @Override
    protected void updateMonitor() {
        super.updateMonitor();
        updateAnalog(); 
    }
   
    public void updateAnalog(){
        double oldAngle = angle; 
        angle = calculateRotation(cpu);   
        
        // step 4: everytime a value is updated, let the controller know
        
        firePropertyChange("Analog", oldAngle, angle); 
//        firePropertyChange("Analog", null, angle);
    }
    
    public double calculateRotation(double cpu){ 
        return cpu * 360;
    }
}