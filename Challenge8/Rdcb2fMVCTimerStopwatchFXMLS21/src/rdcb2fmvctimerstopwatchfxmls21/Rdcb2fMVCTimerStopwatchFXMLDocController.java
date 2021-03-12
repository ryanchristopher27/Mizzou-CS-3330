package rdcb2fmvctimerstopwatchfxmls21;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ryan Christopher
 */
public class Rdcb2fMVCTimerStopwatchFXMLDocController implements Initializable {
    
    @FXML
    private GridPane root;
    @FXML
    private StackPane stopwatchStack;
    @FXML
    private ImageView timerClockImage;
    @FXML
    private ImageView timerHandImage;
    @FXML
    private Text stopWatchTextValue;
    @FXML
    private Text timerTextValue;
    @FXML
    private Text lapTextValue;
    @FXML
    private Text averageLapTextValue;
    @FXML
    private Button startStopButton;
    @FXML
    private Button recordResetButton;
    @FXML
    private Text lapTextLabel;
    @FXML
    private LineChart<String, Number> lapLineChart;
    @FXML
    private NumberAxis lineChartYAxis;
    @FXML
    private CategoryAxis lineChartXAxis;
    @FXML
    private AreaChart<String, Number> averageLapAreaChart;
    @FXML
    private NumberAxis areaChartYAxis;
    @FXML
    private CategoryAxis areaChartXAxis;
    
//    private XYChart.Series<String, Number> seriesLine;
//    private XYChart.Series<String, Number> seriesArea;
    
    private Timeline timeline;
    private KeyFrame keyFrame;
    
//    private AbstractModel abstractModel;
    private AnalogModel aModel;
    private DigitalModel dModel;
        
//    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000 * aModel.getTickTimeInSeconds()), (ActionEvent event) -> { 
////        secondsElapsed += tickTimeInSeconds;
//        aModel.setSecondsElapsed(aModel.getSecondsElapsed() + aModel.getTickTimeInSeconds());
//        updateDigitalClock();
//        dModel.updateLap();
//        rotateHand();
//        updateTimer();
//    }));
    
//    dModel = new DigitalModel;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dModel = new DigitalModel();
        aModel = new AnalogModel();
        
        timeline = new Timeline(new KeyFrame(Duration.millis(1000 * aModel.getTickTimeInSeconds()), (ActionEvent event) -> { 
            aModel.setSecondsElapsed(aModel.getSecondsElapsed() + aModel.getTickTimeInSeconds());
            updateDigitalClock();
            dModel.updateLap();
            rotateHand();
            updateTimer();
        }));
        
        timeline.setCycleCount(Animation.INDEFINITE);
        
        
        
//        dModel.setupTimeline(new KeyFrame(Duration.millis(aModel.getTickTimeInSeconds() * 1000), (ActionEvent event) -> {
//            aModel.setSecondsElapsed(aModel.getSecondsElapsed() + aModel.getTickTimeInSeconds());
//            updateDigitalClock();
//            dModel.updateLap();
//            rotateHand();
//            updateTimer();
//        }));
        
//        timeline.setCycleCount(Animation.INDEFINITE);

        dModel.setSeriesLine(new XYChart.Series());
        lapLineChart.getData().add(dModel.getSeriesLine());
        
        dModel.setSeriesArea(new XYChart.Series());
        averageLapAreaChart.getData().add(dModel.getSeriesArea());

    }   
    
    @FXML
    public void startStopButtonClicked(ActionEvent event) {
        if (isRunning()) {
            // Stop Code
            stop();
        }
        else {
            // Start Code
            start();
        }
    }
    
    @FXML
    public void recordResetButtonClicked(ActionEvent event) {
        if (isRunning()) {
            // Record Code
            record();
        }
        else {
            // Reset Code
            reset();
        }
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
    
    public void setupTimeline(KeyFrame keyFrame) {
//        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (Action event) -> {
//            secondsElapsed += tickTimeInSeconds;
//            updateDigitalClock();
//            updateLap();
//            rotateHand();
//            updateTimer();
//        });
//        this.keyFrame = keyFrame;
//        
//        timeline = new Timeline(keyFrame);
//        timeline.setCycleCount(Animation.INDEFINITE);

    }
    
    public void start() {
        timeline.play();
        startStopButton.setText("Stop");
        recordResetButton.setText("Record");
        setTimerValue();
    }
    
    public void stop() {
        timeline.pause();
        startStopButton.setText("Start");
        recordResetButton.setText("Reset");
    }
    
    public void record() {
        if (dModel.getTimesUp() == false) {
            setLap();
            updateRecordLapChart();
            updateAverageLapChart();
            dModel.resetLap();
            lineChartYAxis.setUpperBound(dModel.getMaxLapTime());
            areaChartYAxis.setUpperBound(dModel.getMaxLapTime());
        }
    }
    
    public void reset() {
        timeline.stop();
        timerHandImage.setRotate(0);
        startStopButton.setText("Start");
        recordResetButton.setText("Record");
        stopWatchTextValue.setText("--:--:--.--");
        timerTextValue.setText("--:--:--.--");
        lapTextValue.setText("--:--:--.--");
        averageLapTextValue.setText("--:--:--.--");
        lapTextLabel.setText("Lap");
//        seriesLine.getData().clear();
        dModel.getSeriesLine().getData().clear();
//        seriesArea.getData().clear();
        dModel.getSeriesArea().getData().clear();
        aModel.resetValues();
        dModel.resetValues();
        lineChartYAxis.setUpperBound(5);
        areaChartYAxis.setUpperBound(5);
    }
    
    public void setLap() { 
        lapTextValue.setText(dModel.setLapString());
        
        updateAverageLap();
        
        lapTextLabel.setText("Lap " + dModel.getTotalLaps() + ":");
    }
    
    public void updateAverageLap() {
        averageLapTextValue.setText(dModel.setAverageLapString());     
    }

    public void setTimerValue() {
        String tempTimerValue = Integer.toString(dModel.getStartValue());
        timerTextValue.setText(tempTimerValue + ".00");
    }
    
    public void updateTimer() {
        String secondsTemp, centisTemp;
        
        if (dModel.getSeconds()+1 > dModel.getStartValue()) {
            timerTextValue.setText("Times Up!");
            dModel.setTimesUp(true);
        }
        else {
            secondsTemp = String.format("%02d", ((dModel.getStartValue()-1) - dModel.getSeconds()));
            centisTemp = String.format("%02d", (100 - dModel.getCentis()));

            timerTextValue.setText(secondsTemp + "." + centisTemp);
            dModel.setTimesUp(false);
        }
    }
    
    // Ensures that the Record Graph value is up to date
    private void updateRecordLapChart() {  
        dModel.getSeriesLine().getData().add(new XYChart.Data(Integer.toString(dModel.getTotalLaps()), dModel.getLapSeconds()));
    }
    
    // Ensures that the Mean Graph value is up to date
    private void updateAverageLapChart() {
        dModel.getSeriesArea().getData().add(new XYChart.Data(Integer.toString(dModel.getTotalLaps()), dModel.getSeconds()/dModel.getTotalLaps()));
    }
    
    /// NEW ADDITIONS ///
    public void rotateHand() {
        double rotation = aModel.findRotation();
        timerHandImage.setRotate(rotation);
    }
    
    public void updateDigitalClock() {
        stopWatchTextValue.setText(dModel.updateDigitalClock());
    }
}
