/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rdcb2fmvctimerstopwatchfxmls21;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Ryan Christopher
 */
public class DigitalModel extends AbstractModel{
    
    public int startValue;
    int minutes = 0;
    int seconds = 0;
    int centis = 0;
    
    int lapMinutes = 0;
    int lapSeconds = 0;
    int lapCentis = 0;
    int totalLaps = 0;
    double maxLapTime = 0;
    
    int aveLapMinutes = 0;
    int aveLapSeconds = 0;
    int aveLapCentis = 0;
    
    boolean timesUp = false;
    
    private Timeline timeline;
    private KeyFrame keyFrame;
    
    private XYChart.Series<String, Number> seriesLine;
    private XYChart.Series<String, Number> seriesArea;
    
    public DigitalModel() {
        getStartingValue();
    }
    
    public void setupTimeline(KeyFrame keyFrame) {
        this.keyFrame = keyFrame;
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    // Updates the Digital Clock
    public String updateDigitalClock() {
        String minutesTemp, secondsTemp, centisTemp;
        
        if (seconds == 60) {
            minutes = minutes + 1;
            seconds = 0;
        }
        if (centis == 100) {
            seconds = seconds + 1;
            centis = 0;
        }
        
        minutesTemp = String.format("%02d", minutes);
        secondsTemp = String.format("%02d", seconds);
        centisTemp = String.format("%02d", centis);
//        stopWatchTextValue.setText(minutesTemp + ":" + secondsTemp + "." + centisTemp);
        
        String clockString = minutesTemp + ":" + secondsTemp + "." + centisTemp;

        centis++;
        
        return clockString;
    }
        
    public void updateLap() {
        if (lapSeconds == 60) {
            lapMinutes += 1;
            lapSeconds = 0;
        }
        if (lapCentis == 100) {
            lapSeconds += 1;
            lapCentis = 0;
        }
        lapCentis++;
    }
    
    public void resetLap() {
        lapMinutes = 0;
        lapSeconds = 0;
        lapCentis = 0;
    }
    
    public String setLapString() {
        String minutesTemp, secondsTemp, centisTemp;
        
        minutesTemp = String.format("%02d", lapMinutes);
        secondsTemp = String.format("%02d", lapSeconds);
        centisTemp = String.format("%02d", lapCentis);
        
        if (lapSeconds > maxLapTime) {
            maxLapTime = lapSeconds + (lapMinutes * 60);
        }
        
        String returnString = minutesTemp + ":" + secondsTemp + "." + centisTemp;
        
        totalLaps += 1;

        return returnString;
    }
    
    public String setAverageLapString() {
        double tempM, tempS, tempC;
        
        // This rounds the integers correctly
        tempM = (minutes / totalLaps) + 0.5;
        tempS = (seconds / totalLaps) + 0.5;
        tempC = (centis / totalLaps) + 0.5;
        
        aveLapMinutes = (int)tempM;
        aveLapSeconds = (int)tempS;
        aveLapCentis = (int)tempC;
        
        String tempMinutes = String.format("%02d", aveLapMinutes);
        String tempSeconds = String.format("%02d", aveLapSeconds);
        String tempCentis = String.format("%02d", aveLapCentis);
        
        String returnString = tempMinutes + ":" + tempSeconds + "." + tempCentis;
        
        return returnString;
    }
    
    // Gets the starting value from the user
    public void getStartingValue() {
        boolean hold = true;
        int start = 0;
        
        while (hold) {
            // Start of code from Reference 1
            TextInputDialog startingValue = new TextInputDialog("Enter a Positive Integer");
            startingValue.setHeaderText("Set up the start time:");
            startingValue.setContentText("Please set up the start time (integer):");

            startingValue.showAndWait();
            // End of code from Reference 1
            
            try {
                startValue = Integer.parseInt(startingValue.getEditor().getText());
                hold = false;
            }
            catch (Exception e) { // Code from Reference 2
                System.out.println("Error");
                continue;
            }
            
            if (start < 0) {
                hold = true;
            }
        }
    }
    
    public void resetValues() {
        minutes = 0;
        seconds = 0;
        centis = 0;
        totalLaps = 0;
        lapMinutes = 0;
        lapSeconds = 0;
        lapCentis = 0;
    }
    
    // Getters and Setters
    public int getTotalLaps() { return totalLaps; }
    public void setTotalLaps(int laps) { this.totalLaps = laps; }
    
    public int getMinutes() { return minutes; }
    public void setMinutes(int minutes) { this.minutes = minutes; }
    
    public int getSeconds() { return seconds; }
    public void setSeconds(int seconds) { this.seconds = seconds; }
    
    public int getCentis() { return centis; }
    public void setCenis(int centis) { this.centis = centis; }
    
    public int getLapMinutes() { return lapMinutes; }
    public void setLapMinutes(int lapMinutes) { this.lapMinutes = lapMinutes; }
    
    public int getLapSeconds() { return lapSeconds; }
    public void setLapSeconds(int lapSeconds) { this.lapSeconds = lapSeconds; }
    
    public int getLapCentis() { return lapCentis; }
    public void setLapCentis(int lapCentis) { this.lapCentis = lapCentis; }
    
    public int getAveLapMinutes() { return aveLapMinutes; }
    public void setAveLapMinutes(int aveLapMinutes) { this.aveLapMinutes = aveLapMinutes; }
    
    public int getAveLapSeconds() { return aveLapSeconds; }
    public void setAveLapSeconds(int aveLapSeconds) { this.aveLapSeconds= aveLapSeconds; }
    
    public int getAveLapCentis() { return aveLapCentis; }
    public void setAveLapCentis(int aveLapCentis) { this.aveLapCentis = aveLapCentis; }
    
    public boolean getTimesUp() { return timesUp; }
    public void setTimesUp(boolean timesUp) { this.timesUp = timesUp; }
    
    public int getStartValue() { return startValue; }
    public void setStartValue(int startValue) { this.startValue = startValue; }
    
    public double getMaxLapTime() { return maxLapTime; }
    
    public XYChart.Series<String, Number> getSeriesLine() { return seriesLine; }
    public void setSeriesLine(XYChart.Series<String, Number> seriesLine) { this.seriesLine = seriesLine; }
    
    public XYChart.Series<String, Number> getSeriesArea() { return seriesArea; }
    public void setSeriesArea(XYChart.Series<String, Number> seriesArea) { this.seriesArea = seriesArea; }
}
