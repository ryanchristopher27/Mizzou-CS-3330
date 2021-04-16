/*
 * Name: Ryan Christopher
 * Pawprint: rdcb2f
 * Class: CS 3330
 * Challenge: AudioVisualiser #10
 * Date: 14 April, 2021
 */
package audioviz;

import static java.lang.Integer.min;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author Ryan Christopher
 */
public class Rdcb2fSecondaryVisualizerS21 implements Visualizer{
    
    private final String name = "Rdcb2f Secondary Visualizer S21";
    
    private Integer numBands;
    private AnchorPane vizPane;
    
    private final Double bandHeightPercentage = 1.0;
    private final Double minEllipseRadius = 10.0;
    
    private Double width = 0.0;
    private Double height = 0.0;
    
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandHeight = 0.0;
    
    private final Double startHue = 0.0;
    
    private Ellipse[] ellipses;
    
    public Rdcb2fSecondaryVisualizerS21() {

    }
    
    // Code reference from lecture
    @Override
    public void setup(Integer numBands, AnchorPane vizPane) {
        destroy();
        
        this.numBands = numBands;
        this.vizPane = vizPane;
        
        height = vizPane.getHeight();
        width = vizPane.getWidth();
        
        bandWidth = width / numBands;
        bandHeight = height * bandHeightPercentage;
        halfBandHeight = bandHeight / 2;
        ellipses = new Ellipse[numBands];
        
        for (int i = 0; i < numBands; i++) {
            Ellipse ellipse = new Ellipse();
            ellipse.setCenterX(bandWidth / 2 + bandWidth * i);
            ellipse.setCenterY(height / 2);
            ellipse.setRadiusX(bandWidth / 2);
            ellipse.setRadiusY(minEllipseRadius);
            ellipse.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            ellipse.setStyle("-fx-stroke: black");
            
            vizPane.getChildren().add(ellipse);
            ellipses[i] = ellipse;
        }
    }
    
    // Code reference from lecture
    @Override
    public void destroy() {
        if (ellipses != null) {
            for (Ellipse ellipse : ellipses) {
                vizPane.getChildren().remove(ellipse);
            }
            ellipses = null;
        }
    }

    // Code reference from lecture
    @Override
    public String getVizName() {
        return name;
    }

    @Override
    public void visualize(double timestamp, double lenght, float[] magnitudes, float[] phases) {
        if (ellipses == null) {
            return;
        }
        
        Integer num = min(ellipses.length, magnitudes.length);
        
        for (int i = 0; i < num / 2; i++) {
            ellipses[i].setRadiusY( ((60.0 + magnitudes[i])/60.0) * halfBandHeight + minEllipseRadius);
            ellipses[i].setTranslateY((Math.abs(magnitudes[i]) * (2)) - 120);
            ellipses[i].setTranslateX((Math.abs(magnitudes[i]) * (-1)) + 60);
            ellipses[i].setRotate((Math.abs(magnitudes[i]) - 60) * (-1.3));
            ellipses[i].setFill(Color.hsb(startHue + (magnitudes[i] * -3.0), 1.0, 1.0, 1.0));
        }
        
        int index = 0;
        for (int i = num - 1; i >= num / 2; i--) {
            ellipses[i].setRadiusY( ((60.0 + magnitudes[index])/60.0) * halfBandHeight + minEllipseRadius);
            ellipses[i].setTranslateY((Math.abs(magnitudes[index]) * (-2)) + 120);
            ellipses[i].setTranslateX(Math.abs(magnitudes[index]) - 60);
            ellipses[i].setRotate((Math.abs(magnitudes[index]) - 60) * (-1.3));
            ellipses[i].setFill(Color.hsb(startHue - (magnitudes[index] * -3.0), 1.0, 1.0, 1.0));
            index++;
        }
    }
    
}
