/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdcb2fmvctimerstopwatchfxmls21;

/**
 *
 * @author Ryan Christopher
 */
public class AnalogModel extends AbstractModel{
    
    public double secondsElapsed = 0;
    private double tickTimeInSeconds = 0.01; // Used to change resolution
    public double angleDeltaPerSeconds = 6;
    
    public AnalogModel() {
        
    }
    
    public double findRotation() {
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        return rotation;
    }
    
    public void resetValues() {
        secondsElapsed = 0;
    }
    
//    public void setupMonitor() {        
//        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
//        });
//        timeline = new Timeline(keyFrame);
//        timeline.setCycleCount(Animation.INDEFINITE);
//    }
    
    /// Getters and Setters ///
    public double getSecondsElapsed() { return secondsElapsed; }
    public void setSecondsElapsed(double secondsElapsed) { this.secondsElapsed = secondsElapsed; }
    
    public double getTickTimeInSeconds() { return tickTimeInSeconds; }
}
