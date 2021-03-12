/*
 * Name: Ryan Christopher
 * Pawprint: rdcb2f
 * Class: CS 3330 Object Oriented Programming
 * Challenge: 6 TimerStopwatchS21
 * Date: February 20, 2021
 */
package rdcb2ftimerstopwatchs21;

import java.text.SimpleDateFormat;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Ryan Christopher
 * 
 * @references
 *  1) https://www.geeksforgeeks.org/javafx-textinputdialog/
 *  2) https://www.w3schools.com/java/java_try_catch.asp
 */
public class Rdcb2fTimerStopwatchS21 extends Application {
    
    private double secondsElapsed = 0;
    private double tickTimeInSeconds = 0.01; // Used to change resolution
    private double angleDeltaPerSeconds = 6;
    private double timeConstant = 1000;
    
    private Timeline timeline;
    private ImageView dialImageView;
    private ImageView handImageView;
    
    private String appName = "Stopwatch";
    private String fontStyle = "Times New Roman";
    
    Button startStopButton, recordResetButton;
    Text timeElapsedText;
    Text timerText;
    Label recordingOneLabel, recordingTwoLabel, recordingThreeLabel;
    Text recordingOneText, recordingTwoText, recordingThreeText;
    Text totalTimeOneText, totalTimeTwoText, totalTimeThreeText;
   
    int startValue;
    int minutes = 0;
    int seconds = 0;
    int centis = 0;
    
    int lapMinutes = 0;
    int lapSeconds = 0;
    int lapCentis = 0;
    
    boolean lapOneUsed = false, lapTwoUsed = false, lapThreeUsed = false;
    int lapOverflow = 4;
    
    int timerSeconds = 0;
    int timerCentis = 0;
    boolean timesUp = false;
    
    @Override
    public void start(Stage primaryStage) {

        startValue = getStartValue();

        // Create Gridpane
        GridPane root = new GridPane();
//        root.setGridLinesVisible(true);
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(15, 15, 15, 15));
        
