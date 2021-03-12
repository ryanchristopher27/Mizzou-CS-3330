/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcstopwatch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author Professor Wergeles <Professor Wergeles at cs3330@missouri.edu>
 */
public class StopwatchModel {
    private double tickTimeInSeconds;
    private double angleDeltaPerSeconds;
    
    private double secondsElapsed;
    
    private Timeline timeline;
    private KeyFrame keyFrame;
    
    public StopwatchModel() {
        tickTimeInSeconds = 0.01;
        angleDeltaPerSeconds = 6.0;
        secondsElapsed = 0.0;
        // other init's
    }
    
    public void setupTimer(KeyFrame keyFrame) {
//        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
//            calculateRotation();
//            update();
//        });

        this.keyFrame = keyFrame;
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    public double calculateRotation() {
        secondsElapsed += tickTimeInSeconds;

        return secondsElapsed * angleDeltaPerSeconds;
        
        // DO NOT HAVE ANY VIEW ELEMENTS UPDATED BY MODEL
        // DO NOT HVAE ANY VIEW ELEMENTS DEFINED IN THE MODEL
//        handImageView.setRotate(rotation);
    }
    
    public void start() {
        timeline.play();
    }
    
    public double getSecondsElapsed() {
        return this.secondsElapsed;
    }
    
    public void setTimeline (Timeline timeline) {
        this.timeline = timeline;
    }
    
    public Timeline getTimeline() {
        return this.timeline;
    }
    
    public double getTickTimeInSeconds() {
        return this.tickTimeInSeconds;
    }
    
    public void setTickTimeInSeconds(Double tickTimeInSeconds) {
        this.tickTimeInSeconds = tickTimeInSeconds;
        setupTimer(keyFrame);
    }
    
    public void setKeyFrame(KeyFrame keyFrame) {
        this.keyFrame = keyFrame;
    }
    
    public KeyFrame getKeyFrame() {
        return this.keyFrame;
    }
}
