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
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Ryan Christopher
 */
public class Rdcb2fPrimaryVisualizerS21 implements Visualizer {
    
    private final String name = "Rdcb2f Primary Visualizer S21";
    
    private Integer numBands;
    private AnchorPane vizPane;
    
    private final Double bandHeightPercentage = 1.0;
    
    private Double width = 0.0;
    private Double height = 0.0;
    
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandHeight = 0.0;
    
    private final Double startHue = 0.0;
    
    private Rectangle[] shapes;
    
    public Rdcb2fPrimaryVisualizerS21() {
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
        shapes = new Rectangle[numBands];
        
        for (int i = 0; i < numBands; i++) {            
            Rectangle rectangle = new Rectangle();
            
            if (numBands > 100) {
                rectangle.setWidth(bandWidth);
            }
            else {
                rectangle.setWidth(bandWidth / 2);
            }
//            rectangle.setWidth(bandWidth);
            rectangle.setHeight(bandHeight / 8);
            rectangle.setX(bandWidth * i);
            rectangle.setY((height / 2) - (bandHeight / 8));
            rectangle.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            rectangle.setStyle("-fx-stroke: black");
            
            vizPane.getChildren().add(rectangle);
            shapes[i] = rectangle;
//            shapes[i].getStyleClass().add("rectangles");
        }
    }

    // Code reference from lecture
    @Override
    public void destroy() {
        if (shapes != null) {
            for (Rectangle rectangle : shapes) {
                vizPane.getChildren().remove(rectangle);
            }
            shapes = null;
        }
    }

    // Code reference from lecture
    @Override
    public String getVizName() {
        return name;
    }

    @Override
    public void visualize(double timestamp, double length, float[] magnitudes, float[] phases) {
        if (shapes == null) {
            return;
        }
        
        Integer num = min(shapes.length, magnitudes.length);

        for (int i = 0; i < num / 2; i++) {
            shapes[i].setHeight((260 - Math.abs(magnitudes[i] * 4)) * 2);
            shapes[i].setY((height / 2) - (shapes[i].getHeight() / 2));
            shapes[i].setFill(Color.hsb(startHue + (magnitudes[i] * -6), 1.0, 1.0, 1.0));
        }
        
        int index = 0;
        for (int i = num - 1; i >= num / 2; i--) {
            shapes[i].setHeight((260 - Math.abs(magnitudes[index] * 4)) * 2);
            shapes[i].setY((height / 2) - (shapes[index].getHeight() / 2));
            shapes[i].setFill(Color.hsb(startHue + (magnitudes[index] * -6), 1.0, 1.0, 1.0));
            index++;
        }
    }
}