        // Create StackPane to hold clock
        StackPane clockRoot = new StackPane();
        dialImageView = new ImageView();
        handImageView = new ImageView();
        clockRoot.getChildren().addAll(dialImageView, handImageView);
        Image dialImage = new Image(getClass().getResourceAsStream("clockface.png"));
        Image handImage = new Image(getClass().getResourceAsStream("hand.png"));
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);
        
        // Add clock to Grid
        root.add(clockRoot, 0, 0, 5, 1);
        
        
        // Create Start/Stop Button
        startStopButton = new Button("Start");
        startStopButton.setPrefWidth(100);
        startStopButton.setAlignment(Pos.CENTER);
        root.add(startStopButton, 0, 1, 2, 1);
        recordResetButton = new Button("Reset");
        recordResetButton.setPrefWidth(100);
        recordResetButton.setAlignment(Pos.CENTER);
        root.add(recordResetButton, 0, 2, 2, 1);
        

        // Create and add Time Elapsed
        timeElapsedText = new Text("00:00.00");
        timeElapsedText.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(timeElapsedText, 0, 3, 2, 1);
        
        // Create and add Count Down
        Label timerLabel = new Label("Timer: ");
        timerLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(timerLabel, 0, 4, 1, 1);
        timerText = new Text("00:00");
        setTimerValue();
        timerText.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(timerText, 1, 4, 1, 1);
        
        
        // Create and add Laps
        Label lapTimeLabel = new Label("Lap Time");
        lapTimeLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        lapTimeLabel.setAlignment(Pos.CENTER);
        root.add(lapTimeLabel, 2, 1, 2, 1);
        
        
        // Create and add Recording Labels
        recordingOneLabel = new Label("Rec 1: ");
        recordingOneLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(recordingOneLabel, 2, 2, 1, 1);
         
        recordingTwoLabel = new Label("Rec 2: ");
        recordingTwoLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(recordingTwoLabel, 2, 3, 1, 1);
         
        recordingThreeLabel = new Label("Rec 3: ");
        recordingThreeLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(recordingThreeLabel, 2, 4, 1, 1);
         
        
        // Create and add Recording Texts
        recordingOneText = new Text("+00:00:00");
        recordingOneText.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(recordingOneText, 3, 2, 1, 1);
         
        recordingTwoText = new Text("+00:00:00");
        recordingTwoText.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(recordingTwoText, 3, 3, 1, 1);
         
        recordingThreeText = new Text("+00:00:00");
        recordingThreeText.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(recordingThreeText, 3, 4, 1, 1);
        
        
        // Create and add Total Time Label
        Label totalTimeLabel = new Label("Total Time");
        totalTimeLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        totalTimeLabel.setAlignment(Pos.CENTER);
        root.add(totalTimeLabel, 4, 1, 1, 1);
        
        
        // Create and add Total Time Texts
        totalTimeOneText = new Text("00:00:00");
        totalTimeOneText.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(totalTimeOneText, 4, 2, 1, 1);
        
        totalTimeTwoText = new Text("00:00:00");
        totalTimeTwoText.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(totalTimeTwoText, 4, 3, 1, 1);
        
        totalTimeThreeText = new Text("00:00:00");
        totalTimeThreeText.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(totalTimeThreeText, 4, 4, 1, 1);
        
        // Set Action for Start and Stop Button
        startStopButton.setOnAction((ActionEvent event) -> {
            if (isRunning()) {
                timeline.pause();
                startStopButton.setText("Start");
                recordResetButton.setText("Reset");
            }
            else {
                timeline.play();
                startStopButton.setText("Stop");
                recordResetButton.setText("Record");
            }
        });
        
        Alert timerAlert = new Alert(AlertType.ERROR);
        // Set Action for Record and Reset Button
        recordResetButton.setOnAction((ActionEvent event) -> {
            if (isRunning()) {
                if (timesUp) {
                    timerAlert.setContentText("Time is up... No more records...");
                    timerAlert.show();
                }
                else {
                    updateLaps();
                }
            }
            else {
                reset();
            }
        });
        
        
        KeyFrame keyFrame = new KeyFrame(Duration.millis(timeConstant * tickTimeInSeconds), (ActionEvent event) -> {
            
            secondsElapsed += tickTimeInSeconds;
            double rotation = secondsElapsed * angleDeltaPerSeconds;
            handImageView.setRotate(rotation);
            updateTimer();
            updateDigitalClock();
        });
        
        timeline = new Timeline(keyFrame);
        
        timeline.setCycleCount(Animation.INDEFINITE);
              
        Scene scene = new Scene(root, 1000, 1000);
        
        primaryStage.setTitle(appName);
        primaryStage.setScene(scene);
        primaryStage.show();
        
