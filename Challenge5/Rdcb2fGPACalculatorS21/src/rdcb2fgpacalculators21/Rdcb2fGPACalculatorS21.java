/*
 * Name: Ryan Christopher
 * Pawprint: rdcb2f
 * Class: CS 3330 Object Oriented Programming
 * Date: 2/13/21
 */
package rdcb2fgpacalculators21;

// Imports
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @author Ryan Christopher
 * 
 * @references
 *  1) http://www.tutorialsface.com/2016/12/how-to-make-numeric-decimal-textfield-in-javafx-example-tutorial/
 */
public class Rdcb2fGPACalculatorS21 extends Application {
    
    private String title = "GPA Calculator";
    private int width = 500;
    private int height = 425;
    
    private String fontStyle = "Times New Roman";
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(15, 15, 15, 15));
        
        // Create and Add Labels
        Label courseOneLabel = new Label("Course 1:");
        courseOneLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(courseOneLabel, 0, 0, 1, 1);
        
        Label courseTwoLabel = new Label("Course 2:");
        courseTwoLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(courseTwoLabel, 0, 1, 1, 1);
        
        Label courseThreeLabel = new Label("Course 3:");
        courseThreeLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(courseThreeLabel, 0, 2, 1, 1);
        
        Label courseFourLabel = new Label("Course 4:");
        courseFourLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(courseFourLabel, 0, 3, 1, 1);
        
        Label informationLabel = new Label("Information:");
        informationLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 18));
        root.add(informationLabel, 0, 4, 2, 1);
                
         
        
        // Create and Add Text Fields
        TextField score1 = new TextField();
        score1.setPrefWidth(350);
        root.add(score1, 1, 0, 1, 1);
        
        TextField score2 = new TextField();
        score2.setPrefWidth(350);
        root.add(score2, 1, 1, 1, 1);
        
        TextField score3 = new TextField();
        score3.setPrefWidth(350);
        root.add(score3, 1, 2, 1, 1);
        
        TextField score4 = new TextField();
        score4.setPrefWidth(350);
        root.add(score4, 1, 3, 1, 1);
        
        // Create Text Area
        TextArea infoArea = new TextArea();
        infoArea.setPrefRowCount(3);
        infoArea.setWrapText(true);
        infoArea.setEditable(false);
        root.add(infoArea, 0, 5, 2, 1);
        
        
        // Create Buttons
        Button calculateAverageScoreButton = new Button("Calculate Average Score");
        calculateAverageScoreButton.setMaxWidth(Double.MAX_VALUE);
        
        Button calculateGPAButton = new Button("Calculate GPA");
        calculateGPAButton.setMaxWidth(Double.MAX_VALUE);
        
        Button alertButton = new Button("Alert");
        alertButton.setMaxWidth(Double.MAX_VALUE);
        
        Button clearAllButton = new Button ("Clear All");
        clearAllButton.setMaxWidth(Double.MAX_VALUE);
        
        
        // Create VBox for Buttons
        VBox vboxForButtons = new VBox(10);
        vboxForButtons.setAlignment(Pos.CENTER);
        vboxForButtons.getChildren().add(calculateAverageScoreButton);
        vboxForButtons.getChildren().add(calculateGPAButton);
        vboxForButtons.getChildren().add(alertButton);
        vboxForButtons.getChildren().add(clearAllButton);
        root.add(vboxForButtons, 0, 6, 2, 1);
        
        // Create Alert
        Alert mainAlert = new Alert(AlertType.NONE);
        
        // Create Event Listener so score1 value muust be numerical
        // Start of Code from Reference 1
        score1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?") || Integer.valueOf(score1.getText()) > 100) {
                    mainAlert.setAlertType(AlertType.ERROR);
                    mainAlert.setContentText("Insert only integer numbers from 0 to 100");
                    mainAlert.show();
                    score1.setText("");
                }
            }
        });
        // Create Event Listener so score2 value must be numerical
        score2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?") || Integer.valueOf(score2.getText()) > 100) {
                    mainAlert.setAlertType(AlertType.ERROR);
                    mainAlert.setContentText("Insert only integer numbers from 0 to 100");
                    mainAlert.show();
                    score2.setText("");
                }
            }
        });
        // Create Event Listener so score3 value must be numerical
        score3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?") || Integer.valueOf(score3.getText()) > 100) {
                    mainAlert.setAlertType(AlertType.ERROR);
                    mainAlert.setContentText("Insert only integer numbers from 0 to 100");
                    mainAlert.show();
                    score3.setText("");
                }
            }
        });
        // Create Event Listener so score4 value must be numerical
        score4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?") || Integer.valueOf(score4.getText()) > 100) {
                    mainAlert.setAlertType(AlertType.ERROR);
                    mainAlert.setContentText("Insert only integer numbers from 0 to 100");
                    mainAlert.show();
                    score4.setText("");
                }
            }
        });
        // End of Code from Reference 1
        
        
        // Create Action for calculateAverageScoreButton
        calculateAverageScoreButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                
                // Finds Average of all 4 scores
                int totalScore = Integer.valueOf(score1.getText()) + Integer.valueOf(score2.getText()) + Integer.valueOf(score3.getText()) + Integer.valueOf(score4.getText());
                float totalAverage = Float.valueOf(totalScore)/4f;     
                
                // Sets TextArea value to display Average Score
                infoArea.setText("Your Average Score is: ((" + score1.getText() + " + " +
                        score2.getText() + " + " + score3.getText() + " + " + score4.getText() + ") / " +
                        "4) = " + totalAverage);
                
            }
        });
        
        
        // Create Action for calculateGPAButton
        calculateGPAButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                
                // Finds Average of all 4 scores
                int totalScore = Integer.valueOf(score1.getText()) + Integer.valueOf(score2.getText()) + Integer.valueOf(score3.getText()) + Integer.valueOf(score4.getText());
                float totalAverage = Float.valueOf(totalScore)/4f;  
                
                if (totalAverage >= 87 && totalAverage <= 100) {
                    infoArea.setText("Your GPA is: A");
                }
                else if (totalAverage >= 77 && totalAverage < 87) {
                    infoArea.setText("Your GPA is: B");
                }
                else if (totalAverage >= 67 && totalAverage < 77) {
                    infoArea.setText("Your GPA is: C");
                }
                else if (totalAverage >= 60 && totalAverage < 67) {
                    infoArea.setText("Your GPA is: D");
                }
                else if (totalAverage >= 0 && totalAverage < 60) {
                    infoArea.setText("Your GPA is: F");
                }
                else {
                    infoArea.setText("Your GPA could not be found.");
                }
            }
        });
        
        
        // Create Action for AlertButton
        alertButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                if (infoArea.getText().trim().length() == 0) {
                    mainAlert.setAlertType(AlertType.ERROR);
                    mainAlert.setContentText("There is nothing to display.");
                    mainAlert.show();
                }
                else {
                    mainAlert.setAlertType(AlertType.INFORMATION);
                    mainAlert.setContentText(infoArea.getText());
                    mainAlert.show();
                }
            }
        });
        
        
        // Create Action for ClearAllButton
        clearAllButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                score1.setText("");
                score2.setText("");
                score3.setText("");
                score4.setText("");
                infoArea.setText("");
            }
        });
        
        
        // Create Scene
        Scene scene = new Scene(root, width, height);
        
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
