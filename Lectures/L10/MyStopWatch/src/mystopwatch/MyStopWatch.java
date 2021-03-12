/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystopwatch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Professor Wergeles 
 */
public class MyStopWatch extends Application {
    
    private double secondsElapsed = 0;
    private double tickTimeInSeconds = 0.01; // How to change the resoluation of the watch
    private double angleDeltaPerSeconds = 6;
    
    // Makes variables global
    private Timeline timeline;
    private ImageView dialImageView;
    private ImageView handImageView;
        
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("StopWatch");
        
        StackPane root = new StackPane();
        dialImageView = new ImageView();
        handImageView = new ImageView();
        root.getChildren().addAll(dialImageView, handImageView);
//        Image test = new Image(getClass().getResourceAsStream("shakeIt.gif"));
        Image dialImage = new Image(getClass().getResourceAsStream("clockface.png"));
        Image handImage = new Image(getClass().getResourceAsStream("hand.png"));
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
        root.getChildren().add(controlsButtons);
        
        
        resetRecordButton.setOnAction((ActionEvent event) -> {
//            if (isRunning()) {
//                // record
//            }
//            else {
//                // reset
//            }

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
                timeline.play();
                startStopButton.setText("Stop");
//                startStop = true;
            }
        });
        
        
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000 * tickTimeInSeconds), (ActionEvent event) -> {
//            System.out.println("Timeline Event!");
            
            // 360 degrees / 60 ticks = each tick 6 degrees
            secondsElapsed += tickTimeInSeconds;
            double rotation = secondsElapsed * angleDeltaPerSeconds;
            handImageView.setRotate(rotation);
        });
        
        timeline = new Timeline(keyFrame);
        
        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();

        double width = dialImage.getWidth();
        double height = dialImage.getHeight();
        
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        
    }
    
    public boolean isRunning() {
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
}