/*
 * Name: Ryan Christopher
 * Pawprint: rdcb2f
 * Class: CS 3330 Object Oriented Programming
 * Challenge: Challenge 7 CPU Monitor
 * Date: March 2, 2021
 */
package rdcb2ffxmlcpumonitors21;

import static java.lang.Double.isNaN;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ryan Christopher
 * 
 * @references
 *  1) https://stackoverflow.com/questions/41956339/javafx-linechart-clean-data-from-chart
 */
public class Rdcb2fFXMLCPUMonitorS21Controller implements Initializable {

    // Declaring all FXML variables
    @FXML
    private GridPane root;
    @FXML
    private StackPane cpuGuageStack;
    @FXML
    private Text currentCPUUsageValue;
    @FXML
    private Button startStopButton;
    @FXML
    private Button recordResetButton;
    @FXML
    private Label peakCPUUsageLabel;
    @FXML
    private Text peakCPUUsageValue;
    @FXML
    private Label meanCPUUsageLabel;
    @FXML
    private Text meanCPUUsageValue;
    @FXML
    private LineChart<String, Number> recordedCPUUsageGraph;
    @FXML
    private AreaChart<String, Number> meanCPUUsageGraph;
    @FXML
    private NumberAxis recordedCPUGraphYAxis;
    @FXML
    private CategoryAxis recordedCPUGraphXAxis;
    @FXML
    private NumberAxis meanCPUGraphYAxis;
    @FXML
    private CategoryAxis meanCPUGraphXAxis;
    @FXML
    private ImageView cpuGuageImage;
    @FXML
    private ImageView cpuHandImage;
    
    private XYChart.Series<String, Number> seriesLine;
    private XYChart.Series<String, Number> seriesArea;
    
        
    // Initializing Controller Variables
    double currentCPU = 0;
    double meanCPU = 0;
    int totalCPUMeasurements = 0;
    double totalCPUAmount = 0;
    double peakCPU = 0;
    int recordCount = 1;
    
    // Declare timeline
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), (ActionEvent event) -> { 
        updateCPU();
        findMeanCPU();
        updatePeakCPU();
        updateMeanCPU();
        updateClockHand();
        updateMeanGraph();
    }));
    
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        seriesLine = new XYChart.Series();
//        recordedCPUGraphYAxis.setAutoRanging(false);
        recordedCPUGraphYAxis.setLowerBound(0);
//        recordedCPUGraphYAxis.setUpperBound(100);
        recordedCPUUsageGraph.getData().add(seriesLine);
        recordedCPUUsageGraph.setAnimated(false);
        
        seriesArea = new XYChart.Series();
        meanCPUGraphYAxis.setAutoRanging(false);
        meanCPUGraphYAxis.setLowerBound(0);
        meanCPUGraphYAxis.setUpperBound(100);
        meanCPUUsageGraph.getData().add(seriesArea);
        meanCPUUsageGraph.setAnimated(false);
        
    }    
    
    // Action event for when the start/stop button is pressed
    @FXML
    private void startStopButtonClicked(ActionEvent event) {
        if (isRunning()) {
            timeline.pause();
            startStopButton.setText("Start");
            recordResetButton.setText("Reset");
//            startStopButton.setBackground(Color.LIGHTGREEN);
            startStopButton.setStyle("-fx-background-color: lightgreen; ");
        }
        else {
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            startStopButton.setText("Stop");
            recordResetButton.setText("Record");
//            startStopButton.setBackground(Color.RED);
            startStopButton.setStyle("-fx-background-color: red; ");
        }
    }
    
    // Action event for when the record/reset button is pressed
    @FXML
    private void recordResetButtonClicked(ActionEvent event) {
        if (isRunning()) {
            // Code when record is hit
            updateRecordGraph();
        }
        else {
            // Code when reset is hit
            reset();
        }
    }
    
    // Used to get the current CPU usage of the computer
    // Returns a double value of the CPU usage
    public double getCPUUsage() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        double value = 0;
        for(Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getName().startsWith("getSystemCpuLoad")&& Modifier.isPublic(method.getModifiers())) {
                try {
                    value = (double) method.invoke(operatingSystemMXBean);
                }
                catch (Exception e) {
                    value = 0;
                }
                return value;
            }
        }
        return value;
    }
    
    // Checks if the timeline is running
    public boolean isRunning() {
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                return true;
            }
        }
        return false;
    }
    
    // Updtates the value of the currentCPU by using getCPUUsage()
    private void updateCPU() {
//        String cpuTemp;
        double oldCPU = currentCPU;
        currentCPU = getCPUUsage() * 100;  
        
        // Checks if new CPU value is a double
        if (isNaN(currentCPU)) {
            currentCPUUsageValue.setText(String.format("%.2f", oldCPU) + "%");
        }
        else {
            currentCPUUsageValue.setText(String.format("%.2f", currentCPU) + "%");
        }
            
        if (currentCPU > peakCPU) {
            peakCPU = currentCPU;
        }
    }
    
    // Ensures that the Peak CPU value is up to date
    private void updatePeakCPU() {
        peakCPUUsageValue.setText(String.format("%.2f", peakCPU) + "%");
    }
    
    // Calculates the Mean CPU
    private void findMeanCPU() {
//        double currentCPUUsage = currentCPU;
        totalCPUMeasurements += 1;
        totalCPUAmount += currentCPU;
        
        meanCPU = totalCPUAmount / totalCPUMeasurements;
    }
    
    // Ensures that the Mean CPU value is up to date
    private void updateMeanCPU() {
        meanCPUUsageValue.setText(String.format("%.2f", meanCPU) + "%");
    }
    
    // Sets the values of all the text objects to 0.00%
    private void setValuesToZero() {
        currentCPUUsageValue.setText("0.00%");
        peakCPUUsageValue.setText("0.00%");
        meanCPUUsageValue.setText("0.00%");
    }
    
    // Sets the rotation of the clock hand to point at 0
    private void setClockHandToZero() {
        cpuHandImage.setRotate(195);
    }
    
    // Ensures that the clock hand is pointing to the correct location on the guage
    private void updateClockHand() {
        double cpuTemp = currentCPU;
        int cpuTheta = (int)cpuTemp * 3;
        
        cpuHandImage.setRotate(195 + cpuTheta);
    }
    
    // Ensures that the Record Graph value is up to date
    private void updateRecordGraph() {  
        seriesLine.getData().add(new XYChart.Data(Integer.toString(recordCount), currentCPU));
        recordCount += 1;
    }
    
    // Ensures that the Mean Graph value is up to date
    private void updateMeanGraph() {
        seriesArea.getData().add(new XYChart.Data(Integer.toString(totalCPUMeasurements), meanCPU));
    }
    
    // Contains all code that must be reset when the reset button is pressed
    private void reset() {
        totalCPUMeasurements = 0;
        totalCPUAmount = 0;
        meanCPU = 0;
        peakCPU = 0;
        setValuesToZero();
        setClockHandToZero();
        seriesLine.getData().clear(); // Code from reference 1
        seriesArea.getData().clear(); // Code from reference 1
        recordCount = 1;
    }
}

