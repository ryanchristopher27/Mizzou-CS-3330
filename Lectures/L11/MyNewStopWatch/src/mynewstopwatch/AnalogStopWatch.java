/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynewstopwatch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Professor Wergeles
 */
public class AnalogStopWatch {
    
    private double secondsElapsed = 0;
    private double tickTimeInSeconds = 0.01; // How to change the resoluation of the watch
    private double angleDeltaPerSeconds = 6;
    
    // Makes variables global
    private Timeline timeline;
    private KeyFrame keyFrame;
    
    private StackPane rootContainer;
    private ImageView dialImageView;
    private ImageView handImageView;
    private Image dialImage;
    private Image handImage;
    private String dialImageName = "clockface.png";
    private String handImageName = "hand.png";
    
    public AnalogStopWatch(){
        setupUI();
        setupTimer();
    }
    
    public void setupUI() {
        
        rootContainer = new StackPane();
        dialImageView = new ImageView();
        handImageView = new ImageView();
//        rootContainer.getChildren().addAll(dialImageView, handImageView);
        
        Image dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        Image handImage = new Image(getClass().getResourceAsStream(handImageName));
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);
        
        HBox controlsButtons = new HBox();
        Button startStopButton = new Button("Stop");
        Button resetRecordButton = new Button("Reset");
        startStopButton.setMaxWidth(Double.MAX_VALUE);
        resetRecordButton.setMaxWidth(Double.MAX_VALUE);
        
        controlsButtons.setAlignment(Pos.BOTTOM_CENTER);
        controlsButtons.setSpacing(10);
        controlsButtons.setPadding(new Insets(25,25,25,25));
        controlsButtons.getChildren().addAll(resetRecordButton, startStopButton);
        rootContainer.getChildren().addAll(dialImageView, handImageView, controlsButtons);
        
        // Makes sure buttons are on top of everything else
        controlsButtons.toFront();
        
        
        resetRecordButton.setOnAction((ActionEvent event) -> {
            if (isRunning()) {
                // record
                setTickTimeInSeconds(0.01);
            }
            else {
                // reset
                setTickTimeInSeconds(1.0);
            }

            timeline.stop();
            handImageView.setRotate(0);
            secondsElapsed = 0;

        });
        
        startStopButton.setOnAction((ActionEvent event) -> {
            if (isRunning()) {
                timeline.pause();
                startStopButton.setText("Start");
//                startStop = false;
            }
            else {
                start();
                startStopButton.setText("Stop");
//                startStop = true;
            }
        });
    }
    
    public void setupTimer() {
        boolean wasItRunning = false;
        if (isRunning()) {
            timeline.pause();
            wasItRunning = true;
        }
        
        keyFrame = new KeyFrame(Duration.millis(1000 * tickTimeInSeconds), (ActionEvent event) -> {
            update();
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        
        if(wasItRunning) {
            timeline.play();
            wasItRunning = false;
        }
    }
    
    private void update() {
        // 360 degrees / 60 ticks = each tick 6 degrees
        secondsElapsed += tickTimeInSeconds;
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        handImageView.setRotate(rotation);
    }
    
    public boolean isRunning() {
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                return true;
            }
        }
        return false;
    }
    
    public Parent getRootContainer() {
        return rootContainer;
    }
    
    public Double getWidth() {
        if (dialImage != null) {
            return dialImage.getWidth();
        }
        else {
            return 0.0;
        }
    }
    
    public Double getHeight() {
        if (dialImage != null)
            return dialImage.getHeight();
        else
            return 0.0;
    }
    
    public void setTickTimeInSeconds(Double tickTimeInSeconds) {
        this.tickTimeInSeconds = tickTimeInSeconds;
//        if (isRunning()) {
//            timeline.pause();
//        }
        setupTimer();
    }
    
    public void start() {
        timeline.play();
    }
    
}
