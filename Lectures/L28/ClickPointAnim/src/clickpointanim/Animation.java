/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickpointanim;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author Professor Wergeles
 */

//public class Animation extends Thread {
public class Animation implements Runnable {
    
    public Boolean stop = false;
    
    private AnchorPane anchorPane;
    private Double xPosn;
    private Double yPosn;
    private Double minRadius = 1.0;
    private Double maxRadius = 100.0;//30.0;
    private Double currentRadius = 10.0;
    private Ellipse ellipse;
    private Long sleepTime = 20L;
    private Random random;
    private Double hue;
    
    public Animation(AnchorPane anchorPane, Double xPosn, Double yPosn) {
        this.anchorPane = anchorPane;
        this.xPosn = xPosn;
        this.yPosn = yPosn;
        random = new Random(System.currentTimeMillis()); // use time for seed
        //System.out.println("Random: " + random.nextDouble()); 
        hue = random.nextDouble() * 360.0;
        ellipse = new Ellipse();
        ellipse.setRadiusX(10);
        ellipse.setRadiusY(10);
        ellipse.setStrokeWidth(5);
        ellipse.setStroke(Color.hsb(hue, 1.0, 1.0, 0.5));
        ellipse.setFill(Color.hsb(hue, 1.0, 1.0, 0.0));
        ellipse.setCenterX(xPosn);
        ellipse.setCenterY(yPosn);
        anchorPane.getChildren().add(ellipse);
    }
    
//    @Override
    public void run() {
        while(!stop) {
            try {
                currentRadius++;
                
                if(currentRadius > maxRadius) { 
                    currentRadius = minRadius;
                }
                
                Platform.runLater(() -> {
                    ellipse.setRadiusX(currentRadius);
                    ellipse.setRadiusY(currentRadius);
                });
                
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void end() {
        stop = true; 
    }
    
    
}
