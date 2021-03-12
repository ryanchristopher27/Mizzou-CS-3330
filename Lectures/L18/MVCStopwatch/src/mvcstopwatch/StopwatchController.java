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
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Professor Wergeles <Professor Wergeles at cs3330@missouri.edu>
 */
public class StopwatchController {
   
    private StackPane rootContainer;
    private ImageView dialImageView;
    private ImageView handImageView;
    private Image dialImage;
    private Image handImage;
    private String dialImageName = "clockface.png";
    private String handImageName = "hand.png";
    
    private StopwatchModel model;
    
    
    public StopwatchController() {
        model = new StopwatchModel();
        setupUI();
        model.setupTimer(new KeyFrame(Duration.millis(model.getTickTimeInSeconds() * 1000), (ActionEvent event) -> {
            // Don't do this, the model is affecting the view
            update();
        }));
    }
    
    private void update() {
        handImageView.setRotate(model.calculateRotation());
    }
    
    private void setupUI() {
        rootContainer = new StackPane();
        dialImageView = new ImageView();
        handImageView = new ImageView();    
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        handImage = new Image(getClass().getResourceAsStream(handImageName));
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);
        rootContainer.getChildren().addAll(dialImageView, handImageView);
    }
    
    public void start() {
        model.start();
    }

    public Parent getRootContainer() {
        return rootContainer;
    }
    
    public Double getWidth() {
        if (dialImage != null) return dialImage.getWidth();
        else return 0.0;
    }
    
    public Double getHeight() {
        if (dialImage != null) return dialImage.getHeight();
        else return 0.0;       
    }
}