//        setTickTimeInSeconds(1.0);
    }
    
    // Checks if clock is running
    public boolean isRunning() {
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                return true;
            }
        }
        return false;
    }
    
    // Resets all components of the clock
    public void reset() {
        timeline.stop();
        handImageView.setRotate(0);
        secondsElapsed = 0;
        recordResetButton.setText("Record");
        startStopButton.setText("Start");
        minutes = 0;
        seconds = 0;
        centis = 0;
        updateDigitalClock();
        setTimerValue();
        lapOneUsed = false;
        lapTwoUsed = false;
        lapThreeUsed = false;
        lapOverflow = 4;
        recordingOneLabel.setText("Rec 1: ");
        recordingTwoLabel.setText("Rec 2: ");
        recordingThreeLabel.setText("Rec 3: ");
        resetLaps();
    }
    
    // Updates the Digital Clock
    public void updateDigitalClock() {
        String minutesTemp, secondsTemp, centisTemp;
        
        if (seconds == 60) {
            minutes = minutes + 1;
            seconds = 0;
            lapMinutes = lapMinutes +1;
            lapSeconds = 0;
        }
        if (centis == 100) {
            seconds = seconds + 1;
            centis = 0;
            lapSeconds = lapSeconds + 1;
            lapCentis = 0;
        }
        
        minutesTemp = String.format("%02d", minutes);
        secondsTemp = String.format("%02d", seconds);
        centisTemp = String.format("%02d", centis);
        timeElapsedText.setText(minutesTemp + ":" + secondsTemp + "." + centisTemp);
        
        centis++;
        lapCentis++;
    }

    // Updates the Timer
    public void updateTimer() {
        String secondsTemp, centisTemp;
        
        if (seconds+1 > startValue) {
            timerText.setText("Times Up!");
            timesUp = true;
        }
        else {
            secondsTemp = String.format("%02d", ((startValue-1) - seconds));
            centisTemp = String.format("%02d", (100 - centis));

            timerText.setText(secondsTemp + "." + centisTemp);
            timesUp = false;
        }
    }
    
    // Gets the starting value from the user
    public int getStartValue() {
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
                start = Integer.parseInt(startingValue.getEditor().getText());
                hold = false;
            }
            catch (Exception e) { // Code from Reference 2
                continue;
            }
            
            if (start < 0) {
                hold = true;
            }
        }
        
        return start;
    }
    
    // Sets the Timer value 
    public void setTimerValue() {
        timerText.setText(startValue + ".00");
    }
    
    // Sets the lap
    public void setLap(int lapNumber) {
        String minutesTemp, secondsTemp, centisTemp;
        
        minutesTemp = String.format("%02d", lapMinutes);
        secondsTemp = String.format("%02d", lapSeconds);
        centisTemp = String.format("%02d", lapCentis);
        
        if (lapNumber == 1) {
            recordingOneText.setText("+" + minutesTemp + ":" + secondsTemp + "." + centisTemp);
            lapOneUsed = true;
        }
        else if (lapNumber == 2) {
            recordingTwoText.setText("+" + minutesTemp + ":" + secondsTemp + "." + centisTemp);
            lapTwoUsed = true;
        }
        else {
            recordingThreeText.setText("+" + minutesTemp + ":" + secondsTemp + "." + centisTemp);
            lapThreeUsed = true;
        }
        
        lapMinutes = 0;
        lapSeconds = 0;
        lapCentis = 0;
    }
    
    // Sets the total time
    public void setTotalTime(int lapNumber) {
        String minutesTemp, secondsTemp, centisTemp;
        
        minutesTemp = String.format("%02d", minutes);
        secondsTemp = String.format("%02d", seconds);
        centisTemp = String.format("%02d", centis);
        
        if (lapNumber == 1) {
            totalTimeOneText.setText(minutesTemp + ":" + secondsTemp + "." + centisTemp);
//            lapOneUsed = true;
        }
        else if (lapNumber == 2) {
            totalTimeTwoText.setText(minutesTemp + ":" + secondsTemp + "." + centisTemp);
//            lapTwoUsed = true;
        }
        else {
            totalTimeThreeText.setText(minutesTemp + ":" + secondsTemp + "." + centisTemp);
//            lapThreeUsed = true;
        }
    }
    
    // Updates all the laps
    public void updateLaps() {
        if (lapOneUsed == false && lapTwoUsed == false && lapThreeUsed == false) {
            setLap(1);
            setTotalTime(1);
        }
        else if (lapOneUsed == true && lapTwoUsed == false && lapThreeUsed == false) {
            setLap(2);
            setTotalTime(2);
            
        }
        else if (lapOneUsed == true && lapTwoUsed == true && lapThreeUsed == false) {
            setLap(3);
            setTotalTime(3);
        }
        else {
            if (lapOverflow%3 == 1) {
                setLap(1);
                setTotalTime(1);
                recordingOneLabel.setText("Rec " + lapOverflow + ": ");
                lapOverflow++;
            }
            else if (lapOverflow%3 == 2) {
                setLap(2);
                setTotalTime(2);
                recordingTwoLabel.setText("Rec " + lapOverflow + ": ");
                lapOverflow++;
            }
            else {
                setLap(3);
                setTotalTime(3);
                recordingThreeLabel.setText("Rec " + lapOverflow + ": ");
                lapOverflow++;
            }
        }
    }
    
    // Resets the laps and total time
    public void resetLaps() {
        recordingOneText.setText("+00:00.00");
        recordingTwoText.setText("+00:00.00");
        recordingThreeText.setText("+00:00.00");
        totalTimeOneText.setText("00:00.00");
        totalTimeTwoText.setText("00:00.00");
        totalTimeThreeText.setText("00:00.00");
    }
    
    // Sets the Tick Time In Seconds
    public void setTickTimeInSeconds(Double tickTimeInSeconds) {
        this.tickTimeInSeconds = tickTimeInSeconds;
        
        if (tickTimeInSeconds == 1) {
            timeConstant = 10;
            angleDeltaPerSeconds = 0.06;
        }
        else if (tickTimeInSeconds == 0.1) {
            timeConstant = 100;
            angleDeltaPerSeconds = 0.6;
        }
        else {
            timeConstant = 1000;
            angleDeltaPerSeconds = 6;            
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
