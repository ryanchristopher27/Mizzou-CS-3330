/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvccpumonitorfxml;

import java.text.DecimalFormat;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 *
 * @author rdash
 */
public class DigitalModel extends AbstractModel {
    
    private String cpuString;
    
    public DigitalModel() {
        tickTimeInSeconds = 0.1;
        cpu = 0;
    }
    
    @Override
    protected void updateMonitor() {
        super.updateMonitor(); // cpu = getCpu();
        updateDigital();
    }
    
    public void updateDigital(){
        String oldString = cpuString; 
        cpuString = getCPUString(cpu); 

        //step 4
        
        firePropertyChange("Digital", oldString, cpuString); 
    }
    
    public String getCPUString(double cpu) {
        DecimalFormat formatter = new DecimalFormat("00.00");
        return formatter.format(cpu * 100);
    }
}
