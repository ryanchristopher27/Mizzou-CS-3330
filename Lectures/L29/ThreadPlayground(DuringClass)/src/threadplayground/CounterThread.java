/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadplayground;

/**
 *
 * @author Professor Wergeles
 */
public class CounterThread extends Thread {
    
    public Boolean stop = false; 
    private Integer countTo = 0; 
    
    public CounterThread(Integer countTo){
        this.countTo = countTo; 
    }
     
    public void run() {
        for (int i = 0; i < countTo; i++) {
            System.out.println("Counter Thread i = " + i);
            
            // Thread stop() is depracated, so this is how you stop a thread
            // in the middle of execution
            if (stop) {
                return;
            }
        }
    }
}
